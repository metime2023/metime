package com.metime.alcohol.service.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metime.alcohol.common.exception.DoesNotExistAlcoholException;
import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.domain.Comment;
import com.metime.alcohol.repository.AlcoholRepository;
import com.metime.alcohol.repository.comment.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

	private final AlcoholRepository alcoholRepository;
	private final CommentRepository commentRepository;

	public Comment create(long alcoholId, String content) {
		Alcohol alcohol = alcoholRepository.findById(alcoholId).orElseThrow(DoesNotExistAlcoholException::new);
		return commentRepository.save(Comment.create(alcohol, content));
	}
}
