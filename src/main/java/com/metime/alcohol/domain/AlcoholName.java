package com.metime.alcohol.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AlcoholName {

    private static final int MINIMUM_LENGTH = 1;
    private static final int MAXIMUM_LENGTH = 50;

    @Column(name = "name")
    private String value;

    private AlcoholName(String value) {
        this.value = value;
    }

    public static AlcoholName from(String value) {
        validateNotBlank(value);
        validateLengthInRange(value);

        return new AlcoholName(value);
    }

    private static void validateNotBlank(String value) {
        if (value.isBlank()) {
            throw new RuntimeException();
        }
    }

    private static void validateLengthInRange(String value) {
        int length = value.length();
        if (length < MINIMUM_LENGTH || MAXIMUM_LENGTH < length) {
            throw new RuntimeException();
        }
    }

    public AlcoholName update(String value) {
        return AlcoholName.from(value);
    }
}
