package com.metime.alcohol.repository;

import java.util.List;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.domain.Alcohol;

public interface AlcoholRepositoryExtension {

    List<Alcohol> getAlcoholPerPage(PagingCondition pagingCondition);
}
