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

        private static final int DEFAULT_VALUE = 0;

        public boolean isCursorDefaultValue() {
                return this.cursorNo <= DEFAULT_VALUE;
        }

        public PagingDto toDto() {
                return PagingDto.builder()
                    .cursorNo(this.cursorNo)
                    .displayPerPage(this.displayPerPage)
                    .sort(this.sort)
                    .minPrice(this.minPrice)
                    .maxPrice(this.maxPrice)
                    .build();
        }
}
