package com.metime.alcohol.controller.response;

import com.metime.alcohol.domain.distributor.Distributor;
import com.metime.alcohol.domain.keyword.Keyword;
import com.metime.alcohol.dto.AlcoholDto;

import java.util.List;

public record AlcoholDetailPageResponse(
		String name,
		List<String> distributors,
		List<String> keywords,
		String category,
		long likeCount,
		long commentCount
) {

	public static AlcoholDetailPageResponse from(AlcoholDto alcoholDto) {
		return new AlcoholDetailPageResponse(
				alcoholDto.getAlcoholName().getValue(),
				alcoholDto.getDistributors().getDistributors()
						.stream().map(Distributor::getName)
						.toList(),
				alcoholDto.getKeywords().getKeywords()
						.stream()
						.map(Keyword::getTitle)
						.toList(),
				alcoholDto.getCategory().getTitle(),
				alcoholDto.getLikeCount(),
				alcoholDto.getCommentCount());
	}
}
