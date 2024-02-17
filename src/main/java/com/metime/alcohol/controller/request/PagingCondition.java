package com.metime.alcohol.controller.request;

import com.metime.alcohol.common.annotation.SortCondition;
import com.metime.alcohol.domain.AlcoholSort;
import com.metime.alcohol.dto.PagingDto;

import jakarta.validation.constraints.Min;

public record PagingCondition(
    long cursorNo,
    @Min(1)
    int displayPerPage,
    @SortCondition(enumClass = AlcoholSort.class, ignoreCase = true)
    String sort,
    int minPrice,
    int maxPrice
) {

    private static final int DEFAULT_CURSOR_NO = 0;
    private static final int DEFAULT_DISPLAY_PER_PAGE = 10;
    private static final int DEFAULT_MAX_PRICE = 10000;

    public boolean isCursorDefaultValue() {
        return this.cursorNo <= DEFAULT_CURSOR_NO;
    }

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
