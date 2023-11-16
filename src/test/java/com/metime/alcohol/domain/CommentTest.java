package com.metime.alcohol.domain;

import static com.metime.alcohol.fixture.alcohol.AlcoholFixture.ALCOHOL_FIXTURE;
import static org.assertj.core.api.Assertions.assertThat;

import com.metime.comment.domain.Comment;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CommentTest {

	@Test
	void 댓글_생성시_익명_ID_값과_작성날짜가_생성된다() {
		// given
		String content = "댓글내용1";

		// when
		Comment actual = Comment.create(ALCOHOL_FIXTURE, content);

		// then
		assertThat(actual.getAnonymousId()).isNotNull();
		assertThat(actual.getCreatedAt()).isNotNull();
	}
}
