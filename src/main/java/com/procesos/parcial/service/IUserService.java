package com.procesos.parcial.service;

import com.procesos.parcial.dto.UserRequestDto;
import com.procesos.parcial.dto.UserResponseDto;

import java.util.List;

public interface IUserService {

    void saveUser(UserRequestDto user);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    void updateUser(UserRequestDto user, Long id);
}
