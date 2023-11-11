package com.metime.alcohol.controller.comment;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metime.alcohol.controller.comment.request.AlcoholCommentRequest;
import com.metime.alcohol.service.comment.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/alcohol/{alcoholId}/comments")
	public void createComment(@RequestBody @Valid AlcoholCommentRequest alcoholCommentRequest,
		@PathVariable long alcoholId) {
		commentService.create(alcoholId, alcoholCommentRequest.content());
	}
}
