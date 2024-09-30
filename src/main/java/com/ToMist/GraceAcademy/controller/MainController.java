package com.ToMist.GraceAcademy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String main(){
        return "MainPage";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "LoginPage";
    }

}
