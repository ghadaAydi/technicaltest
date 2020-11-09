package com.canalplus.technicaltest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canalplus.technicaltest.model.entity.Adresse;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long>{

}
