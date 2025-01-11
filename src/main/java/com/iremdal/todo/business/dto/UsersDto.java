package com.iremdal.todo.business.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsersDto extends BaseDto {

    @NotEmpty(message = "{users.username.validation.constraints.NotNull.message}")
    private String username;

    @NotEmpty(message = "{users.password.validation.constraints.NotNull.message}")
    private String password;

    @NotEmpty(message = "{users.email.validation.constraints.NotNull.message}")
    @Email(message = "{users.email1.validation.constraints.NotNull.message}")
    private String email;
    private Boolean isDeleted = false;

    private int version;

}
