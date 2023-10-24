package com.metime.alcohol.controller.response;

import com.metime.alcohol.domain.Keyword;
import com.metime.alcohol.dto.AlcoholDto;
import java.util.List;

public record AlcoholDetailPageResponse(
        String name,
        List<String> keyword,
        String category,
        long likeCount,
        long commentCount
) {

    public static AlcoholDetailPageResponse from(AlcoholDto alcoholDto) {
        return new AlcoholDetailPageResponse(
                alcoholDto.getAlcoholName().getValue(),
                alcoholDto.getKeyword().stream()
                        .map(Keyword::getTitle)
                        .toList(),
                alcoholDto.getCategory().getTitle(),
                alcoholDto.getLikeCount(),
                alcoholDto.getCommentCount());
    }
}
