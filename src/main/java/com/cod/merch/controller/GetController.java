package com.cod.merch.controller;

import com.cod.merch.model.DTO.*;
import com.cod.merch.service.GetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/api/get")
@RequiredArgsConstructor
public class GetController {

    private final GetService getService;

    private final ResponseEntity BAD_REQUEST = new ResponseEntity(HttpStatus.BAD_REQUEST);

    @GetMapping(value = "/item/{id}")
    public synchronized ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) {
        ItemDTO item = getService.getItemById(id);
        return item == null ? BAD_REQUEST : ResponseEntity.ok(item);
    }

    @GetMapping(value = "/category/all")
    public synchronized ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryList = getService.getAllCategories();
        return categoryList == null ? BAD_REQUEST : ResponseEntity.ok(categoryList);
    }

    @GetMapping(value = "/category/{id}")
    public synchronized ResponseEntity<List<ItemDTO>> getAllItemsByCategoryId(@PathVariable Long id) {
        List<ItemDTO> itemList = getService.getItemsByCategoryId(id);
        return itemList == null ? BAD_REQUEST : ResponseEntity.ok(itemList);
    }

    @GetMapping(value = "/user/{id}")
    public synchronized ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = getService.getUserById(id);
        return user == null ? BAD_REQUEST : ResponseEntity.ok(user);
    }

    @GetMapping(value = "/user/basket/{id}")
    public synchronized ResponseEntity<List<ItemDTO>> getBasketByUserId(@PathVariable Long id) {
        List<ItemDTO> itemList = getService.getBasketByUserId(id);
        return itemList == null ? BAD_REQUEST : ResponseEntity.ok(itemList);
    }

    @GetMapping(value = "/user/wish/{id}")
    public synchronized ResponseEntity<List<ItemDTO>> getWishListByUserId(@PathVariable Long id) {
        List<ItemDTO> itemList = getService.getWishListByUserId(id);
        return itemList == null ? BAD_REQUEST : ResponseEntity.ok(itemList);
    }

    @GetMapping(value = "/contest/users/{id}")
    public synchronized ResponseEntity<List<UserDTO>> getWinnersByContestId(@PathVariable Long id) {
        List<UserDTO> userList = getService.getWinnersByContestId(id);
        return userList == null ? BAD_REQUEST : ResponseEntity.ok(userList);
    }

    @GetMapping(value = "/contest/all")
    public synchronized ResponseEntity<List<ContestDTO>> getAllContests() {
        List<ContestDTO> contestList = getService.getAllContests();
        return contestList == null ? BAD_REQUEST : ResponseEntity.ok(contestList);
    }

    @GetMapping(value = "/contest/{id}")
    public synchronized ResponseEntity<ContestDTO> getContestById(@PathVariable Long id) {
        ContestDTO contest = getService.getContestById(id);
        return contest == null ? BAD_REQUEST : ResponseEntity.ok(contest);
    }

    @GetMapping(value = "/department/all_users/{id}")
    public synchronized ResponseEntity<List<UserDTO>> getAllUsersByDepartmentId(@PathVariable Long id) {
        List<UserDTO> ans = getService.getUsersByDepartmentId(id);
        return ans == null ? BAD_REQUEST : ResponseEntity.ok(ans);
    }

    @GetMapping(value = "/user/get_achievements/{id}")
    public synchronized ResponseEntity<List<AchievementDTO>> getAllAchievementsByUserId(@PathVariable Long id) {
        List<AchievementDTO> ans = getService.getAchievementsByUserId(id);
        return ans == null ? BAD_REQUEST : ResponseEntity.ok(ans);
    }

    @GetMapping(value = "/achievement/all")
    public synchronized ResponseEntity<List<AchievementDTO>> getAllAchievements() {
        List<AchievementDTO> ans = getService.getAllAchievements();
        return ans == null ? BAD_REQUEST : ResponseEntity.ok(ans);
    }

    @GetMapping(value = "/achievement/locked/{id}")
    public synchronized ResponseEntity<List<AchievementDTO>> getLockedAchievementsByUserId(@PathVariable Long id) {
        List<AchievementDTO> ans = getService.getLockedAchievementsByUserId(id);
        return ans == null ? BAD_REQUEST : ResponseEntity.ok(ans);
    }

    @GetMapping(value = "/achievement/users/{id}")
    public synchronized ResponseEntity<List<UserDTO>> getUsersByAchievementId(@PathVariable Long id) {
        List<UserDTO> ans = getService.getUsersByAchievementId(id);
        return ans == null ? BAD_REQUEST : ResponseEntity.ok(ans);
    }

    @GetMapping(value = "/item/category/{id}")
    public synchronized ResponseEntity<List<CategoryDTO>> getCategoriesByItemId(@PathVariable Long id) {
        List<CategoryDTO> ans = getService.getCategoriesByItemId(id);
        return ans == null ? BAD_REQUEST : ResponseEntity.ok(ans);
    }
}
