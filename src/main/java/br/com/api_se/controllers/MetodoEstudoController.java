package br.com.api_se.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api_se.entities.MetodoEstudo;
import br.com.api_se.services.MetodoEstudoService;

@RestController
@RequestMapping("/metodosestudo")
public class MetodoEstudoController {

	@Autowired
	private MetodoEstudoService estudoService;
	
	@GetMapping
	public ResponseEntity<List<MetodoEstudo>> getMetodoEstudo(){
		return new ResponseEntity<List<MetodoEstudo>>(estudoService.getMetodoEstudo(),HttpStatus.ACCEPTED);
	}
}
