package com.procesos.parcial.mapper.impl;

import com.procesos.parcial.dto.UserResponseDto;
import com.procesos.parcial.mapper.IUserResponseMapper;
import com.procesos.parcial.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserResponseMapperImpl implements IUserResponseMapper {


    @Override
    public UserResponseDto toResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setName( user.getName() );
        userResponseDto.setLastName( user.getLastName() );
        userResponseDto.setEmail( user.getEmail() );
        userResponseDto.setAddress( user.getAddress() );
        userResponseDto.setBirthday( user.getBirthday() );

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> toResponseList(List<User> userList) {

        if (userList.isEmpty()) {
            return Collections.emptyList();
        }

        List<UserResponseDto> list = new ArrayList<>();

        for (User user :
                userList) {
            list.add( toResponse(user) );
        }

        return list;
    }
}
