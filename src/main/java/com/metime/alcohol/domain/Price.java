package com.metime.alcohol.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Price {

    private static final double MINIMUM_PRICE = 0;
    private static final double MAXIMUM_PRICE = 100_000_000;

    @Column(name = "price")
    private double value;

    private Price(double value) {
        this.value = value;
    }

    public static Price from(double value) {
        validatePriceInRange(value);

        return new Price(value);
    }

    private static void validatePriceInRange(double value) {
        double price = value;
        if (price < MINIMUM_PRICE || MAXIMUM_PRICE < price) {
            throw new RuntimeException();
        }
    }

    public Price update(double value) {
        return Price.from(value);
    }
}
