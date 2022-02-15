package com.cod.merch.service;

import com.cod.merch.model.DTO.request.LoginRequest;
import com.cod.merch.model.DTO.request.RegisterRequest;
import com.cod.merch.model.DTO.UserDTO;
import com.cod.merch.model.Department;
import com.cod.merch.model.User;
import com.cod.merch.repository.DepartmentRepository;
import com.cod.merch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public boolean register(RegisterRequest request) {
        try {
            Department department = departmentRepository.getById(request.getDepartment_id());
            User user = new User(request.getName(),
                    request.getSurname(),
                    request.getPassword(),
                    request.isSex(),
                    new Department(),
                    request.getEmail());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserDTO login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        return userOptional.isEmpty() ? null : new UserDTO(userOptional.get());
    }
}
