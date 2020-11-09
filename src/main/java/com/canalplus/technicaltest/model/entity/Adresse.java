package com.canalplus.technicaltest.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.canalplus.technicaltest.model.enumeration.AdresseStatus;

import lombok.Data;

@Data
@Entity
public class Adresse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private AdresseStatus statut;
	
	private int numeroRue;
	
	private String nomRue;
	
	private String complementAdresse;
	
	private int codePostal;
	
	private String ville;
	
	private String pays;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "abonne")
	private Abonne abonne;

}
