package com.metime.alcohol.controller;

import com.metime.alcohol.controller.request.PagingCondition;
import com.metime.alcohol.controller.response.AlcoholDetailPageResponse;
import com.metime.alcohol.controller.response.AlcoholResponse;
import com.metime.alcohol.service.AlcoholService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AlcoholRestController {

    private final AlcoholService alcoholService;

    @GetMapping("/alcohols")
    public List<AlcoholResponse> alcohols(@ModelAttribute PagingCondition pagingCondition) {
        if (pagingCondition.isCursorDefaultValue()) {
            // select max(alcoholId) from alcohol
        }

        return alcoholService.getAlcoholPerPage(pagingCondition)
                .stream()
                .map(AlcoholResponse::from)
                .toList();
    }

    @GetMapping("/alcohols/{alcoholId}")
    public AlcoholDetailPageResponse alcoholDetail(@PathVariable long alcoholId) {
        return AlcoholDetailPageResponse.from(alcoholService.alcoholDetail(alcoholId));
    }
}
