package com.example.spring02.controller;

import com.example.spring02.dto.PostRequestDto;
import com.example.spring02.dto.ResponseDto;
import com.example.spring02.model.Member;
import com.example.spring02.security.UserDetailsImpl;
import com.example.spring02.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @PostMapping("/auth/post") //게시글 생성
    public ResponseDto<?> createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/post") // 게시글 조회
    public ResponseDto<?> getAllPosts() {
        return postService.getAllPost();
    }

    @GetMapping("/post/{id}") // 게시글 상세 조회
    public ResponseDto<?> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/auth/post/{id}") // 게시글 수정
    public ResponseDto<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {

        return postService.updatePost(id, postRequestDto);
    }

    @DeleteMapping("/auth/post/{id}") // 게시글 삭제
    public ResponseDto<?> deletePost(@PathVariable Long id) {

        return postService.deletePost(id);
    }
}
