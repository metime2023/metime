package com.metime.alcohol.domain.distributor;

import jakarta.persistence.*;
import lombok.*;

@Getter
@EqualsAndHashCode(of = "name")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Distributor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public Distributor(String name) {
		this.name = name;
	}
}
