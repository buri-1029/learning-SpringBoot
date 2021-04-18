package com.test.board.model.dto;

import com.test.board.model.entity.Board;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class BoardListResponseDto implements Serializable {
    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime modifiedDate;

    public BoardListResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.nickname = entity.getNickname();
        this.modifiedDate = entity.getModifiedDate();
    }
}
