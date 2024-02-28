package com.metime.comment.service;

import com.metime.comment.dto.response.CommentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metime.alcohol.support.exception.DoesNotExistAlcoholException;
import com.metime.alcohol.domain.Alcohol;
import com.metime.comment.domain.Comment;
import com.metime.alcohol.repository.AlcoholRepository;
import com.metime.comment.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

	private final AlcoholRepository alcoholRepository;
	private final CommentRepository commentRepository;

	@Transactional(readOnly = true)
	public List<CommentResponse> findAllByAlcoholId(long alcoholId) {
		List<Comment> comments = commentRepository.findByAlcoholId(alcoholId);

		return CommentResponse.listFrom(comments);
	}

	public CommentResponse create(long alcoholId, String content) {
		Alcohol alcohol = alcoholRepository.findById(alcoholId).orElseThrow(DoesNotExistAlcoholException::new);
		Comment comment = commentRepository.save(Comment.create(alcohol, content));

		return CommentResponse.from(comment);
	}
}
