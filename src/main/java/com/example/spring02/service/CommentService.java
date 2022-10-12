package com.example.spring02.service;

import com.example.spring02.dto.CommentRequestDto;
import com.example.spring02.dto.ResponseDto;
import com.example.spring02.model.Comment;
import com.example.spring02.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional // 게시글 생성
    public ResponseDto<?> createComment(CommentRequestDto requestDto, Long userId) {

        Comment comment = new Comment(requestDto, userId);

        commentRepository.save(comment);

        return ResponseDto.success(comment);
    }
    @Transactional(readOnly = true) // 댓글 상세 조회
    public ResponseDto<?> getComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isEmpty()) {
            return ResponseDto.fail("NULL_POST_ID", "post id isn't exist");
        }

        return ResponseDto.success(optionalComment.get());
    }
    @Transactional(readOnly = true) // 댓글 전체 조회
    public ResponseDto<?> getAllComments() {
        return ResponseDto.success(commentRepository.findAllByOrderByModifiedAtDesc());
    }
    @Transactional // 댓글 수정
    public ResponseDto<?> updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment.getUserId().equals(id)) {
            comment.update(requestDto);
        }

        return ResponseDto.success(comment);
    }
    @Transactional // 댓글 삭제
    public ResponseDto<?> deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment.getUserId().equals(id)) {
            commentRepository.delete(comment);
        }

        return ResponseDto.success(true);
    }

}
