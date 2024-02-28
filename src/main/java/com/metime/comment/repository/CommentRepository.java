package com.metime.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metime.comment.domain.Comment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query("SELECT c FROM Comment c WHERE c.alcohol.id = :alcoholId")
	List<Comment> findByAlcoholId(long alcoholId);
}
