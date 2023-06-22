package com.procesos.parcial.service.impl;

import com.procesos.parcial.dto.UserRequestDto;
import com.procesos.parcial.dto.UserResponseDto;
import com.procesos.parcial.exception.NoDataFoundException;
import com.procesos.parcial.mapper.IUserRequestMapper;
import com.procesos.parcial.mapper.IUserResponseMapper;
import com.procesos.parcial.model.User;
import com.procesos.parcial.repository.IUserRepository;
import com.procesos.parcial.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserResponseMapper userResponseMapper;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void saveUser(UserRequestDto user) {
        if (user == null) {
            throw new NullPointerException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userRequestMapper.toUser(user));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.toResponseList(userRepository.findAll());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User userDb = userRepository.findUserById(id);
        if (userDb == null) {
            throw new NoDataFoundException();
        }
        return userResponseMapper.toResponse(userDb);
    }

    @Override
    public void updateUser(UserRequestDto user, Long id) {
        User userDb = userRepository.findUserById(id);
        if (userDb == null) {
            throw new NoDataFoundException();
        }
        userDb.setName( user.getName() );
        userDb.setLastName( user.getLastName() );
        userDb.setEmail( user.getEmail() );
        userDb.setPassword( user.getPassword() );
        userDb.setAddress( user.getAddress() );
        userDb.setBirthday( user.getBirthday() );

        userRepository.save(userDb);
    }



}
