package com.example.spring02.service;

import com.example.spring02.dto.PostRequestDto;
import com.example.spring02.dto.ResponseDto;
import com.example.spring02.model.Member;
import com.example.spring02.model.Post;
import com.example.spring02.repository.PostRepository;
import com.example.spring02.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional // 게시글 생성
    public ResponseDto<?> createPost(PostRequestDto requestDto, Long userId) {

        Post post = new Post(requestDto, userId);

        postRepository.save(post);

        return ResponseDto.success(post);
    }
    @Transactional(readOnly = true) // 게시글 상세 조회
    public ResponseDto<?> getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NULL_POST_ID", "post id isn't exist");
        }

        return ResponseDto.success(optionalPost.get());
    }
    @Transactional(readOnly = true) // 게시글 전체 조회
    public ResponseDto<?> getAllPost() {
        return ResponseDto.success(postRepository.findAllByOrderByModifiedAtDesc());
    }
    @Transactional // 게시글 수정
    public ResponseDto<?> updatePost(Long id, PostRequestDto requestDto, Long userId) {
        Post post = postRepository.findById(id).orElse(null);

        if (post.getUserId().equals(userId)) {
            post.update(requestDto);
        }

        return ResponseDto.success(post);
    }
    @Transactional // 게시글 삭제
    public ResponseDto<?> deletePost(Long id, Long userId) {
        Post post = postRepository.findById(id).orElse(null);

        if (post.getUserId().equals(userId)) {
            postRepository.delete(post);
        }

        return ResponseDto.success(true);
    }
}
