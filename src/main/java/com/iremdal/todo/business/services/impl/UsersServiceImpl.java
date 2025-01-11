package com.iremdal.todo.business.services.impl;

import com.iremdal.todo.business.dto.UsersDto;
import com.iremdal.todo.business.services.interfaces.IUsersService;
import com.iremdal.todo.data.entity.UsersEntity;
import com.iremdal.todo.data.mapper.UsersMapper;
import com.iremdal.todo.data.repository.IUsersRepository;
import com.iremdal.todo.exception._404_NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Log4j2
@Service
public class UsersServiceImpl implements IUsersService<UsersDto, UsersEntity> {
    private final IUsersRepository iUsersRepository;

    @Override
    public UsersDto entityToDto(UsersEntity usersEntity) {
        return UsersMapper.UsersEntityToUsersDto(usersEntity);
    }

    @Override
    public UsersEntity dtoToEntity(UsersDto usersDto) {
        return UsersMapper.UsersDtoToUsersEntity(usersDto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 30, rollbackFor = Exception.class, noRollbackFor = IllegalArgumentException.class)
    public UsersDto objectServiceCreate(UsersDto usersDto) {
        if (usersDto == null) {
            throw new _404_NotFoundException("Kullanıcı bilgileri boş olamaz.");
        }
        UsersEntity usersEntitySave = dtoToEntity(usersDto);
        UsersEntity savedEntity = iUsersRepository.save(usersEntitySave);
        return entityToDto(savedEntity);
    }

    @Override
    public List<UsersDto> objectServiceList() {
        List<UsersEntity> usersEntityList = iUsersRepository.findAllUsers();
        if (usersEntityList.isEmpty()) {
            throw new _404_NotFoundException("Kullanıcı bulunamadı.");
        }
        return usersEntityList.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "usersFindByIdCache", key = "#id")
    @Override
    public UsersDto objectServiceFindById(Long id) {
        return iUsersRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new _404_NotFoundException("Kullanıcı bulunamadı: " + id));
    }

    @Override
    @Transactional
    public UsersDto objectServiceUpdate(Long id, UsersDto usersDto) {
        UsersEntity existingEntity = iUsersRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Kullanıcı bulunamadı: " + id));
        existingEntity.setUsername(usersDto.getUsername());
        existingEntity.setPassword(usersDto.getPassword());
        existingEntity.setEmail(usersDto.getEmail());
        existingEntity.setIsDeleted(usersDto.getIsDeleted());

        UsersEntity updatedEntity = iUsersRepository.save(existingEntity);
        return entityToDto(updatedEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 30, rollbackFor = Exception.class, noRollbackFor = IllegalArgumentException.class)
    public UsersDto objectServiceDelete(Long id) {
        UsersEntity existingEntity = iUsersRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Kullanıcı bulunamadı: " + id));
        existingEntity.setIsDeleted(true);
        UsersEntity deletedEntity = iUsersRepository.save(existingEntity);
        return entityToDto(deletedEntity);
    }
}
