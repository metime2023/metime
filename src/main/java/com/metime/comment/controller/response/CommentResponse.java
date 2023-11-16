package com.metime.comment.controller.response;

import com.metime.comment.domain.Comment;

public record CommentResponse(
	String anonymousId,
	String content
) {
	public static CommentResponse from(Comment comment) {
		return new CommentResponse(comment.getAnonymousId(), comment.getContent());
	}
}
