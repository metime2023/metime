package com.metime.alcohol.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Description {

    private static final int MINIMUM_LENGTH = 1;
    private static final int MAXIMUM_LENGTH = 200;

    @Column(name = "description")
    private String value;

    private Description(String value) {
        this.value = value;
    }

    public static Description from(String value) {
        validateNotBlank(value);
        validateLengthInRange(value);

        return new Description(value);
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

    public Description update(String value) {
        return Description.from(value);
    }
}
