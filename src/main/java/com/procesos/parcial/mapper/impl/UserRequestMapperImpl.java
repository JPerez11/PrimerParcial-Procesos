package com.procesos.parcial.mapper.impl;

import com.procesos.parcial.dto.UserRequestDto;
import com.procesos.parcial.mapper.IUserRequestMapper;
import com.procesos.parcial.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapperImpl implements IUserRequestMapper {

    @Override
    public User toUser(UserRequestDto userRequest) {
        if (userRequest == null) {
            return null;
        }

        User user = new User();

        user.setName( userRequest.getName() );
        user.setLastName( userRequest.getLastName() );
        user.setEmail( userRequest.getEmail() );
        user.setPassword( userRequest.getPassword() );
        user.setAddress( userRequest.getAddress() );
        user.setBirthday( userRequest.getBirthday() );

        return user;
    }
}
