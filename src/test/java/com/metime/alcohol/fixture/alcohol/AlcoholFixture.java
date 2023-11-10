package com.metime.alcohol.fixture.alcohol;

import com.metime.alcohol.domain.*;

public class AlcoholFixture {

	private static final AlcoholName name = AlcoholName.from("처음처럼 새로");
	private static final Description description = Description.from("새로 만나는 부드러움");
	private static final Price price = Price.from(1800L);
	private static final Category category = Category.ETC;

	public static final Alcohol ALCOHOL_FIXTURE = Alcohol.builder()
			.name(name)
			.description(description)
			.price(price)
			.category(category)
			.build();

	public static AlcoholName getName() {
		return name;
	}

	public static Description getDescription() {
		return description;
	}

	public static Price getPrice() {
		return price;
	}

	public static Category getCategory() {
		return category;
	}
}
