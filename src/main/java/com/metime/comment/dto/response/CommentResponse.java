package com.metime.comment.dto.response;

import com.metime.comment.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

public record CommentResponse(
		Long id,
		String content
) {
	public static CommentResponse from(Comment comment) {
		return new CommentResponse(comment.getId(), comment.getContent());
	}

	public static List<CommentResponse> listFrom(List<Comment> comments) {
		return comments.stream()
				.map(CommentResponse::from)
				.collect(Collectors.toList());
	}
}
