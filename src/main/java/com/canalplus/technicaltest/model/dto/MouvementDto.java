package com.canalplus.technicaltest.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MouvementDto {
	
	private Long id;
	
	private String action;
	
	private AbonneDto abonneDto;
	
	private ConseillerDto conseillerDto;
	
	private Date creationDate;

}
