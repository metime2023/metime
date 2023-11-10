package com.metime.alcohol.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AlcoholTest {

	@DisplayName("올바른 길이로 이름 설정에 성공한다")
	@ParameterizedTest
	@ValueSource(ints = {1, 50})
	void 올바른_길이로_이름_설정에_성공한다(int length) {
		// given
		String name = "1".repeat(length);

		// when
		AlcoholName alcoholName = AlcoholName.from(name);

		// then
		assertThat(alcoholName.getValue()).isEqualTo(name);
	}

	@DisplayName("올바르지 않은 길이로 이름 설정에 실패한다")
	@ParameterizedTest
	@ValueSource(ints = {0, 51})
	void 올바르지_않은_길이로_이름_설정에_실패한다(int length) {
		// given
		String name = "1".repeat(length);

		// when, then
		assertThatThrownBy(() -> AlcoholName.from(name))
				.isInstanceOf(RuntimeException.class);
	}

	@DisplayName("null 또는 empty 로 이름 설정에 실패한다")
	@ParameterizedTest
	@NullAndEmptySource
	void null_또는_empty_로_이름_설정에_실패한다(String value) {
		// given
		String name = value;

		// when, then
		assertThatThrownBy(() -> AlcoholName.from(name))
				.isInstanceOf(RuntimeException.class);
	}
}
