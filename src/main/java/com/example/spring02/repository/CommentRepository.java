package com.example.spring02.repository;

import com.example.spring02.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>  {
    List<Comment> findAllByPostId(Long postId);
    List<Comment> findAllByOrderByModifiedAtDesc();//최근 작성 시간 게시글이 위로
}
