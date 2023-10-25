package com.metime.alcohol.dto;

import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.domain.AlcoholName;
import com.metime.alcohol.domain.Category;

import java.util.List;

import com.metime.alcohol.domain.distributor.Distributors;
import com.metime.alcohol.domain.keyword.Keywords;
import lombok.Getter;

@Getter
public class AlcoholDto {

	private AlcoholName alcoholName;
	private Distributors distributors;
	private Keywords keywords;
	private Category category;
	private long likeCount;
	private long commentCount;

	private AlcoholDto(
			AlcoholName alcoholName, Distributors distributors, Keywords keywords,
			Category category, long likeCount, long commentCount) {
		this.alcoholName = alcoholName;
		this.distributors = distributors;
		this.keywords = keywords;
		this.category = category;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
	}

	public static AlcoholDto of(AlcoholName alcoholName, Distributors distributors,
	                            Keywords keywords, Category category, long likeCount, long commentCount) {
		return new AlcoholDto(alcoholName, distributors, keywords, category, likeCount, commentCount);
	}

	public static AlcoholDto from(Alcohol alcohol) {
		return new AlcoholDto(alcohol.getAlcoholName(), alcohol.getDistributors(), alcohol.getKeywords(),
				alcohol.getCategory(), alcohol.getLikesCount(), alcohol.getCommentsCount());
	}

	public static List<AlcoholDto> listFrom(List<Alcohol> alcoholList) {
		return alcoholList.stream()
				.map(AlcoholDto::from)
				.toList();
	}
}
