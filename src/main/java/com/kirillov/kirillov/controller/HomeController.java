package com.kirillov.kirillov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/repairs"; // Перенаправляем на страницу списка ремонта
    }
}
