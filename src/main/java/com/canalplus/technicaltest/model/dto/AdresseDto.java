package com.canalplus.technicaltest.model.dto;

import com.canalplus.technicaltest.model.entity.Adresse;
import com.canalplus.technicaltest.model.enumeration.AdresseStatus;

import lombok.Data;

@Data
public class AdresseDto {
	
	private long id;
	
	private AdresseStatus statut;
	
	private int numeroRue;
	
	private String nomRue;
	
	private String complementAdresse;
	
	private int codePostal;
	
	private String ville;
	
	private String pays;
	
	
	public AdresseDto convertToAdresseDto(Adresse adresse) {
		
		AdresseDto adresseDto = new AdresseDto();
		
		adresseDto.setId(adresse.getId());
		adresseDto.setStatut(adresse.getStatut());
		adresseDto.setNumeroRue(adresse.getNumeroRue());
		adresseDto.setNomRue(adresse.getNomRue());
		adresseDto.setComplementAdresse(adresse.getComplementAdresse());
		adresseDto.setCodePostal(adresse.getCodePostal());
		adresseDto.setVille(adresse.getVille());
		adresseDto.setPays(adresse.getPays());
		
		return adresseDto;
	}
	
	public Adresse convertToAdresse(AdresseDto adresseDto) {
		
		Adresse  adresse = new Adresse();
		
		adresse.setId(adresseDto.getId());
		adresse.setStatut(adresseDto.getStatut());
		adresse.setNumeroRue(adresseDto.getNumeroRue());
		adresse.setNomRue(adresseDto.getNomRue());
		adresse.setComplementAdresse(adresseDto.getComplementAdresse());
		adresse.setCodePostal(adresseDto.getCodePostal());
		adresse.setVille(adresseDto.getVille());
		adresse.setPays(adresseDto.getPays());
		
		return adresse;
	}

}
