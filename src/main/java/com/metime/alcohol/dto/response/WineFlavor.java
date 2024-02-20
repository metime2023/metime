package com.metime.alcohol.dto.response;

public record WineFlavor(
		int sugarContent,
		int acidity,
		int body
) {

	public static WineFlavor of(int sugarContent, int acidity, int body) {
		return new WineFlavor(sugarContent, acidity, body);
	}
}
