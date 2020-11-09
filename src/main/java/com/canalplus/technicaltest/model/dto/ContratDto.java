package com.canalplus.technicaltest.model.dto;

import java.util.Date;

import com.canalplus.technicaltest.model.entity.Contrat;

import lombok.Data;

@Data
public class ContratDto {
	
	private long id;
	
	private Date dateSignature;
	
	private Date dateEffet;
	
	private boolean renouvelable;

	private AbonneDto abonne;
	
	public ContratDto convertToContratDto(Contrat contrat) {
		
		ContratDto contratDto = new ContratDto();
		
		contratDto.setId(contrat.getId());
		contratDto.setDateSignature(contrat.getDateSignature());
		contratDto.setDateEffet(contrat.getDateEffet());
		contratDto.setRenouvelable(contrat.isRenouvelable());
		AbonneDto abonneDto = new AbonneDto();
		contratDto.setAbonne(abonneDto.convertToAbonneDto(contrat.getAbonne()));
		
		return contratDto;
	}
	
	public Contrat convertToContrat(ContratDto contratDto) {
		
		Contrat contrat = new Contrat();
		
		contrat.setId(contratDto.getId());
		contrat.setDateSignature(contratDto.getDateSignature());
		contrat.setDateEffet(contratDto.getDateEffet());
		contrat.setRenouvelable(contratDto.isRenouvelable());
		contrat.setAbonne(contratDto.getAbonne().convertToAbonne(contratDto.getAbonne()));
		
		return contrat;
	}

}
