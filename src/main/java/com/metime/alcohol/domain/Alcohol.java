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
	private static final int ENG_NAME_MINIMUM_LENGTH = 1;
	private static final int ENG_NAME_MAXIMUM_LENGTH = 200;
	private static final int PRICE_MINIMUM = 0;
	private static final int PRICE_MAXIMUM = 100_000_000;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // PK

	private String name; // 이름

	private String engName; // 영어 이름

	private int price; // 가격

	@OneToMany(mappedBy = "alcohol", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<AlcoholDistributor> distributors = new ArrayList<>(); // 판매처

	@OneToMany(mappedBy = "alcohol", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<AlcoholKeyword> keywords = new ArrayList<>(); // 키워드

	@Enumerated(EnumType.STRING)
	private Category category; // 카테고리

	private double degree; // 도수

	private int capacity; // 용량 (ml)

	private int sugarContent; // 당도

	private int acidity; // 산도

	private int body; // 바디

	private String thumbnailImageUrl; // 썸네일 이미지 URL

	private String detailImageUrl; // 디테일 이미지 URL

	private int star;

	@Builder
	private Alcohol(String name, String engName, int price, Category category,
	                int degree, int capacity, int sugarContent, int acidity, int body,
	                String thumbnailImageUrl, String detailImageUrl, int star) {
		setName(name);
		setEngName(engName);
		setPrice(price);
		this.degree = degree;
		this.capacity = capacity;
		this.category = category;
		this.sugarContent = sugarContent;
		this.acidity = acidity;
		this.body = body;
		this.thumbnailImageUrl = thumbnailImageUrl;
		this.detailImageUrl = detailImageUrl;
		this.star = star;
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

	// engName
	private void setEngName(String engName) {
		validateEngNameNotBlank(engName);
		validateEngNameLengthInRange(engName);
		this.engName = engName;
	}

	private void validateEngNameNotBlank(String engName) {
		if (engName.isBlank()) {
			throw new RuntimeException();
		}
	}

	private void validateEngNameLengthInRange(String engName) {
		int length = engName.length();
		if (length < ENG_NAME_MINIMUM_LENGTH || ENG_NAME_MAXIMUM_LENGTH < length) {
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


	public List<String> convertDistributorsToNameList() {
		return distributors.stream()
				.map(AlcoholDistributor::getDistributor)
				.map(Distributor::getName)
				.toList();
	}

	public List<String> convertKeywordsToTitleList() {
		return keywords.stream()
				.map(AlcoholKeyword::getKeyword)
				.map(Keyword::getTitle)
				.toList();
	}

	public boolean isWine() {
		return this.getCategory().isWine();
	}
}
