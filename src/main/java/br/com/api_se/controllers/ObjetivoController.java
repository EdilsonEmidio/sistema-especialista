package br.com.api_se.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api_se.dtos.ObjetivoCreateDTO;
import br.com.api_se.dtos.ObjetivoUpdateDTO;
import br.com.api_se.entities.Objetivo;
import br.com.api_se.services.ObjetivoService;

@RestController
@RequestMapping("objetivos")
public class ObjetivoController {

	@Autowired
	private ObjetivoService objetivoService;
	
	
	@GetMapping
	public ResponseEntity<List<Objetivo>> getObjetivos(){
		return new ResponseEntity<List<Objetivo>>(objetivoService.getObjetivos(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Objetivo> postObjetivo(@RequestBody ObjetivoCreateDTO dto){
		return new ResponseEntity<Objetivo>(objetivoService.postObjetivo(dto), HttpStatus.CREATED);
	}
	@PutMapping
	public ResponseEntity<Objetivo> updateObjetivo(@RequestBody ObjetivoUpdateDTO dto){
		return new ResponseEntity<Objetivo>(objetivoService.updateObjetivo(dto), HttpStatus.ACCEPTED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Objetivo> verificarObjetivo(@PathVariable long id){
		return new ResponseEntity<Objetivo>(objetivoService.verificarObjetivo(id), HttpStatus.OK);
	}
}
