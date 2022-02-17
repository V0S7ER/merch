package com.cod.merch.model.DTO;

import com.cod.merch.model.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private List<ItemDTO> items;

    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
        description = category.getDescription();
        items = new ArrayList<>();
        for (var item : category.getItemList()) {
            items.add(new ItemDTO(item));
        }
    }
}
