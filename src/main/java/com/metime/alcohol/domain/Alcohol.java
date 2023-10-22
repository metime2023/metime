package com.metime.alcohol.domain;

import java.util.Optional;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Alcohol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AlcoholName name;

    @Embedded
    private Description description;

    @Embedded
    private Price price;

    @Enumerated(EnumType.STRING)
    private Distributor distributor;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Keyword keyword;

    @Embedded
    private Comments comments;

    @Embedded
    private Likes likes;

    private String imageUrl;

    @Builder
    private Alcohol(AlcoholName name, Description description, Price price, Category category, Keyword keyword,
        Distributor distributor, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.keyword = keyword;
        this.distributor = distributor;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return Optional.ofNullable(name)
            .map(AlcoholName::getValue)
            .orElse("");
    }

    public String getDescription() {
        return Optional.ofNullable(description)
            .map(Description::getValue)
            .orElse("");
    }

    public double getPrice() {
        return Optional.ofNullable(price)
            .map(Price::getValue)
            .orElse(0d);
    }
}
