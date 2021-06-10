package com.buri.board.model.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Get, Set, Builder, Constructor 관련 코드 자동 생성
@Entity // 데이터베이스의 테이블과 1:1 매핑되는 객체
@Table(name = "board") // 테이블 명을 "board"로 지정하기 위하여 Table annotation 사용
public class Board extends BaseTime {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 500, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Builder
    public Board(String title, String content, String nickname) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }
}
