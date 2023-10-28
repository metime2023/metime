package com.metime.alcohol.domain.keyword;

import com.metime.alcohol.domain.Alcohol;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AlcoholKeyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Alcohol alcohol;

	@ManyToOne(fetch = FetchType.LAZY)
	private Keyword keyword;

	public AlcoholKeyword(Alcohol alcohol, Keyword keyword) {
		this.alcohol = alcohol;
		this.keyword = keyword;
	}
}
