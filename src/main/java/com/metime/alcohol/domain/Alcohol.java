package com.metime.alcohol.domain;

import java.util.ArrayList;
import java.util.List;

import com.metime.alcohol.domain.distributor.AlcoholDistributor;
import com.metime.alcohol.domain.distributor.Distributor;
import com.metime.alcohol.domain.keyword.AlcoholKeyword;
import com.metime.alcohol.domain.keyword.Keyword;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Alcohol {

    private static final int NAME_MINIMUM_LENGTH = 1;
    private static final int NAME_MAXIMUM_LENGTH = 50;
    private static final int DESCRIPTION_MINIMUM_LENGTH = 1;
    private static final int DESCRIPTION_MAXIMUM_LENGTH = 200;
    private static final int PRICE_MINIMUM = 0;
    private static final int PRICE_MAXIMUM = 100_000_000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    private String name; // 이름

    private String description; // 설명

    private int price; // 가격

    @OneToMany(mappedBy = "alcohol", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AlcoholDistributor> distributors = new ArrayList<>(); // 판매처

    @OneToMany(mappedBy = "alcohol", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AlcoholKeyword> keywords = new ArrayList<>(); // 키워드

    @Enumerated(EnumType.STRING)
    private Category category; // 카테고리

    private int sugarContent; // 당도

    private int acidity; // 산도

    private int body; // 바디

    private String imageUrl; // 이미지 URL

    @Builder
    private Alcohol(String name, String description, int price, Category category,
        int sugarContent, int acidity, int body, String imageUrl) {
        setName(name);
        setDescription(description);
        setPrice(price);
        this.category = category;
        this.sugarContent = sugarContent;
        this.acidity = acidity;
        this.body = body;
        this.imageUrl = imageUrl;
    }

    // name
    private void setName(String name) {
        validateNameIsNotBlank(name);
        validateNameLengthInRange(name);
        this.name = name;
    }

    private static void validateNameIsNotBlank(String name) {
        if (name.isBlank()) {
            throw new RuntimeException();
        }
    }

    private static void validateNameLengthInRange(String name) {
        int length = name.length();
        if (length < NAME_MINIMUM_LENGTH || NAME_MAXIMUM_LENGTH < length) {
            throw new RuntimeException();
        }
    }

    // description
    private void setDescription(String description) {
        validateDescriptionNotBlank(description);
        validateDescriptionLengthInRange(description);
        this.description = description;
    }

    private void validateDescriptionNotBlank(String description) {
        if (description.isBlank()) {
            throw new RuntimeException();
        }
    }

    private void validateDescriptionLengthInRange(String description) {
        int length = description.length();
        if (length < DESCRIPTION_MINIMUM_LENGTH || DESCRIPTION_MAXIMUM_LENGTH < length) {
            throw new RuntimeException();
        }
    }

    // price
    private void setPrice(int price) {
        validatePriceInRange(price);
        this.price = price;
    }

    private static void validatePriceInRange(int price) {
        if (price < PRICE_MINIMUM || PRICE_MAXIMUM < price) {
            throw new RuntimeException();
        }
    }

    // distributor
    public void allocate(Distributor distributor) {
        validateIsAlreadyDistributed(distributor);
        distributors.add(new AlcoholDistributor(this, distributor));
    }

    private void validateIsAlreadyDistributed(Distributor distributor) {
        if (getDistributors().contains(distributor)) {
            throw new RuntimeException();
        }
    }

    public List<Distributor> getDistributors() {
        return distributors.stream()
                .map(AlcoholDistributor::getDistributor)
                .toList();
    }

    // keyword
    public void addKeyword(Keyword keyword) {
        validateIsAlreadyAdded(keyword);
        keywords.add(new AlcoholKeyword(this, keyword));
    }

    private void validateIsAlreadyAdded(Keyword keyword) {
        if (getKeywords().contains(keyword)) {
            throw new RuntimeException();
        }
    }

    public List<Keyword> getKeywords() {
        return keywords.stream()
                .map(AlcoholKeyword::getKeyword)
                .toList();
    }
}
