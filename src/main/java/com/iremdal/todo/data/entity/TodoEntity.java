package com.iremdal.todo.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@Entity(name = "Todo")
@Table(name = "todo")

public class TodoEntity extends BaseEntity{
    private String title;
    private String description;
    private int priority;
    private Boolean isCompleted;
    private LocalDateTime dueDate;
    private Boolean isDeleted = false;
    // Order(N) - Customer(1)
    // Order(1) - Customer(1) NOT: Customer bilgilerine Order üzerinden erişimden sağlayacağım.
    // Bundan dolayı @JoinColumn Order içinde yazıyoruz
    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "customer_id", referencedColumnName = "id",unique = true) //FK için
    @JoinColumn(name = "users_id", referencedColumnName = "id",unique = false) //FK için
    private UsersEntity usersTodoEntity;


}
