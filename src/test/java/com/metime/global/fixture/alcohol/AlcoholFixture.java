package com.metime.global.fixture.alcohol;

import com.metime.alcohol.domain.*;
import lombok.Getter;

@Getter
public class AlcoholFixture {

	private static final String NAME = "처음처럼 새로";
	private static final String DESCRIPTION = "새로 만나는 부드러움";
	private static final int PRICE = 1800;
	private static final Category CATEGORY = Category.ETC;
	private static final int SUGAR_CONTENT = 3;
	private static final int ACIDITY = 3;
	private static final int BODY = 3;

	public static final Alcohol ALCOHOL_FIXTURE = Alcohol.builder()
			.name(NAME)
			.description(DESCRIPTION)
			.price(PRICE)
			.category(CATEGORY)
			.sugarContent(SUGAR_CONTENT)
			.acidity(ACIDITY)
			.body(BODY)
			.build();
}
