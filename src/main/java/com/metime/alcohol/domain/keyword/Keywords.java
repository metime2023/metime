package com.metime.alcohol.domain.keyword;

import com.metime.alcohol.domain.Alcohol;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class Keywords {

	@OneToMany(mappedBy = "alcohol", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<AlcoholKeyword> keywords = new ArrayList<>();

	public void add(Alcohol alcohol, Keyword keyword) {
		validateIsAlreadyAdded(keyword);
		keywords.add(new AlcoholKeyword(alcohol, keyword));
	}

	private void validateIsAlreadyAdded(Keyword keyword) {
		if (getKeywords().contains(keyword)) {
			throw new RuntimeException();
		}
	}

	public List<Keyword> getKeywords() {
		return keywords.stream()
				.map(AlcoholKeyword::getKeyword)
				.toList();
	}
}
