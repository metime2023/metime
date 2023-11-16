package com.metime.alcohol.dto;

import lombok.Builder;

@Builder
public record PagingDto(
	long cursorNo,
	int displayPerPage,
	String sort,
	int minPrice,
	int maxPrice
) {
}
