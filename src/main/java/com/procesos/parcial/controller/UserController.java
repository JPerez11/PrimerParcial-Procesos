package com.procesos.parcial.controller;

import com.procesos.parcial.messages.MessageUser;
import com.procesos.parcial.model.User;
import com.procesos.parcial.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;


    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> saveUser(@RequestBody User user) {
        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message", MessageUser.USER_CREATED.getMessage()));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(user, id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message", MessageUser.USER_UPDATED.getMessage()));
    }

}