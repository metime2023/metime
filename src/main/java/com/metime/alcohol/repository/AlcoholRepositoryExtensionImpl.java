package com.metime.alcohol.repository;

import static com.metime.alcohol.domain.QAlcohol.*;
import static com.metime.alcohol.domain.keyword.QAlcoholKeyword.*;
import static com.metime.alcohol.domain.keyword.QKeyword.*;
import static com.metime.comment.domain.QComment.comment;

import java.util.List;

import com.metime.comment.domain.QComment;
import org.springframework.stereotype.Repository;

import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.dto.PagingDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class AlcoholRepositoryExtensionImpl implements AlcoholRepositoryExtension {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Alcohol> findAlcoholList(PagingDto pagingDto) {
		List<Long> alcoholIds = queryFactory
				.select(alcohol.id)
				.from(alcohol)
				.where(
						alcohol.id.gt(pagingDto.cursorNo()),
						alcohol.price.between(pagingDto.minPrice(), pagingDto.maxPrice())
				)
				.limit(pagingDto.displayPerPage())
				.fetch();

		return queryFactory
				.selectFrom(alcohol)
				.leftJoin(alcohol.keywords, alcoholKeyword).fetchJoin()
				.leftJoin(alcoholKeyword.keyword, keyword).fetchJoin()
				.leftJoin(comment).on(alcohol.eq(comment.alcohol)).fetchJoin()
				.where(alcohol.id.in(alcoholIds))
				.fetch();
	}
}
