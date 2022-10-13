package com.example.spring02.service;

import com.example.spring02.dto.PostRequestDto;
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

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional // 게시글 생성
    public ResponseDto<?> createPost(PostRequestDto requestDto) {

        Post post = new Post(requestDto);

        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                ()-> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
        post.setMember(member);
        post.setAuthor(member.getNickname());

        postRepository.save(post);

        return ResponseDto.success(post);
    }
    @Transactional(readOnly = true) // 게시글 상세 조회
    public ResponseDto<?> getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 없습니다."));
        List<Comment> comment =commentRepository.findAllByPostId(id);
        post.setComments(comment);

        return ResponseDto.success(post);
    }
    @Transactional(readOnly = true) // 게시글 전체 조회
    public ResponseDto<?> getAllPost() {
        return ResponseDto.success(postRepository.findAllByOrderByModifiedAtDesc());
    }
    @Transactional // 게시글 수정
    public ResponseDto<?> updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 없습니다."));
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                ()-> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
        if (post.getMember().getId() == member.getId()){
            post.update(requestDto);
        }else {
            throw new IllegalArgumentException("게시글 작성자가 아닙니다.");
        }

        return ResponseDto.success(post);
    }
    @Transactional // 게시글 삭제
    public ResponseDto<?> deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 없습니다."));
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
                ()-> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
        if (post.getMember().getId() == member.getId()){
            postRepository.delete(post);
        }else {
            throw new IllegalArgumentException("게시글 작성자가 아닙니다.");
        }

        return ResponseDto.success("delete success");
    }
}
