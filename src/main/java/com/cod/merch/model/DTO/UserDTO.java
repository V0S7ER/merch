package com.cod.merch.model.DTO;

import com.cod.merch.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private Boolean sex;
    private Boolean admin;
    private Date birthday;
    private Long balance;
    private DepartmentDTO department;
    private String photo;

    public UserDTO(User user) {
        department = new DepartmentDTO(user.getDepartment());
        id = user.getId();
        email = user.getEmail();
        name = user.getName();
        surname = user.getSurname();
        sex = user.getSex();
        admin = user.getIsAdmin();
        balance = user.getBalance();
        birthday = user.getBirthday();
    }
}
