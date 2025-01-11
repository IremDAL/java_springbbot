package com.iremdal.todo.business.services.impl;

import com.iremdal.todo.business.dto.TodoDto;
import com.iremdal.todo.business.dto.UsersDto;
import com.iremdal.todo.business.services.interfaces.ITodoService;
import com.iremdal.todo.business.services.interfaces.IUsersService;
import com.iremdal.todo.data.entity.TodoEntity;
import com.iremdal.todo.data.entity.UsersEntity;
import com.iremdal.todo.data.mapper.TodoMapper;
import com.iremdal.todo.data.mapper.UsersMapper;
import com.iremdal.todo.data.repository.ITodoRepository;
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
public class TodoServiceImpl implements ITodoService<TodoDto, TodoEntity> {
    private final ITodoRepository iTodoRepository;
    private final IUsersRepository iUsersRepository;

    @Override
    public TodoDto entityToDto(TodoEntity todoEntity) {
        return TodoMapper.TodoEntityToTodoDto(todoEntity);
    }

    @Override
    public TodoEntity dtoToEntity(TodoDto todoDto) {
        return TodoMapper.TodoDtoToTodoEntity(todoDto);
    }
    @Override
    public TodoDto objectServiceCreate(TodoDto todoDto) {
        if (todoDto == null) {
            throw new _404_NotFoundException("Kullanıcı bilgileri boş olamaz.");
        }
        TodoEntity todoEntitySave = dtoToEntity(todoDto);
        UsersEntity usersEntity = iUsersRepository.findById(todoDto.getUserId()).orElseThrow(() -> new _404_NotFoundException("kullanıcı bulunamadı"));
        TodoEntity savedEntity = iTodoRepository.save(todoEntitySave);
        return entityToDto(savedEntity);
    }

    @Override
    public List<TodoDto> objectServiceList() {
        List<TodoEntity> todoEntityList = iTodoRepository.findAll();
        if (todoEntityList.isEmpty()) {
            throw new _404_NotFoundException("Kullanıcı bulunamadı.");
        }
        return todoEntityList.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public TodoDto objectServiceUpdate(Long id, TodoDto todoDto) {
        TodoEntity existingEntity = iTodoRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Kullanıcı bulunamadı: " + id));
        existingEntity.setTitle(todoDto.getTitle());
        existingEntity.setDescription(todoDto.getDescription());
        existingEntity.setPriority(todoDto.getPriority());
        existingEntity.setIsCompleted(todoDto.getIsCompleted());
        existingEntity.setDueDate(todoDto.getDueDate());


        TodoEntity updatedEntity = iTodoRepository.save(existingEntity);
        return entityToDto(updatedEntity);
    }

    @Override
    public TodoDto objectServiceFindById(Long id) {
        return iTodoRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new _404_NotFoundException("Kullanıcı bulunamadı: " + id));
    }

    @Override
    public TodoDto objectServiceDelete(Long id) {
        TodoEntity existingEntity = iTodoRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Kullanıcı bulunamadı: " + id));
        existingEntity.setIsDeleted(true);
        TodoEntity deletedEntity = iTodoRepository.save(existingEntity);
        return entityToDto(deletedEntity);
    }


}
