package com.cod.merch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
public class DefaultController {

    @RequestMapping("/")
    String start() {
        return String.format("%d", new Random().nextInt(100));
    }

}
