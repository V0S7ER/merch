package com.cod.merch.model.DTO;

import com.cod.merch.model.Item;
import com.cod.merch.model.User;

import java.util.List;

public class BasketDTO {
    List<Item> basket;

    public BasketDTO(User user) {
        basket = user.getBasket();
    }
}
