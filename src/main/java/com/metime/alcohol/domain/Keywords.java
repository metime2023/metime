package com.metime.alcohol.domain;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Keywords {

    HONEY(1, "꿀");

    private final long id;
    private final String title;

    public static Keywords from(long id) {
        return Arrays.stream(values())
            .filter(keyword -> keyword.id == id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException());
    }
}
