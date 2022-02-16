package com.cod.merch.model.DTO;

import com.cod.merch.model.Item;
import lombok.Data;

@Data
public class ItemDTO {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private String photo;

    public ItemDTO(Item item) {
        id = item.getId();
        name = item.getName();
        price = item.getPrice();
        description = item.getDescription();
        photo = item.getPhoto();
    }
}
