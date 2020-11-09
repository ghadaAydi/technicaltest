package com.canalplus.technicaltest.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Abonne {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nom;
	
	private String prenom;
	
	private String telephone;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rib")
	private Rib rib;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adresse")
	private Adresse adresse;
	
	@OneToMany(mappedBy = "abonne", fetch = FetchType.LAZY)
	private List<Contrat> contrats;

}
