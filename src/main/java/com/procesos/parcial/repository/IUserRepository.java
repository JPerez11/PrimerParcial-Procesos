package com.procesos.parcial.repository;

import com.procesos.parcial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    User findUserByEmailAndPassword(String email, String password);

}
