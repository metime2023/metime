package com.metime.alcohol.controller.response;

import com.metime.alcohol.dto.AlcoholDto;

import java.util.List;

public record AlcoholDetailPageResponse(
	String name,
	String description,
	double price,
	String category,
	List<String> distributors,
	List<String> keywords,
	long likeCount,
	long commentCount
) {

	public static AlcoholDetailPageResponse from(AlcoholDto alcoholDto) {
		return new AlcoholDetailPageResponse(
			alcoholDto.name(),
			alcoholDto.description(),
			alcoholDto.price(),
			alcoholDto.category().getTitle(),
			alcoholDto.convertDistributorsToNameList(),
			alcoholDto.convertKeywordsToTitleList(),
			alcoholDto.likeCount(),
			alcoholDto.commentCount());
	}
}
