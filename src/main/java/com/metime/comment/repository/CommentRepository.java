package com.metime.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metime.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
