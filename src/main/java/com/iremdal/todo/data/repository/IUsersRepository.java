package com.iremdal.todo.data.repository;


import com.iremdal.todo.data.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsersRepository extends JpaRepository<UsersEntity,Long> {

    List<UsersEntity> findAllUsers();

    // 2. Belirli soyadı olan müşteriyi getir
    List<UsersEntity> findByUsername(String username);


}
