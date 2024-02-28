package com.metime.alcohol.controller;

import com.metime.alcohol.dto.request.AlcoholSearchRequest;
import com.metime.alcohol.dto.response.AlcoholResponse;
import com.metime.alcohol.service.AlcoholService;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "주류 API", description = "주류 CRUD API")
@RequiredArgsConstructor
@RestController
public class AlcoholRestController {

	private final AlcoholService alcoholService;

	@Operation(summary = "주류 리스트 조회")
	@Parameter(name = "alcoholSearchRequest", in = ParameterIn.QUERY, description = "sort: RECOMMEND, ARCHIVE, COMMENT, STAR_SCORE")
	@GetMapping("/alcohol")
	public List<AlcoholResponse> findAlcoholList(@ModelAttribute AlcoholSearchRequest alcoholSearchRequest) {
		return alcoholService.findAlcoholList(alcoholSearchRequest.toDto());
	}

	@Operation(summary = "주류 단일 조회")
	@Parameter(name = "alcoholId", in = ParameterIn.PATH)
	@GetMapping("/alcohol/{alcoholId}")
	public AlcoholResponse alcohol(@PathVariable Long alcoholId) {
		return alcoholService.findAlcoholById(alcoholId);
	}
}
