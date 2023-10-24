package com.metime.alcohol.dto;

import com.metime.alcohol.domain.AlcoholName;
import com.metime.alcohol.domain.Category;
import com.metime.alcohol.domain.Keyword;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class AlcoholDto {
    private AlcoholName alcoholName;
    private List<Keyword> keyword;
    private Category category;
    private long likeCount;
    private long commentCount;

    private AlcoholDto(AlcoholName alcoholName, List<Keyword> keyword, Category category, long likeCount,
            long commentCount) {
        this.alcoholName = alcoholName;
        this.keyword = keyword == null ? new ArrayList<>() : keyword;
        this.category = category;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

    public static AlcoholDto of(AlcoholName alcoholName, List<Keyword> keyword, Category category, long likeCount, long commentCount) {
        return new AlcoholDto(alcoholName, keyword, category, likeCount, commentCount);
    }
}
