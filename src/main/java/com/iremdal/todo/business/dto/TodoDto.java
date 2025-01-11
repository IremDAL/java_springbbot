package com.iremdal.todo.business.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

// Users(1)- Todos(N)
public class TodoDto extends BaseDto {

    @NotEmpty(message = "{todo.title.validation.constraints.NotNull.message}")
    private String title;

    @NotEmpty(message = "{todo.description.validation.constraints.NotNull.message}")
    private String description;

    @NotNull(message = "{todo.priority.validation.constraints.NotNull.message}")
    private int priority;

    @NotNull(message = "{todo.isCompleted.validation.constraints.NotNull.message}")
    private Boolean isCompleted;

    @NotNull(message = "{todo.dueDate.validation.constraints.NotNull.message}")
    private LocalDateTime dueDate;

    @NotNull(message = "user id boş olamaz")
    private Long userId;

    // RELATION
    private UsersDto compositionUsersDto;
}
