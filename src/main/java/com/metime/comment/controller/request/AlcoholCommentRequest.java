package com.metime.comment.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record AlcoholCommentRequest(
	@NotEmpty
	String content
) {
}
