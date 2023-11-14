package com.metime.alcohol.repository;

import java.util.List;

import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.dto.PagingDto;

public interface AlcoholRepositoryExtension {

    List<Alcohol> getAlcoholPerPage(PagingDto pagingDto);
}
