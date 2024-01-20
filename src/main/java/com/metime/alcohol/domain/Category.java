package com.metime.alcohol.domain;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    LAGER(1, "라거"),
    WHITE_BEER(2, "밀맥주"),
    BLACK_BEER(3, "흑맥주"),
    RED_WINE(4, "레드와인"),
    WHITE_WINE(5, "화이트와인"),
    WHISKEY(6, "위스키");

    private final long id;
    private final String title;

    public static Category from(long id) {
        return Arrays.stream(values())
            .filter(category -> category.id == id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException());
    }
}
