package com.metime.alcohol.service;

import com.metime.alcohol.dto.response.AlcoholResponse;
import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.dto.PagingDto;
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

    public List<AlcoholResponse> findAlcoholList(PagingDto pagingDto) {
        List<Alcohol> alcoholList = alcoholRepository.findAlcoholList(pagingDto);

        return AlcoholResponse.listFrom(alcoholList);
    }

	public AlcoholResponse findAlcoholById(Long alcoholId) {
		Alcohol alcohol = alcoholRepository.findById(alcoholId)
				.orElseThrow(RuntimeException::new);

		return AlcoholResponse.from(alcohol);
	}
}
