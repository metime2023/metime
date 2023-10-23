package com.metime.alcohol.dto;

import com.metime.alcohol.domain.AlcoholName;
import com.metime.alcohol.domain.Category;
import com.metime.alcohol.domain.Keyword;
import lombok.Getter;

@Getter
public class AlcoholDto {
    private AlcoholName alcoholName;
    private Keyword keyword;
    private Category category;
    private long likeCount;
    private long commentCount;

    public AlcoholDto(AlcoholName alcoholName, Keyword keyword, Category category, long likeCount,
            long commentCount) {
        this.alcoholName = alcoholName;
        this.keyword = keyword;
        this.category = category;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }
}
