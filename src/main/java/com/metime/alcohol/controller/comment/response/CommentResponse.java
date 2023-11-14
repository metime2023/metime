package com.metime.alcohol.controller.comment.response;

import com.metime.alcohol.domain.Comment;

public record CommentResponse(
	String anonymousId,
	String content
) {
	public static CommentResponse from(Comment comment) {
		return new CommentResponse(comment.getAnonymousId(), comment.getContent());
	}
}
