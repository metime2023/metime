package com.metime.alcohol.service;

import com.metime.alcohol.domain.AlcoholName;
import com.metime.alcohol.domain.Category;
import com.metime.alcohol.domain.Keyword;
import com.metime.alcohol.dto.AlcoholDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AlcoholService {

    public List<AlcoholDto> getAlcoholsPerPage(long cursorNo, int displayPerPage, String sort, int minPrice,
            int maxPrice) {
        return List.of(AlcoholDto.of(AlcoholName.from("맥주"), List.of(Keyword.HONEY), Category.BEER, 0, 0));
    }

    public AlcoholDto alcoholDetail(long alcoholId) {
        return AlcoholDto.of(AlcoholName.from("맥주"), List.of(Keyword.HONEY), Category.BEER, 0, 0);
    }
}