package com.canalplus.technicaltest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canalplus.technicaltest.model.dto.ContratDto;
import com.canalplus.technicaltest.service.ContratService;

@RestController
@RequestMapping("/contrat")
public class ContratController {

	@Autowired
	ContratService contratService;
    
    @PostMapping("/")
    public ResponseEntity<ContratDto> addContrat(@RequestParam(name = "id") Long id, @RequestParam(name = "contrat") ContratDto contratDto) {
    	contratDto = contratService.addContrat(contratDto, id);
        return new ResponseEntity<>(contratDto, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<ContratDto>> getContratsAbonne(@RequestParam(name = "id") Long id) {
    	List<ContratDto> contratDtoList = contratService.getContratsByAbonne(id);
        return new ResponseEntity<>(contratDtoList, HttpStatus.OK);
    }
}
