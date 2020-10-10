package com.study.server.ex2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/", "/home"})
public class HomeController {

    @GetMapping("")
    public String getHome() {
        return "index";
    }

}
