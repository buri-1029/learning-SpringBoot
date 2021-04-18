package com.test.board.service;

import com.test.board.model.dto.BoardListResponseDto;
import com.test.board.model.dto.BoardRequestDto;
import com.test.board.model.dto.BoardResponseDto;
import com.test.board.model.entity.Board;
import com.test.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    // 게시물 등록
    @Transactional
    public Long save(BoardRequestDto boardRequestDto) {
        return boardRepository.save(boardRequestDto.toEntity()).getId();
    }

    // 게시물 수정
    @Transactional
    public Long update(Long id, BoardRequestDto boardRequestDto) {
        Board board = getOne(id);

        board.setTitle(boardRequestDto.getTitle());
        board.setNickname(boardRequestDto.getNickname());
        board.setContent(boardRequestDto.getContent());
        boardRepository.save(board);

        return id;
    }

    // 게시물 상세정보 조회
    public BoardResponseDto findById(Long id) {
        Board board = getOne(id);

        return new BoardResponseDto(board);
    }

    // 게시물 리스트
    @Transactional
    public List<BoardListResponseDto> findAllDesc() {
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 게시물 삭제
    @Transactional
    public void delete(Long id) {
        Board posts = getOne(id);
        boardRepository.delete(posts);
    }

    // 각 함수마다 게시물 존재 확인
    private Board getOne(Long id) throws IllegalArgumentException {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시물이 없습니다.[id=" + id + "]"));
    }
}
