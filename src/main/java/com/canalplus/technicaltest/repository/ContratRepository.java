package com.canalplus.technicaltest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.canalplus.technicaltest.model.entity.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long>{
	
	@Query("from Contrat c WHERE c.abonne.id = :abonneId")
	List<Contrat> findByAbonneId(Long abonneId);
}
