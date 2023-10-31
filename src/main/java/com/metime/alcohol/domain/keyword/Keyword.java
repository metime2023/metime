package com.metime.alcohol.domain.keyword;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = "title")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class Keyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	public Keyword(String title) {
		this.title = title;
	}
}
