package com.procesos.parcial.controller;

import com.procesos.parcial.dto.LoginRequestDto;
import com.procesos.parcial.service.ILoginService;
import com.procesos.parcial.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final ILoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDto loginRequest) {
        String token = loginService.login(loginRequest);
        return ResponseEntity.ok(
                Collections.singletonMap(Constants.MESSAGE_TOKEN,
                        token)
        );
    }

}
