package com.cod.merch.model.DTO;

import com.cod.merch.model.Department;
import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;
    private String name;
    private String description;

    public DepartmentDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.description = department.getDescription();
    }
}
