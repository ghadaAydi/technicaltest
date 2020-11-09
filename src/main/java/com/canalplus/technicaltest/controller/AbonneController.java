package com.canalplus.technicaltest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canalplus.technicaltest.model.dto.AbonneDto;
import com.canalplus.technicaltest.service.AbonneService;

@RestController
@RequestMapping("/abonne")
public class AbonneController {

	@Autowired
    private AbonneService abonneService;
    
    @GetMapping("/")
    public ResponseEntity<AbonneDto> getAbonne(@RequestParam(name = "id") Long id) {
    	
        AbonneDto abonneDto = abonneService.getAbonne(id);
        return new ResponseEntity<>(abonneDto, HttpStatus.OK);
    }
    
    @PostMapping("/")
    public ResponseEntity<AbonneDto> addAbonne(@RequestParam(name = "abonne") AbonneDto abonneDto) {
    	abonneDto = abonneService.addAbonne(abonneDto);
        return new ResponseEntity<>(abonneDto, HttpStatus.OK);
    }

}
