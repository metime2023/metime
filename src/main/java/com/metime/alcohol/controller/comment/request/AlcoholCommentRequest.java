package com.metime.alcohol.controller.comment.request;

import jakarta.validation.constraints.NotEmpty;

public record AlcoholCommentRequest(
	@NotEmpty
	String content
) {
}
