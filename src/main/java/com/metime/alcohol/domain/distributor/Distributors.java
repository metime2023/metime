package com.metime.alcohol.domain.distributor;

import com.metime.alcohol.domain.Alcohol;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class Distributors {

	@OneToMany(mappedBy = "alcohol", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<AlcoholDistributor> distributors = new ArrayList<>();

	public void distribute(Alcohol alcohol, Distributor distributor) {
		validateIsAlreadyDistributed(distributor);
		distributors.add(new AlcoholDistributor(alcohol, distributor));
	}

	private void validateIsAlreadyDistributed(Distributor distributor) {
		if (getDistributors().contains(distributor)) {
			throw new RuntimeException();
		}
	}

	public List<Distributor> getDistributors() {
		return distributors.stream()
				.map(AlcoholDistributor::getDistributor)
				.toList();
	}
}
