package com.metime.comment.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CommentRequest(
	@NotEmpty
	String content
) {
}
