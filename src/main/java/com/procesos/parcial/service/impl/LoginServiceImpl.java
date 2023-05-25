package com.procesos.parcial.service.impl;

import com.procesos.parcial.configuration.security.jwt.JwtToken;
import com.procesos.parcial.dto.LoginRequestDto;
import com.procesos.parcial.exception.NoDataFoundException;
import com.procesos.parcial.model.User;
import com.procesos.parcial.repository.IUserRepository;
import com.procesos.parcial.service.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements ILoginService {

    private final IUserRepository userRepository;

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findUserByEmailAndPassword(loginRequestDto.getEmail(),
                loginRequestDto.getPassword());
        if (user == null) {
            throw new NoDataFoundException();
        }

        return JwtToken.generateToken(user);
    }
}
