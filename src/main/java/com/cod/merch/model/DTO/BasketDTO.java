package com.cod.merch.model.DTO;

import com.cod.merch.model.Item;
import com.cod.merch.model.User;
import lombok.Data;

import java.util.List;

@Data
public class BasketDTO {
    private List<Item> basket;

    public BasketDTO(User user) {
        basket = user.getBasket();
    }
}
