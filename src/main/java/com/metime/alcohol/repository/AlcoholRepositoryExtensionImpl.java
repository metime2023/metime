package com.metime.alcohol.repository;

import static com.metime.alcohol.domain.QAlcohol.*;

import java.util.List;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.domain.Alcohol;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlcoholRepositoryExtensionImpl implements AlcoholRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Alcohol> getAlcoholPerPage(PagingCondition pagingCondition) {
        return queryFactory
            .selectFrom(alcohol)
            .leftJoin(alcohol.keywords.keywords).fetchJoin()
            .where(
                alcohol.id.gt(pagingCondition.cursorNo()),
                alcohol.price.value.between(pagingCondition.minPrice(), pagingCondition.maxPrice())
            )
            .orderBy(alcohol.id.asc())
            .limit(pagingCondition.displayPerPage())
            .fetch();
    }
}
