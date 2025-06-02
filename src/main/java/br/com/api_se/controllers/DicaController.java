package br.com.api_se.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api_se.entities.Dica;
import br.com.api_se.services.DicaService;

@RestController
@RequestMapping("/dicas")
public class DicaController {

	@Autowired
	private DicaService dicaService;
	
	@GetMapping
	public ResponseEntity<List<Dica>> getDica(){
		return new ResponseEntity<List<Dica>>(dicaService.getDicas(),HttpStatus.ACCEPTED);
	}
}
