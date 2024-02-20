package com.metime.alcohol.dto.request;

import com.metime.alcohol.support.annotation.SortCondition;
import com.metime.alcohol.domain.AlcoholSort;
import com.metime.alcohol.dto.PagingDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

@Schema(description = "주류 응답 DTO")
public record AlcoholSearchRequest(
		@Schema(defaultValue = "0") long cursorNo,
		@Schema(defaultValue = "10") @Min(1) int displayPerPage,
		@Schema(defaultValue = "RECOMMEND") @SortCondition(enumClass = AlcoholSort.class, ignoreCase = true) String sort,
		@Schema(defaultValue = "0") int minPrice,
		@Schema(defaultValue = "10000") int maxPrice
) {

	private static final int DEFAULT_DISPLAY_PER_PAGE = 10;
	private static final int DEFAULT_MAX_PRICE = 10000;

	public int getDisplayPerPage() {
		if (this.displayPerPage <= 0) {
			return DEFAULT_DISPLAY_PER_PAGE;
		}
		return this.displayPerPage;
	}

	public int getMaxPrice() {
		if (this.maxPrice <= 0) {
			return DEFAULT_MAX_PRICE;
		}
		return this.maxPrice;
	}

	public PagingDto toDto() {
		return PagingDto.builder()
				.cursorNo(this.cursorNo)
				.displayPerPage(displayPerPage)
				.sort(this.sort)
				.minPrice(this.minPrice)
				.maxPrice(getMaxPrice())
				.build();
	}
}
