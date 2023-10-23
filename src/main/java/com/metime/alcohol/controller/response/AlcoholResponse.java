package com.metime.alcohol.controller.response;

import com.metime.alcohol.domain.AlcoholName;
import com.metime.alcohol.domain.Category;
import com.metime.alcohol.domain.Keyword;
import com.metime.alcohol.dto.AlcoholDto;

public record AlcoholResponse(
        String name,
        String keyword,
        String category,
        long likeCount,
        long commentCount
) {

    public static AlcoholResponse from(AlcoholDto alcoholDto) {
        return new AlcoholResponse(alcoholDto.getAlcoholName().getValue(), alcoholDto.getKeyword().getTitle(),
                alcoholDto.getCategory().getTitle(), alcoholDto.getLikeCount(),
                alcoholDto.getCommentCount());
    }
}
