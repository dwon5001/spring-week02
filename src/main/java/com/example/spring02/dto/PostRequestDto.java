package com.example.spring02.dto;

import com.example.spring02.model.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
}

