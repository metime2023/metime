package com.metime.alcohol.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.domain.Category;
import lombok.Builder;

import java.util.List;

@Builder
public record AlcoholResponse(
		Long id,
		String name,
		String engName,
		int price,
		String category,
		int capacity,
		int starCount,
		String thumbnailImageUrl,
		String detailImageUrl,
		List<String> distributors,
		List<String> keywords,
		@JsonInclude(JsonInclude.Include.NON_NULL) WineFlavor wineFlavor,
		ReactionCount reactionCount
) {

	public static AlcoholResponse from(Alcohol alcohol) {
		WineFlavor wf = null;
		if (alcohol.isWine()) {
			wf = WineFlavor.of(alcohol.getSugarContent(), alcohol.getAcidity(), alcohol.getBody());
		}

		return AlcoholResponse.builder()
				.id(alcohol.getId())
				.name(alcohol.getName())
				.engName(alcohol.getEngName())
				.price(alcohol.getPrice())
				.capacity(alcohol.getCapacity())
				.starCount(alcohol.getStar())
				.category(Category.from(alcohol.getCategory().getId()).name())
				.thumbnailImageUrl(alcohol.getThumbnailImageUrl())
				.detailImageUrl(alcohol.getDetailImageUrl())
				.distributors(alcohol.convertDistributorsToNameList())
				.keywords(alcohol.convertKeywordsToTitleList())
				.wineFlavor(wf)
				.reactionCount(ReactionCount.of(0, 0))
				.build();
	}

	public static List<AlcoholResponse> listFrom(List<Alcohol> alcoholList) {
		return alcoholList.stream()
				.map(AlcoholResponse::from)
				.toList();
	}
}
