package com.buri.board.model.dto;

import com.buri.board.model.entity.Board;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class BoardResponseDto implements Serializable {
    private Long id;
    private String title;
    private String content;
    private String nickname;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.nickname = entity.getNickname();
    }
}
