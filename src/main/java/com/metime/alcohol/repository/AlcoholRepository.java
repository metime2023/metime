package com.metime.alcohol.repository;

import com.metime.alcohol.domain.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlcoholRepository extends JpaRepository<Alcohol, Long>, AlcoholRepositoryExtension {
}
