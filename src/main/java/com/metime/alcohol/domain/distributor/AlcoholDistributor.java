package com.metime.alcohol.domain.distributor;

import com.metime.alcohol.domain.Alcohol;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AlcoholDistributor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Alcohol alcohol;

	@ManyToOne(fetch = FetchType.LAZY)
	private Distributor distributor;

	public AlcoholDistributor(Alcohol alcohol, Distributor distributor) {
		this.alcohol = alcohol;
		this.distributor = distributor;
	}
}
