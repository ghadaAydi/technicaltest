package com.canalplus.technicaltest.model.dto;

import com.canalplus.technicaltest.model.entity.Rib;

import lombok.Data;

@Data
public class RibDto {
	
	private long id;
	
	private String iban;
	
	private String bic;
	
	private String titulaire;
	
	public RibDto convertToRibDto(Rib rib) {
		
		RibDto ribDto = new RibDto();
		
		ribDto.setId(rib.getId());
		ribDto.setIban(rib.getIban());
		ribDto.setBic(rib.getBic());
		ribDto.setTitulaire(rib.getTitulaire());
		
		return ribDto;
	}
	
	public Rib convertToRib(RibDto ribDto) {
		
		Rib rib = new Rib();
		
		rib.setId(ribDto.getId());
		rib.setIban(ribDto.getIban());
		rib.setBic(ribDto.getBic());
		rib.setTitulaire(ribDto.getTitulaire());
		
		return rib;
	}
}
