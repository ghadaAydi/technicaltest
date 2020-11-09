package com.canalplus.technicaltest.model.dto;

import com.canalplus.technicaltest.model.entity.Abonne;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AbonneDto {

	private long id;
	
	private String nom;
	
	private String prenom;
	
	private String telephone;
	
	private RibDto rib;
	
	private AdresseDto adresse;
	
	public AbonneDto convertToAbonneDto (Abonne abonne) {
		
		AbonneDto abonneDto = new AbonneDto();
		
		abonneDto.setId(abonne.getId());
		abonneDto.setNom(abonne.getNom());
		abonneDto.setPrenom(abonne.getPrenom());
		abonneDto.setTelephone(abonne.getTelephone());
		
		RibDto ribDto = new RibDto();
		abonneDto.setRib(ribDto.convertToRibDto(abonne.getRib()));
		
		AdresseDto adresseDto = new AdresseDto();
		abonneDto.setAdresse(adresseDto.convertToAdresseDto(abonne.getAdresse()));
		
		return abonneDto;
	}
	
	public Abonne convertToAbonne (AbonneDto abonneDto) {
		
		Abonne abonne = new Abonne();
		
		abonne.setId(abonneDto.getId());
		abonne.setNom(abonneDto.getNom());
		abonne.setPrenom(abonneDto.getPrenom());
		abonne.setTelephone(abonneDto.getTelephone());
		
		abonne.setRib(abonneDto.getRib().convertToRib(abonneDto.getRib()));
		
		abonne.setAdresse(abonneDto.getAdresse().convertToAdresse(abonneDto.getAdresse()));
		
		return abonne;
	}
}
