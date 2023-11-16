package com.metime.alcohol.service;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.dto.AlcoholDto;
import com.metime.alcohol.repository.AlcoholRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.metime.global.fixture.alcohol.AlcoholFixture.ALCOHOL_FIXTURE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RequiredArgsConstructor
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
class AlcoholServiceTest {

	private static final long DEFAULT_ALCOHOL_ID = 1L;

	@InjectMocks
	private AlcoholService sut;

	@Mock
	private AlcoholRepository alcoholRepository;

	@Test
	void 주류_리스트_조회에_성공한다() {
		// given
		List<Alcohol> expect = List.of(ALCOHOL_FIXTURE);
		PagingCondition pagingCondition =
				new PagingCondition(0, 5, "recommend", 0, 5000);
		given(alcoholRepository.getAlcoholPerPage(pagingCondition.toDto())).willReturn(expect);

		// when
		List<AlcoholDto> actual = sut.getAlcoholPerPage(pagingCondition.toDto());

		// then
		assertThat(actual).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(AlcoholDto.listFrom(expect));
	}

	@Test
	void 주류_상세_조회에_성공한다() {
		// given
		Alcohol expect = ALCOHOL_FIXTURE;
		given(alcoholRepository.findById(DEFAULT_ALCOHOL_ID)).willReturn(Optional.of(ALCOHOL_FIXTURE));

		// when
		AlcoholDto actual = sut.alcoholDetail(DEFAULT_ALCOHOL_ID);

		// then
		assertThat(actual).usingRecursiveComparison()
				.isEqualTo(AlcoholDto.from(expect));
	}
}
