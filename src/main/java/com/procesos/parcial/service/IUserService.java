package com.procesos.parcial.service;

import com.procesos.parcial.model.User;

import java.util.List;

public interface IUserService {

    void saveUser(User user);

    List<User> getAllUsers();

    User getOneUser(User user);

    User getUserById(Long id);

    void updateUser(User user, Long id);
}
