package com.example.spring02.service;

import com.example.spring02.dto.CommentRequestDto;
import com.example.spring02.dto.ResponseDto;
import com.example.spring02.model.Comment;
import com.example.spring02.model.Member;
import com.example.spring02.model.Post;
import com.example.spring02.repository.CommentRepository;
import com.example.spring02.repository.MemberRepository;
import com.example.spring02.repository.PostRepository;
import com.example.spring02.security.SecurityUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional // 게시글 생성
    public ResponseDto<?> createComment(CommentRequestDto requestDto) {

        Comment comment = new Comment(requestDto);

        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                ()-> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 없습니다."));

        comment.setPost(post);
        comment.setMember(member);
        comment.setAuthor(member.getNickname());

        commentRepository.save(comment);

        return ResponseDto.success(comment);
    }
    @Transactional(readOnly = true) // 댓글 상세 조회
    public ResponseDto<?> getComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isEmpty()) {
            throw new IllegalArgumentException("댓글이 없습니다.");
        }

        return ResponseDto.success(optionalComment.get());
    }
    @Transactional(readOnly = true) // 댓글 전체 조회
    public ResponseDto<?> getAllComments() {
        return ResponseDto.success(commentRepository.findAllByOrderByModifiedAtDesc());
    }
    @Transactional // 댓글 수정
    public ResponseDto<?> updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 없습니다."));
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                ()-> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
        if (comment.getId() == member.getId()){
            comment.update(requestDto);
        }
        return ResponseDto.success(comment);
    }
    @Transactional // 댓글 삭제
    public ResponseDto<?> deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 없습니다."));
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                ()-> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
        if (comment.getId() == member.getId()){
            commentRepository.delete(comment);
        }
        return ResponseDto.success(true);
    }

}
