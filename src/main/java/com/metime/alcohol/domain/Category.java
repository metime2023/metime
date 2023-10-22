package com.metime.alcohol.domain;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    BEER(1, "맥주"),
    WINE(2, "와인"),
    WHISKEY(3, "위스키"),
    ETC(4, "기타");

    private final long id;
    private final String title;

    public static Category from(long id) {
        return Arrays.stream(values())
            .filter(category -> category.id == id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException());
    }
}
