package com.example.spring02.model;

import com.example.spring02.common.Timestamped;
import com.example.spring02.dto.PostRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private Long userId;

    public Post(PostRequestDto postRequestDto, Long userId) {
        this.userId = userId;
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.author = postRequestDto.getAuthor();
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.author = postRequestDto.getAuthor();
    }

}