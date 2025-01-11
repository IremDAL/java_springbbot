package com.iremdal.todo.data.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

// @NamedQuery anotasyonu ile tanımlanan statik sorgulardır.
@NamedQueries({
        @NamedQuery(name = "UsersEntity.findAllUsers", query = "SELECT u FROM Users u"),
        @NamedQuery(name = "UsersEntity.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
})

// ENTITY
@Entity(name = "Users") // name="Customers" => Relation için name yazdım
@Table(
        name = "users" // name="customers" => Database tablo adı için ekledim
)


public class UsersEntity extends BaseEntity {

    private String username;

    private String password;


    private String email;

    private Boolean isDeleted = Boolean.FALSE;
    @Version
    private int version;


}
