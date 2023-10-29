package com.metime.alcohol.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.domain.AlcoholName;
import com.metime.alcohol.domain.Category;
import com.metime.alcohol.domain.distributor.Distributor;
import com.metime.alcohol.domain.keyword.Keyword;
import com.metime.alcohol.dto.AlcoholDto;
import com.metime.alcohol.service.AlcoholService;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AlcoholRestController.class)
class AlcoholRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlcoholService alcoholService;

    private Alcohol alcohol;

    @BeforeEach
    void setUp() {
        alcohol = Alcohol.builder()
                .name(AlcoholName.from("맥주"))
                .category(Category.BEER)
                .build();
        Distributor distributor = new Distributor("편의점");
        alcohol.allocate(distributor);
        Keyword keyword = new Keyword("꿀");
        alcohol.addKeyword(keyword);
    }

    @DisplayName("주류 리스트 조회성공")
    @Test
    void 주류리스트_조회_성공() throws Exception {
        // given
        List<AlcoholDto> alcoholList = AlcoholDto.listFrom(List.of(alcohol));
        PagingCondition pagingCondition = new PagingCondition(2, 5, "recommend", 0, 1000);
        given(alcoholService.getAlcoholPerPage(eq(pagingCondition))).willReturn(alcoholList);

        // when
        mockMvc.perform(get("/alcohols?cursorNo=2&displayPerPage=5&sort=recommend&minPrice=0&maxPrice=1000"))
                .andExpect(status().isOk())
                .andDo(print());

        // then
    }

    @DisplayName("주류 리스트 조회실패 : 존재하지않는 정렬조건 입력")
    @Test
    void 주류리스트_조회_실패_존재하지않는_정렬조건() throws Exception {
        // given

        // when
        mockMvc.perform(get("/alcohols?cursorNo=0&displayPerPage=3&sort=nothing")
                )
                .andExpect(status().isBadRequest())
                .andDo(print());

        // then
    }

    @DisplayName("주류 리스트 조회실패 : 요청값 검증통과 실패")
    @Test
    void 주류리스트_조회_실패_요청값_검증통과실패() throws Exception {
        // given

        // when
        mockMvc.perform(get("/alcohols?cursorNo=0&sort=recommend")
                )
                .andExpect(status().isBadRequest())
                .andDo(print());

        // then
    }

    @DisplayName("주류 리스트 상세조회 성공")
    @Test
    void 주류리스트_상세조회_성공() throws Exception {
        // given
        long alcoholId = 1;
        AlcoholDto beer = AlcoholDto.from(alcohol);
        given(alcoholService.alcoholDetail(eq(alcoholId))).willReturn(beer);

        // when
        mockMvc.perform(get("/alcohols/1")
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
    }
}
