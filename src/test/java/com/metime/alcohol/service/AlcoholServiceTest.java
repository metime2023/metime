package com.metime.alcohol.service;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.dto.AlcoholDto;
import com.metime.alcohol.repository.AlcoholRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.metime.alcohol.fixture.alcohol.AlcoholFixture.ALCOHOL_FIXTURE;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
@SpringBootTest
class AlcoholServiceTest {

	private final AlcoholService alcoholService;
	private final AlcoholRepository alcoholRepository;

	private List<Alcohol> alcoholList = new ArrayList<>();

	@BeforeEach
	void setUp() {
		alcoholList.add(ALCOHOL_FIXTURE);
		alcoholRepository.saveAll(alcoholList);
	}

	@DisplayName("주류 리스트 조회에 성공한다")
	@Test
	void 주류_리스트_조회에_성공한다() {
		// given
		PagingCondition pagingCondition =
				new PagingCondition(0, 5, "recommend", 0, 5000);

		// when
		List<AlcoholDto> alcoholDtoList = alcoholService.getAlcoholPerPage(pagingCondition);

		// then
		assertThat(alcoholDtoList).usingRecursiveFieldByFieldElementComparator()
				.isEqualTo(AlcoholDto.listFrom(alcoholList));
	}

	@DisplayName("주류 상세 조회에 성공한다")
	@Test
	void 주류_상세_조회에_성공한다() {
		// given
		Alcohol alcohol = alcoholRepository.findAll().get(0);
		AlcoholDto expect = AlcoholDto.from(alcohol);

		// when
		AlcoholDto actual = alcoholService.alcoholDetail(alcohol.getId());

		// then
		assertThat(actual).usingRecursiveComparison()
				.isEqualTo(expect);
	}
}
