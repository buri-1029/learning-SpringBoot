package com.test.board.controller;

import com.test.board.model.dto.BoardListResponseDto;
import com.test.board.model.dto.BoardRequestDto;
import com.test.board.model.dto.BoardResponseDto;
import com.test.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @ApiOperation(value = "게시물 등록")
    @PostMapping
    public Long save(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.save(boardRequestDto);
    }

    @ApiOperation(value = "게시물 수정")
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.update(id, boardRequestDto);
    }

    @ApiOperation(value = "해당 게시물 조회")
    @GetMapping("/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @ApiOperation(value = "게시물 전체 조회")
    @GetMapping
    public List<BoardListResponseDto> findAll() {
        return boardService.findAllDesc();
    }

    @ApiOperation(value = "해당 게시물 삭제")
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        boardService.delete(id);
        return id;
    }
}
