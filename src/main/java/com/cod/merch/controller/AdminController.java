package com.cod.merch.controller;

import com.cod.merch.model.DTO.request.AchievementRequest;
import com.cod.merch.model.DTO.request.ContestRequest;
import com.cod.merch.model.DTO.request.ItemRequest;
import com.cod.merch.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final AdminService adminService;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public synchronized ResponseEntity setAdmin(@PathVariable Long id, String admin_email, String admin_password) {
        boolean OK = adminService.setAdmin(id, admin_email, admin_password);
        return adminReturn(OK);
    }

    @RequestMapping(value = "/achievement", method = RequestMethod.POST)
    public synchronized ResponseEntity createAchievement(AchievementRequest request) {
        boolean OK = adminService.createAchievement(request);
        return adminReturn(OK);
    }

    @RequestMapping(value =  "/contest", method = RequestMethod.POST)
    public synchronized ResponseEntity createContest(ContestRequest request) {
        boolean OK = adminService.createContest(request);
        return adminReturn(OK);
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public synchronized ResponseEntity createItem(ItemRequest request) {
        boolean OK = adminService.createItem(request);
        return OK ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    public synchronized ResponseEntity changeItem(@PathVariable Long id, ItemRequest request) {
        boolean OK = adminService.changeItem(id, request);
        return adminReturn(OK);
    }

    @RequestMapping(value = "/contest/{id}", method = RequestMethod.PUT)
    public synchronized ResponseEntity changeContest(@PathVariable Long id, ContestRequest request) {
        boolean OK = adminService.changeContest(id, request);
        return adminReturn(OK);
    }

    @RequestMapping(value = "/achievement/{id}", method = RequestMethod.PUT)
    public synchronized ResponseEntity changeAchievement(@PathVariable Long id, AchievementRequest request) {
        boolean OK = adminService.changeAchievement(id, request);
        return adminReturn(OK);
    }

    @RequestMapping(value = "/achievement/{id}", method = RequestMethod.DELETE)
    public synchronized ResponseEntity deleteAchievement(@PathVariable Long id, String admin_email, String admin_password) {
        boolean OK = adminService.deleteAchievement(id, admin_email, admin_password);
        return adminReturn(OK);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public synchronized ResponseEntity deleteItem(@PathVariable Long id, String admin_email, String admin_password) {
        boolean OK = adminService.deleteItem(id, admin_email, admin_password);
        return adminReturn(OK);
    }

    @RequestMapping(value = "/contest/{id}", method = RequestMethod.DELETE)
    public synchronized ResponseEntity deleteContest(@PathVariable Long id, String admin_email, String admin_password) {
        boolean OK = adminService.deleteContest(id, admin_email, admin_password);
        return adminReturn(OK);
    }

    @RequestMapping(value = "/contest/add_winner", method = RequestMethod.POST)
    public synchronized ResponseEntity addContestWinner(Long user_id, Long contest_id, String admin_email, String admin_password) {
        boolean OK = adminService.addContestWinner(user_id, contest_id, admin_email, admin_password);
        return adminReturn(OK);
    }

    private ResponseEntity adminReturn(boolean OK) {
        return OK ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}