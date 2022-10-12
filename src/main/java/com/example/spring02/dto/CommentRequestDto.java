package com.example.spring02.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String author;
    private String content;
}
