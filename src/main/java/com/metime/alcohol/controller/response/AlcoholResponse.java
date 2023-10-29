package com.metime.alcohol.controller.response;

import com.metime.alcohol.dto.AlcoholDto;

import java.util.List;

public record AlcoholResponse(
    String name,
    String description,
    double price,
    String category,
    List<String> distributors,
    List<String> keywords,
    long likeCount,
    long commentCount
) {

    public static AlcoholResponse from(AlcoholDto alcoholDto) {
        return new AlcoholResponse(
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
