package com.canalplus.technicaltest.service;

import com.canalplus.technicaltest.model.dto.AbonneDto;
import com.canalplus.technicaltest.model.dto.AdresseDto;

public interface AbonneService {

	public AbonneDto addAbonne(AbonneDto abonneDto);
	
	public AbonneDto updateAdresse(Long id, AdresseDto adresseDto);
	
	public AbonneDto getAbonne(Long id);
}
