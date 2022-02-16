package com.cod.merch.model.DTO;

import com.cod.merch.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private boolean sex;
    private boolean admin;
    private Long balance;
    private DepartmentDTO department;
    public UserDTO(User user) {
        department = new DepartmentDTO(user.getDepartment());
        id = user.getId();
        email = user.getEmail();
        name = user.getName();
        surname = user.getSurname();
        sex = user.getSex();
        admin = user.getIsAdmin();
        balance = user.getBalance();
    }
}
