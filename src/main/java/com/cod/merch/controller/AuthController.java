package com.cod.merch.controller;

import com.cod.merch.model.DTO.UserDTO;
import com.cod.merch.model.DTO.request.LoginRequest;
import com.cod.merch.model.DTO.request.RegisterRequest;
import com.cod.merch.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public synchronized ResponseEntity<Boolean> register(RegisterRequest request) {
        boolean OK = authService.register(request);
        return OK ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/login")
    public synchronized ResponseEntity<UserDTO> login(LoginRequest request) {
        UserDTO user = authService.login(request);
        return user == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(user, HttpStatus.OK);
    }
}
