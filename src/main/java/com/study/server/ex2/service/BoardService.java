package com.study.server.ex2.service;

import com.study.server.ex2.domain.Board;
import com.study.server.ex2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository repository;

    public Board findOneById(Integer boardId) {
        return repository.getOne(boardId);
    }

    public Board updateBoard(Integer boardId, Board updatedBoard) {
        // 게시글 ID에 맞는 데이터 조회
        Board savedBoard = repository.getOne(boardId);
        // 조회한 데이터를 수정
        savedBoard.setTitle(updatedBoard.getTitle());
        savedBoard.setPassword(updatedBoard.getPassword());
        savedBoard.setContent(updatedBoard.getContent());
        // 수정된 데이터를 저장
        savedBoard = repository.save(savedBoard);
        // 수정된 데이터를 응답
        return savedBoard;
    }
}
