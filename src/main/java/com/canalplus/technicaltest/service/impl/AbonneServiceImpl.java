package com.canalplus.technicaltest.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canalplus.technicaltest.model.dto.AbonneDto;
import com.canalplus.technicaltest.model.dto.AdresseDto;
import com.canalplus.technicaltest.model.entity.Abonne;
import com.canalplus.technicaltest.repository.AbonneRepository;
import com.canalplus.technicaltest.repository.AdresseRepository;
import com.canalplus.technicaltest.repository.RibRepository;
import com.canalplus.technicaltest.service.AbonneService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AbonneServiceImpl implements AbonneService{
	
	@Autowired
	private AbonneRepository abonneRepository;
	
	@Autowired
	private RibRepository ribRepository;

	@Autowired
	private AdresseRepository adresseRepository;
	
	@Override
	public AbonneDto addAbonne(AbonneDto abonneDto) {
		ribRepository.save(abonneDto.getRib().convertToRib(abonneDto.getRib()));
		
		adresseRepository.save(abonneDto.getAdresse().convertToAdresse(abonneDto.getAdresse()));
		
		Abonne abonne = abonneRepository.save(abonneDto.convertToAbonne(abonneDto));
		
		return abonneDto.convertToAbonneDto(abonne);
	}

	@Override
	public AbonneDto updateAdresse(Long id, AdresseDto adresseDto) {
		if (id != null) {
			Optional<Abonne> abonneOptional = abonneRepository.findById(id);
			if (abonneOptional.isPresent()) {
				Abonne abonne = abonneOptional.get();
				abonne.setAdresse(adresseDto.convertToAdresse(adresseDto));
				AbonneDto abonneDto = new AbonneDto();
				return abonneDto.convertToAbonneDto(abonneRepository.save(abonne));
			}
		}
		return null;
	}

	@Override
	public AbonneDto getAbonne(Long id) {
		if (id != null) {

			Optional<Abonne> abonneOptional = abonneRepository.findById(id);
			
			if (abonneOptional.isPresent()) {
				AbonneDto abonneDto = new AbonneDto();
				abonneDto = abonneDto.convertToAbonneDto(abonneOptional.get());
				return abonneDto;
			}
		}
		return null;
	}

}
