package com.iremdal.todo.data.repository;

import com.iremdal.todo.data.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITodoRepository extends JpaRepository<TodoEntity, Long> {

}
