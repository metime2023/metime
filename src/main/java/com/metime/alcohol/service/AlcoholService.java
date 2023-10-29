package com.metime.alcohol.service;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.domain.AlcoholName;
import com.metime.alcohol.domain.Category;
import com.metime.alcohol.dto.AlcoholDto;
import com.metime.alcohol.repository.AlcoholRepository;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AlcoholService {

    private final AlcoholRepository alcoholRepository;

    public List<AlcoholDto> getAlcoholPerPage(PagingCondition pagingCondition) {
        List<Alcohol> alcoholList = alcoholRepository.getAlcoholPerPage(pagingCondition);

        return AlcoholDto.listFrom(alcoholList);
    }

    public AlcoholDto alcoholDetail(long alcoholId) {
        Alcohol alcohol = Alcohol.builder().build();

        return AlcoholDto.from(alcohol);
    }
}
