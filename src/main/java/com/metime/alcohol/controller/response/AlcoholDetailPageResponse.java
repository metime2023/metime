package com.metime.alcohol.controller.response;

import com.metime.alcohol.dto.AlcoholDto;

public record AlcoholDetailPageResponse(
        String name,
        String keyword,
        String category,
        long likeCount,
        long commentCount
) {

    public static AlcoholDetailPageResponse from(AlcoholDto alcoholDto) {
        return new AlcoholDetailPageResponse(alcoholDto.getAlcoholName().getValue(), alcoholDto.getKeyword().getTitle(),
                alcoholDto.getCategory().getTitle(), alcoholDto.getLikeCount(),
                alcoholDto.getCommentCount());
    }
}
