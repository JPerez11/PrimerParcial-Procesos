package com.procesos.parcial.mapper;

import com.procesos.parcial.dto.UserRequestDto;
import com.procesos.parcial.model.User;

public interface IUserRequestMapper {

    User toUser(UserRequestDto userRequest);

}
