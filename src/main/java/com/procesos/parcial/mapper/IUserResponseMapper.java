package com.procesos.parcial.mapper;

import com.procesos.parcial.dto.UserResponseDto;
import com.procesos.parcial.model.User;

import java.util.List;

public interface IUserResponseMapper {

    UserResponseDto toResponse(User user);
    List<UserResponseDto> toResponseList(List<User> userList);

}
