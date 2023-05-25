package com.procesos.parcial.repository;

import com.procesos.parcial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

}
