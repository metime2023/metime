package com.metime.alcohol.service;

import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.dto.AlcoholDto;
import com.metime.alcohol.dto.PagingDto;
import com.metime.alcohol.repository.AlcoholRepository;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AlcoholService {

    private final AlcoholRepository alcoholRepository;

    public List<AlcoholDto> getAlcoholPerPage(PagingDto pagingDto) {
        List<Alcohol> alcoholList = alcoholRepository.getAlcoholPerPage(pagingDto);

        return AlcoholDto.listFrom(alcoholList);
    }

    public AlcoholDto alcoholDetail(long alcoholId) {
        Alcohol alcohol = alcoholRepository.findById(alcoholId)
                .orElseThrow(EntityNotFoundException::new);

        return AlcoholDto.from(alcohol);
    }
}
