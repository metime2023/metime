package com.metime.global.fixture.alcohol;

import com.metime.alcohol.domain.*;
import lombok.Getter;

@Getter
public class AlcoholFixture {

	private static final String name = "처음처럼 새로";
	private static final String description = "새로 만나는 부드러움";
	private static final int price = 1800;
	private static final Category category = Category.ETC;

	public static final Alcohol ALCOHOL_FIXTURE = Alcohol.builder()
			.name(name)
			.description(description)
			.price(price)
			.category(category)
			.build();
}
