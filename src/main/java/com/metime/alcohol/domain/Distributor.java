package com.metime.alcohol.domain;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Distributor {

    모든_편의점(1, "모든 편의점"),
    대형_일반_마트(2, "대형/일반 마트"),
    맥주_전문점(3, "맥주 전문점");

    private final long id;
    private final String title;

    public static Distributor from(long id) {
        return Arrays.stream(values())
            .filter(category -> category.id == id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException());
    }
}
