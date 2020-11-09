package com.canalplus.technicaltest.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Contrat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date dateSignature;
	
	private Date dateEffet;
	
	private boolean renouvelable;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "abonne_id")
	private Abonne abonne;

}
