package br.com.api_se.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api_se.entities.Problema;
import br.com.api_se.services.ProblemaService;

@RestController
@RequestMapping("/problemas")
public class ProblemaController {

	@Autowired
	private ProblemaService problemaService;
	
	@GetMapping
	public ResponseEntity<List<Problema>> getProblema(){
		return new ResponseEntity<List<Problema>>(problemaService.getProblemas(),HttpStatus.ACCEPTED);
	}
}
