package com.canalplus.technicaltest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canalplus.technicaltest.model.entity.Abonne;

@Repository
public interface AbonneRepository extends JpaRepository<Abonne, Long>{

}
