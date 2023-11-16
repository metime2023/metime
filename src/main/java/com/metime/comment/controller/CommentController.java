package com.metime.comment.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metime.comment.controller.request.AlcoholCommentRequest;
import com.metime.comment.controller.response.CommentResponse;
import com.metime.comment.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/alcohol/{alcoholId}/comments")
	public CommentResponse createComment(@RequestBody @Valid AlcoholCommentRequest alcoholCommentRequest,
		@PathVariable long alcoholId) {
		return CommentResponse.from(commentService.create(alcoholId, alcoholCommentRequest.content()));
	}
}
