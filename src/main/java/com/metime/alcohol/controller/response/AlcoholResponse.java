package com.metime.alcohol.controller.response;

import com.metime.alcohol.dto.AlcoholDto;

import java.util.List;

public record AlcoholResponse(
    String name,
    String description,
    int price,
    String category,
    List<String> keywords,
    int sugarContent,
    int acidity,
    int body,
    long likeCount,
    long commentCount
) {

    public static AlcoholResponse from(AlcoholDto alcoholDto) {
        return new AlcoholResponse(
            alcoholDto.name(),
            alcoholDto.description(),
            alcoholDto.price(),
            alcoholDto.category().getTitle(),
            alcoholDto.convertKeywordsToTitleList(),
            alcoholDto.sugarContent(),
            alcoholDto.acidity(),
            alcoholDto.body(),
            alcoholDto.likeCount(),
            alcoholDto.commentCount());
    }

    public static List<AlcoholResponse> listFrom(List<AlcoholDto> alcoholDtoList) {
        return alcoholDtoList.stream()
            .map(AlcoholResponse::from)
            .toList();
    }
}
