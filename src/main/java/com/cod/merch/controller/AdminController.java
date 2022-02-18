package com.cod.merch.controller;

import com.cod.merch.model.DTO.request.AchievementRequest;
import com.cod.merch.model.DTO.request.ContestRequest;
import com.cod.merch.model.DTO.request.ItemRequest;
import com.cod.merch.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping(value = "/{id}")
    public synchronized ResponseEntity setAdmin(@PathVariable Long id, String admin_email, String admin_password) {
        boolean OK = adminService.setAdmin(id, admin_email, admin_password);
        return adminReturn(OK);
    }

    @PostMapping(value = "/achievement")
    public synchronized ResponseEntity createAchievement(AchievementRequest request) {
        boolean OK = adminService.createAchievement(request);
        return adminReturn(OK);
    }

    @PostMapping(value = "/contest")
    public synchronized ResponseEntity createContest(ContestRequest request) {
        boolean OK = adminService.createContest(request);
        return adminReturn(OK);
    }

    @PostMapping(value = "/item")
    public synchronized ResponseEntity createItem(ItemRequest request) {
        boolean OK = adminService.createItem(request);
        return OK ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/item/{id}")
    public synchronized ResponseEntity changeItem(@PathVariable Long id, ItemRequest request) {
        boolean OK = adminService.changeItem(id, request);
        return adminReturn(OK);
    }

    @PutMapping(value = "/contest/{id}")
    public synchronized ResponseEntity changeContest(@PathVariable Long id, ContestRequest request) {
        boolean OK = adminService.changeContest(id, request);
        return adminReturn(OK);
    }

    @PutMapping(value = "/achievement/{id}")
    public synchronized ResponseEntity changeAchievement(@PathVariable Long id, AchievementRequest request) {
        boolean OK = adminService.changeAchievement(id, request);
        return adminReturn(OK);
    }

    @DeleteMapping(value = "/achievement/{id}")
    public synchronized ResponseEntity deleteAchievement(@PathVariable Long id, String admin_email, String admin_password) {
        boolean OK = adminService.deleteAchievement(id, admin_email, admin_password);
        return adminReturn(OK);
    }

    @DeleteMapping(value = "/item/{id}")
    public synchronized ResponseEntity deleteItem(@PathVariable Long id, String admin_email, String admin_password) {
        boolean OK = adminService.deleteItem(id, admin_email, admin_password);
        return adminReturn(OK);
    }

    @DeleteMapping(value = "/contest/{id}")
    public synchronized ResponseEntity deleteContest(@PathVariable Long id, String admin_email, String admin_password) {
        boolean OK = adminService.deleteContest(id, admin_email, admin_password);
        return adminReturn(OK);
    }

    @PostMapping(value = "/contest/add_winner")
    public synchronized ResponseEntity addContestWinner(Long user_id, Long contest_id, String admin_email, String admin_password) {
        boolean OK = adminService.addContestWinner(user_id, contest_id, admin_email, admin_password);
        return adminReturn(OK);
    }

    @PostMapping(value = "/user/balance/{id}")
    public synchronized ResponseEntity setUserBalanceByUserId(@PathVariable Long id, String admin_email, String admin_password, Long balance) {
        boolean OK = adminService.setUserBalance(id, admin_email, admin_password, balance);
        return adminReturn(OK);
    }

    @PostMapping(value = "/user/achievement/{user_id}")
    public synchronized ResponseEntity addAchievementToUser(Long achievement_id, @PathVariable Long user_id, String admin_email, String admin_password) {
        boolean OK = adminService.setAchievementToUser(user_id, achievement_id, admin_email, admin_password);
        return adminReturn(OK);
    }

    @PostMapping(value = "/category/add_item/{category_id}")
    public synchronized ResponseEntity addItemToCategory(@PathVariable Long category_id, Long item_id, String admin_email, String admin_password) {
        boolean OK = adminService.addItemToCategory(item_id, category_id, admin_email, admin_password);
        return adminReturn(OK);
    }

    private ResponseEntity adminReturn(boolean OK) {
        return OK ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}