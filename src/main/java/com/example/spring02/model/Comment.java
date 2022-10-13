package com.example.spring02.model;

import com.example.spring02.common.Timestamped;
import com.example.spring02.dto.CommentRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Setter
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

    public Comment(CommentRequestDto requestDto){
        this.content = requestDto.getContent();
    }
    public void update(CommentRequestDto requestDto){
        this.content = requestDto.getContent();
    }
}
