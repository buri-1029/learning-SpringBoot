package com.test.board.model.entity;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "board")
public class Board extends BaseTime{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 500, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "nickname",nullable = false)
    private String nickname;

    @Builder
    public Board(String title, String content, String nickname) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }
}
