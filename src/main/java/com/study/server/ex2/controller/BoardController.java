package com.study.server.ex2.controller;

import com.study.server.ex2.domain.Board;
import com.study.server.ex2.repository.BoardRepository;
import com.study.server.ex2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardRepository repository;
    @Autowired
    private BoardService service;

    @GetMapping("")
    public ModelAndView getHomeForModel() {
        List<Board> boardList = repository.findAll();

        ModelAndView result = new ModelAndView("board/index");
        result.addObject(boardList);

        return result;
    }

    // Redirect를 통해 다른 페이지로 안보내면 문제점
    // > 새로고침하면 데이터가 계속 저장될 수 있음
    // 설명 필요
    /*@PostMapping("/params")
    public String postBoardWithParams(@RequestParam("title") String title,
                            @RequestParam("password") String password,
                            @RequestParam("content") String content) {
        // 각 파라미터들을 1개의 데이터로 묶음
        // 묶은 데이터를 repository를 이용하여 저장
        // 저장된 게시글을 볼 수 있도록 페이지 이동

        return "";
    }*/

    @PostMapping("")
    public String postBoardWithParams(@ModelAttribute Board board) {
        Board savedBoard = repository.save(board);
        // Redirect item page with board number

        String redirectUrl = "redirect:/board/" + savedBoard.getId();

        return redirectUrl;
    }

    @GetMapping("/{id}")
    public ModelAndView getBoardItem(@PathVariable("id") Integer boardId) {
        // Get item of boards
        Board board = repository.getOne(boardId);

        ModelAndView result = new ModelAndView("board/item");
        result.getModel().put("boardItem", board);
        return result;
    }

    @GetMapping("/{id}/update")
    public ModelAndView getUpdatePage(@PathVariable("id") Integer boardId) {
        Board savedBoard = service.findOneById(boardId);

        ModelAndView response = new ModelAndView("board/update");
        response.getModel().put("board", savedBoard);

        return response;
    }

    @PostMapping("/{id}/update")
    public String postUpdateBoard(@PathVariable("id") Integer boardId,
                                        @ModelAttribute Board updatedBoard) {
        service.updateBoard(boardId, updatedBoard);

        // 방법1) 수정된 데이터를 새로 출력해서 응답하는 방법
        /*ModelAndView response = new ModelAndView("board/index");
        response.getModel().put("board", resultBoard);
        return response;*/
        // 방법2) 기존에 게시글을 조회하는 페이지로 이동하는 방법
        return "redirect:/board/" + boardId;
    }

}
