package com.metime.alcohol.dto;

import com.metime.alcohol.domain.Alcohol;
import com.metime.alcohol.domain.Category;

import java.util.List;

import com.metime.alcohol.domain.distributor.Distributor;
import com.metime.alcohol.domain.distributor.Distributors;
import com.metime.alcohol.domain.keyword.Keyword;
import com.metime.alcohol.domain.keyword.Keywords;

public record AlcoholDto(
    String name,
    String description,
    double price,
    Category category,
    Distributors distributors,
    Keywords keywords,
    long likeCount,
    long commentCount
) {

    public static AlcoholDto from(Alcohol alcohol) {
        return new AlcoholDto(alcohol.getName(), alcohol.getDescription(), alcohol.getPrice(),
            alcohol.getCategory(), alcohol.getDistributors(),
            alcohol.getKeywords(), alcohol.getLikesCount(), alcohol.getCommentsCount());
    }

    public static List<AlcoholDto> listFrom(List<Alcohol> alcoholList) {
        return alcoholList.stream()
            .map(AlcoholDto::from)
            .toList();
    }

    public List<String> convertDistributorsToNameList() {
        return distributors().getDistributors()
            .stream()
            .map(Distributor::getName)
            .toList();
    }

    public List<String> convertKeywordsToTitleList() {
        return keywords().getKeywords()
            .stream()
            .map(Keyword::getTitle)
            .toList();
    }
}
