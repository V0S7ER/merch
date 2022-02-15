package com.cod.merch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class DefaultController {

    @RequestMapping("/")
    String start() {
        return String.format("%d", new Random().nextInt(100));
    }

}
