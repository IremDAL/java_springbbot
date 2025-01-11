package com.iremdal.todo.data.mapper;


import com.iremdal.todo.business.dto.TodoDto;
import com.iremdal.todo.data.entity.TodoEntity;

public class TodoMapper {
    public static TodoDto TodoEntityToTodoDto(TodoEntity todoEntity) {

        TodoDto todoDto = new TodoDto();

        todoDto.setId(todoEntity.getId());

        // Auditing
        todoDto.setCreatedBy(todoEntity.getCreatedBy());
        todoDto.setLastModifiedBy(todoEntity.getLastModifiedBy());
        todoDto.setLastModifiedDate(todoEntity.getLastModifiedDate());

        todoDto.setTitle(todoEntity.getTitle());
        todoDto.setDescription(todoEntity.getDescription());
        todoDto.setPriority(todoEntity.getPriority());
        todoDto.setIsCompleted(todoEntity.getIsCompleted());
        todoDto.setDueDate(todoEntity.getDueDate());
        if(todoEntity.getUsersTodoEntity()!=null) {
            todoDto.setCompositionUsersDto(UsersMapper.UsersEntityToUsersDto(todoEntity.getUsersTodoEntity()));
        }
        return todoDto;
    }

    public static TodoEntity TodoDtoToTodoEntity(TodoDto todoDto) {

        TodoEntity todoEntity = new TodoEntity();
        todoDto.setId(todoDto.getId());
        todoDto.setSystemCreatedDate(todoDto.getSystemCreatedDate());

        // Auditing
        todoDto.setCreatedBy(todoDto.getCreatedBy());
        todoDto.setLastModifiedBy(todoDto.getLastModifiedBy());
        todoDto.setLastModifiedDate(todoDto.getLastModifiedDate());

        todoEntity.setId(todoDto.getId());
        todoEntity.setTitle(todoDto.getTitle());
        todoEntity.setDescription(todoDto.getDescription());
        todoEntity.setPriority(todoDto.getPriority());
        todoEntity.setIsCompleted(todoDto.getIsCompleted());
        todoEntity.setDueDate(todoDto.getDueDate());

        if(todoDto.getCompositionUsersDto()!=null) {
            todoEntity.setUsersTodoEntity(UsersMapper.UsersDtoToUsersEntity(todoDto.getCompositionUsersDto()));
        }
        return todoEntity;
    }
}
