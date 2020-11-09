package com.canalplus.technicaltest.service;

import java.util.List;

import com.canalplus.technicaltest.model.dto.ContratDto;

public interface ContratService {
	
	public ContratDto addContrat(ContratDto contratDto, Long id);
	
	public List<ContratDto> getContratsByAbonne(Long id);

}
