package com.metime.alcohol.controller;

import static com.metime.global.fixture.alcohol.AlcoholFixture.ALCOHOL_FIXTURE;
import static com.metime.global.fixture.distributor.DistributorFixture.CONVENIENCE_STORE;
import static com.metime.global.fixture.distributor.DistributorFixture.SUPERMARKET;
import static com.metime.global.fixture.keyword.KeywordFixture.DILUTED_SOJU;
import static com.metime.global.fixture.keyword.KeywordFixture.DISTILLED_SOJU;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.dto.AlcoholDto;
import com.metime.alcohol.service.AlcoholService;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(AlcoholRestController.class)
class AlcoholRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AlcoholService alcoholService;

	private static Alcohol alcohol;
	private static final String ENDPOINT = "/alcohol";

	@BeforeAll
	static void beforeAll() {
		alcohol = ALCOHOL_FIXTURE;
		alcohol.allocate(CONVENIENCE_STORE);
		alcohol.allocate(SUPERMARKET);
		alcohol.addKeyword(DILUTED_SOJU);
		alcohol.addKeyword(DISTILLED_SOJU);
	}

	@DisplayName("주류 리스트 조회성공")
	@Test
	void 주류리스트_조회_성공() throws Exception {
		// given
		List<AlcoholDto> alcoholList = AlcoholDto.listFrom(List.of(alcohol));
		PagingCondition pagingCondition =
				new PagingCondition(2, 5, "recommend", 0, 1000);
		given(alcoholService.getAlcoholPerPage(eq(pagingCondition.toDto()))).willReturn(alcoholList);

		// when
		ResultActions resultActions = mockMvc.perform(get(ENDPOINT + "?cursorNo=2&displayPerPage=5&sort=recommend&minPrice=0&maxPrice=1000"));

		// then
		resultActions.andExpect(status().isOk())
				.andDo(print());
	}

	@DisplayName("주류 리스트 조회실패 : 존재하지않는 정렬조건 입력")
	@Test
	void 주류리스트_조회_실패_존재하지않는_정렬조건() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(get(ENDPOINT + "?cursorNo=0&displayPerPage=3&sort=nothing"));

		// then
		resultActions.andExpect(status().isBadRequest())
				.andDo(print());
	}

	@DisplayName("주류 리스트 조회실패 : 요청값 검증통과 실패")
	@Test
	void 주류리스트_조회_실패_요청값_검증통과실패() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(get(ENDPOINT + "?cursorNo=0&sort=recommend"));

		// then
		resultActions.andExpect(status().isBadRequest())
				.andDo(print());
	}

	@DisplayName("주류 리스트 상세조회 성공")
	@Test
	void 주류리스트_상세조회_성공() throws Exception {
		// given
		long alcoholId = 1;
		AlcoholDto beer = AlcoholDto.from(alcohol);
		given(alcoholService.alcoholDetail(eq(alcoholId))).willReturn(beer);

		// when
		ResultActions resultActions = mockMvc.perform(get(ENDPOINT + "/1"));

		// then
		resultActions.andExpect(status().isOk())
				.andDo(print());
	}
}
