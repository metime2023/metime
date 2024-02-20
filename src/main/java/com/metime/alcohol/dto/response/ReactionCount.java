package com.metime.alcohol.dto.response;

public record ReactionCount(int likeCount, int commentCount) {

	public static ReactionCount of(int likeCount, int commentCount) {
		return new ReactionCount(likeCount, commentCount);
	}
}
