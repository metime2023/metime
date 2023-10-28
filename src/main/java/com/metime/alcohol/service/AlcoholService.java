package com.metime.alcohol.service;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.domain.AlcoholName;
import com.metime.alcohol.domain.Category;
import com.metime.alcohol.dto.AlcoholDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AlcoholService {

    public List<AlcoholDto> getAlcoholsPerPage(PagingCondition pagingCondition) {
        Alcohol alcohol = Alcohol.builder()
                .name(AlcoholName.from("맥주"))
                .category(Category.BEER)
                .build();
        return AlcoholDto.listFrom(List.of(alcohol));
    }

    public AlcoholDto alcoholDetail(long alcoholId) {
        Alcohol alcohol = Alcohol.builder().build();
        return AlcoholDto.from(alcohol);
    }
}
