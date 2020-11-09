package com.canalplus.technicaltest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canalplus.technicaltest.model.dto.AbonneDto;
import com.canalplus.technicaltest.model.dto.ContratDto;
import com.canalplus.technicaltest.model.entity.Contrat;
import com.canalplus.technicaltest.repository.ContratRepository;
import com.canalplus.technicaltest.service.AbonneService;
import com.canalplus.technicaltest.service.ContratService;

@Service
public class ContratServiceImpl implements ContratService{
	
	@Autowired
	ContratRepository contratRepository;
	
	@Autowired
	AbonneService abonneService;

	@Override
	public ContratDto addContrat(ContratDto contratDto, Long id) {
		AbonneDto abonneDto = abonneService.getAbonne(id);
		if (abonneDto != null) {
			contratDto.setAbonne(abonneDto);
			return contratDto.convertToContratDto(contratRepository.save(contratDto.convertToContrat(contratDto)));
		}
		return null;
	}

	@Override
	public List<ContratDto> getContratsByAbonne(Long abonneId) {
		List<ContratDto> contratDtoList = new ArrayList<>();
		if (abonneId != null) {
			List<Contrat> contratList = contratRepository.findByAbonneId(abonneId);
			for (Contrat contrat : contratList) {
				ContratDto contratDto = new ContratDto();
				contratDtoList.add(contratDto.convertToContratDto(contrat));
			}
		}
		return contratDtoList;
	}

}
