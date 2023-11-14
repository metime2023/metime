package com.metime.alcohol.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metime.alcohol.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
