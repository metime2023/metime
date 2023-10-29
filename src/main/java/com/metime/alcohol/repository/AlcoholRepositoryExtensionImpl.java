package com.metime.alcohol.repository;

import static com.metime.alcohol.domain.QAlcohol.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.domain.Alcohol;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlcoholRepositoryExtensionImpl implements AlcoholRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Alcohol> getAlcoholPerPage(PagingCondition pagingCondition) {
        JPAQuery<Alcohol> alcoholWithFetchDistributors = queryFactory.selectFrom(alcohol)
                .leftJoin(alcohol.distributors.distributors).fetchJoin();
        List<Alcohol> alcoholsWithDistributors = alcoholWithFetchDistributors.fetch();

        JPAQuery<Alcohol> alcoholWithFetchKeywords = queryFactory.selectFrom(alcohol)
                .leftJoin(alcohol.keywords.keywords).fetchJoin();
        List<Alcohol> alcoholsWithKeywords = alcoholWithFetchKeywords.fetch();

        List<Alcohol> result = new ArrayList<>(alcoholsWithDistributors);
        result.addAll(alcoholsWithKeywords);

        result = result.stream()
            .filter(a -> a.getId() > pagingCondition.cursorNo() &&
                a.getPrice() >= pagingCondition.minPrice() &&
                a.getPrice() <= pagingCondition.maxPrice())
            .sorted(Comparator.comparing(Alcohol::getId))
            .limit(pagingCondition.displayPerPage())
            .collect(Collectors.toList());

        return result;
    }
}
