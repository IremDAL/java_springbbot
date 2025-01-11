package com.iremdal.todo.data.mapper;

import com.iremdal.todo.business.dto.UsersDto;
import com.iremdal.todo.data.entity.UsersEntity;

public class UsersMapper {
    public static UsersDto UsersEntityToUsersDto(UsersEntity usersEntity) {

        UsersDto usersDto = new UsersDto();

        usersDto.setId(usersEntity.getId());
        usersDto.setIsDeleted(usersEntity.getIsDeleted());
        usersDto.setVersion(usersEntity.getVersion());

        // Auditing
        usersDto.setCreatedBy(usersEntity.getCreatedBy());

        usersDto.setLastModifiedBy(usersEntity.getLastModifiedBy());
        usersDto.setLastModifiedDate(usersEntity.getLastModifiedDate());

        usersDto.setUsername(usersEntity.getUsername());
        usersDto.setPassword(usersEntity.getPassword());
        usersDto.setEmail(usersEntity.getEmail());
        return usersDto;
    }

    public static UsersEntity UsersDtoToUsersEntity(UsersDto usersDto) {

        UsersEntity usersEntity = new UsersEntity();
        usersDto.setId(usersDto.getId());
        usersDto.setSystemCreatedDate(usersDto.getSystemCreatedDate());
        usersDto.setIsDeleted(usersDto.getIsDeleted());
        usersDto.setVersion(usersDto.getVersion());

        // Auditing
        usersDto.setCreatedBy(usersDto.getCreatedBy());
        usersDto.setLastModifiedBy(usersDto.getLastModifiedBy());
        usersDto.setLastModifiedDate(usersDto.getLastModifiedDate());

        usersEntity.setId(usersDto.getId());
        usersEntity.setUsername(usersDto.getUsername());
        usersEntity.setPassword(usersDto.getPassword());
        usersEntity.setEmail(usersDto.getEmail());
        return usersEntity;
    }
}
