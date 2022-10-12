package com.example.spring02.model;

import com.example.spring02.common.Timestamped;
import com.example.spring02.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String author;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    private Long userId;

    public Comment(CommentRequestDto requestDto, Long userId){
        this.userId = userId;
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
    }
    public void update(CommentRequestDto requestDto){
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
    }
}
