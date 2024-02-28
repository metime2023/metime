package com.metime.comment.controller;

import org.springframework.web.bind.annotation.*;

import com.metime.comment.dto.request.CommentRequest;
import com.metime.comment.dto.response.CommentResponse;
import com.metime.comment.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/alcohol/{alcoholId}/comments")
	public List<CommentResponse> findAllCommentsByAlcoholId(@PathVariable long alcoholId) {
		return commentService.findAllByAlcoholId(alcoholId);
	}

	@PostMapping("/alcohol/{alcoholId}/comments")
	public CommentResponse createComment(
			@RequestBody @Valid CommentRequest commentRequest,
			@PathVariable long alcoholId) {
		return commentService.create(alcoholId, commentRequest.content());
	}
}
