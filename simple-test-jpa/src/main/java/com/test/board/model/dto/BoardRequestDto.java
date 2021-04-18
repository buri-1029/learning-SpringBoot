package com.test.board.model.dto;

import com.test.board.model.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;
    private String nickname;

    @Builder
    public BoardRequestDto(String title, String content, String nickname) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .nickname(nickname)
                .build();
    }
}
