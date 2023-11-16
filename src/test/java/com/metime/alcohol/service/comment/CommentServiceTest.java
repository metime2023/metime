package com.metime.alcohol.service.comment;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.Optional;

import com.metime.comment.service.CommentService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.metime.alcohol.common.exception.DoesNotExistAlcoholException;
import com.metime.comment.domain.Comment;
import com.metime.alcohol.fixture.alcohol.AlcoholFixture;
import com.metime.alcohol.repository.AlcoholRepository;
import com.metime.comment.repository.CommentRepository;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

	private static final long DEFAULT_ALCOHOL_ID = 1L;
	private static final String DEFAULT_ALCOHOL_CONTENT = "댓글내용1";

	@InjectMocks
	private CommentService sut;

	@Mock
	private AlcoholRepository alcoholRepository;

	@Mock
	private CommentRepository commentRepository;

	@Test
	void 알콜_댓글작성에_성공한다() {
		// given
		given(alcoholRepository.findById(DEFAULT_ALCOHOL_ID)).willReturn(Optional.of(AlcoholFixture.ALCOHOL_FIXTURE));

		// when
		sut.create(DEFAULT_ALCOHOL_ID, DEFAULT_ALCOHOL_CONTENT);

		// then
		then(commentRepository).should().save(any(Comment.class));
	}

	@Test
	void 알콜_댓글작성에_실패한다() {
		// given
		given(alcoholRepository.findById(DEFAULT_ALCOHOL_ID)).willReturn(Optional.empty());

		// when & then
		assertThrows(DoesNotExistAlcoholException.class, () -> sut.create(DEFAULT_ALCOHOL_ID, DEFAULT_ALCOHOL_CONTENT));
		then(commentRepository).shouldHaveNoInteractions();
	}
}
