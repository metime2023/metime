package com.metime.alcohol.dto;

import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.domain.Category;

import java.util.List;

import com.metime.alcohol.domain.distributor.Distributor;
import com.metime.alcohol.domain.keyword.Keyword;
import lombok.Builder;

@Builder
public record AlcoholDto(
		String name,
		String description,
		double price,
		Category category,
		List<Distributor> distributors,
		List<Keyword> keywords,
		long likeCount,
		long commentCount
) {

	public static AlcoholDto from(Alcohol alcohol) {
		return AlcoholDto.builder()
				.name(alcohol.getName())
				.description(alcohol.getDescription())
				.price(alcohol.getPrice())
				.category(alcohol.getCategory())
				.distributors(alcohol.getDistributors())
				.keywords(alcohol.getKeywords())
				.build();
	}

	public static AlcoholDto exceptDistributorsFrom(Alcohol alcohol) {
		return AlcoholDto.builder()
				.name(alcohol.getName())
				.description(alcohol.getDescription())
				.price(alcohol.getPrice())
				.category(alcohol.getCategory())
				.distributors(null)
				.keywords(alcohol.getKeywords())
				.build();
	}

	public static List<AlcoholDto> listFrom(List<Alcohol> alcoholList) {
		return alcoholList.stream()
				.map(AlcoholDto::exceptDistributorsFrom)
				.toList();
	}

	public List<String> convertDistributorsToNameList() {
		return distributors()
				.stream()
				.map(Distributor::getName)
				.toList();
	}

	public List<String> convertKeywordsToTitleList() {
		return keywords()
				.stream()
				.map(Keyword::getTitle)
				.toList();
	}
}
