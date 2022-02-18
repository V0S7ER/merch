package com.cod.merch.controller;

import com.cod.merch.model.DTO.request.ChangeUserRequest;
import com.cod.merch.model.DTO.response.BooleanResponse;
import com.cod.merch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/basket/add/{item_id}")
    public synchronized ResponseEntity addItemToBasket(@PathVariable Long item_id, String email, String password) {
        boolean OK = userService.addItemToBasket(item_id, email, password);
        return responseUser(OK);
    }

    @PostMapping(value = "/wish/add/{item_id}")
    public synchronized ResponseEntity addItemToWish(@PathVariable Long item_id, String email, String password) {
        boolean OK = userService.addItemToWish(item_id, email, password);
        return responseUser(OK);
    }

    @DeleteMapping(value = "/wish/delete/{item_id}")
    public synchronized ResponseEntity deleteItemFromWish(@PathVariable Long item_id, String email, String password) {
        boolean OK = userService.deleteItemFromWish(item_id, email, password);
        return responseUser(OK);
    }

    @DeleteMapping(value = "/basket/delete/{item_id}")
    public synchronized ResponseEntity deleteItemFromBasket(@PathVariable Long item_id, String email, String password) {
        boolean OK = userService.deleteItemFromBasket(item_id, email, password);
        return responseUser(OK);
    }

    @PostMapping(value = "/basket/buy")
    public synchronized ResponseEntity buyAllBasket(String email, String password) {
        boolean OK = userService.buyBasket(email, password);
        return responseUser(OK);
    }

    @DeleteMapping(value = "/basket")
    public synchronized ResponseEntity clearBasket(String email, String password) {
        boolean OK = userService.clearBasket(email, password);
        return responseUser(OK);
    }

    @DeleteMapping(value = "/wish")
    public synchronized ResponseEntity clearWish(String email, String password) {
        boolean OK = userService.clearWish(email, password);
        return responseUser(OK);
    }

    @PostMapping(value = "/basket/buy/{item_id}")
    public synchronized ResponseEntity buyItem(@PathVariable Long item_id, String email, String password) {
        boolean OK = userService.buyItem(item_id, email, password);
        return responseUser(OK);
    }

    @PostMapping(value = "/")
    public synchronized ResponseEntity changeUser(ChangeUserRequest request) {
        boolean OK = userService.changeUser(request);
        return responseUser(OK);
    }

    @GetMapping(value = "/basket/contains/{id}")
    public synchronized ResponseEntity<BooleanResponse> containsItemInBasket(@PathVariable Long id, String email, String password) {
        BooleanResponse OK = userService.itemContainsInBasket(id, email, password);
        return ResponseEntity.ok(OK);
    }

    @GetMapping(value = "/wish/contains/{id}")
    public synchronized ResponseEntity<BooleanResponse> containsItemInWishList(@PathVariable Long id, String email, String password) {
        BooleanResponse OK = userService.itemContainsInWishList(id, email, password);
        return ResponseEntity.ok(OK);
    }

    private ResponseEntity responseUser(boolean OK) {
        return OK ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
