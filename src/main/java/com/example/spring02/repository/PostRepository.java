package com.example.spring02.repository;

import com.example.spring02.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();//최근 작성 시간 게시글이 위로
}
