package com.study.server.ex2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/game")
public class GameController {

    @GetMapping({"", "/index"})
    public String index() { return "game/index"; }

    @PostMapping("")
    public ModelAndView process(@RequestParam("input") int inputValue) {
        String user = "";
        String computer = "";
        String result = "";

        int computerValue = (int) ((Math.random() * 3) / 1 + 1);

        // User
        switch (inputValue) {
            case 1:
                user = "가위";
                break;
            case 2:
                user = "바위";
                break;
            case 3:
                user = "보";
                break;
        }
        // Computer
        switch (computerValue) {
            case 1:
                computer = "가위";
                break;
            case 2:
                computer = "바위";
                break;
            case 3:
                computer = "보";
                break;
        }

        // 게임 결과 판단
        if (inputValue == computerValue) {
            result = "비김";
        } else if ((computerValue == 1 && inputValue == 2) ||
                (computerValue == 2 && inputValue == 3) ||
                (computerValue == 3 && inputValue == 1)) {
            result = "승리";
        } else {
            result = "패배";
        }

        // 응답 생성
        ModelAndView response = new ModelAndView("game/result");
        response.getModel().put("user", user);
        response.getModel().put("computer", computer);
        response.getModel().put("result", result);

        return response;
    }
}
