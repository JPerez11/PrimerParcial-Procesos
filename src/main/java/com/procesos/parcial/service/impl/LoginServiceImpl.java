package com.procesos.parcial.service.impl;

import com.procesos.parcial.configuration.security.jwt.JwtToken;
import com.procesos.parcial.dto.LoginRequestDto;
import com.procesos.parcial.exception.IncorrectPasswordException;
import com.procesos.parcial.exception.UserNotFoundException;
import com.procesos.parcial.model.User;
import com.procesos.parcial.repository.IUserRepository;
import com.procesos.parcial.service.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements ILoginService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findUserByEmail(loginRequestDto.getEmail());
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException();
        }
        return JwtToken.generateToken(user);
    }
}
