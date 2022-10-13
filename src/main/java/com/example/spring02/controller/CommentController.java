package com.example.spring02.controller;

import com.example.spring02.dto.ResponseDto;
import com.example.spring02.security.UserDetailsImpl;
import com.example.spring02.model.Comment;
import com.example.spring02.dto.CommentRequestDto;
import com.example.spring02.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/auth/comment") //게시글 생성
    public ResponseDto<?> createComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }

    @GetMapping("/comment") // 댓글 조회
    public ResponseDto<?> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/comment/{id}") // 댓글 상세 조회
    public ResponseDto<?> getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    @PutMapping("/auth/comment/{id}") // 댓글 수정
    public ResponseDto<?> updateComment(@PathVariable Long id,
                                        @RequestBody CommentRequestDto commentRequestDto) {

        return commentService.updateComment(id, commentRequestDto);
    }

    @DeleteMapping("/auth/comment/{id}") // 댓글 삭제
    public ResponseDto<?> deleteComment(@PathVariable Long id) {

        return commentService.deleteComment(id);
    }
}
