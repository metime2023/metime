package com.metime.alcohol.repository;

import static com.metime.alcohol.domain.QAlcohol.*;
import static com.metime.alcohol.domain.keyword.QAlcoholKeyword.*;
import static com.metime.alcohol.domain.keyword.QKeyword.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.domain.Alcohol;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class AlcoholRepositoryExtensionImpl implements AlcoholRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Alcohol> getAlcoholPerPage(PagingCondition pagingCondition) {
        List<Long> alcoholIds = queryFactory
            .select(alcohol.id)
            .from(alcohol)
            .where(
                alcohol.id.gt(pagingCondition.cursorNo()),
                alcohol.price.between(pagingCondition.minPrice(), pagingCondition.maxPrice())
            )
            .limit(pagingCondition.displayPerPage())
            .fetch();

        return queryFactory
            .selectFrom(alcohol)
            .leftJoin(alcohol.keywords, alcoholKeyword).fetchJoin()
            .leftJoin(alcoholKeyword.keyword, keyword).fetchJoin()
            .where(alcohol.id.in(alcoholIds))
            .fetch();
    }
}
