package com.metime.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import com.metime.comment.dto.request.CommentRequest;
import com.metime.comment.dto.response.CommentResponse;
import com.metime.comment.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Tag(name = "댓글 API", description = "댓글 CRUD API")
@RequiredArgsConstructor
@RestController
public class CommentController {

	private final CommentService commentService;

	@Operation(summary = "댓글 리스트 조회")
	@GetMapping("/alcohol/{alcoholId}/comments")
	public List<CommentResponse> findAllCommentsByAlcoholId(@PathVariable long alcoholId) {
		return commentService.findAllByAlcoholId(alcoholId);
	}

	@Operation(summary = "댓글 등록")
	@PostMapping("/alcohol/{alcoholId}/comments")
	public CommentResponse createComment(
			@RequestBody @Valid CommentRequest commentRequest,
			@PathVariable long alcoholId) {
		return commentService.create(alcoholId, commentRequest.content());
	}
}
