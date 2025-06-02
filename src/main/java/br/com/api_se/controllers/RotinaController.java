package br.com.api_se.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api_se.dtos.RotinaDiaCreateDTO;
import br.com.api_se.dtos.RotinaEsperadaCreateDTO;
import br.com.api_se.entities.RotinaDia;
import br.com.api_se.entities.RotinaEsperada;
import br.com.api_se.services.RotinaService;

@RestController
@RequestMapping("/rotinas")
public class RotinaController {

	@Autowired
	private RotinaService rotinaService;
	
	@GetMapping("/dia")
	public ResponseEntity<List<RotinaDia>> getRotinaDia(){
		return new ResponseEntity<List<RotinaDia>>(rotinaService.getRotinaDia(), HttpStatus.OK);
	}
	@GetMapping("/esperada")
	public ResponseEntity<List<RotinaEsperada>> getRotinaEsperada(){
		return new ResponseEntity<List<RotinaEsperada>>(rotinaService.getRotinaEsperada(), HttpStatus.OK);
	}
	@PostMapping("/dia")
	public ResponseEntity<RotinaDia> postRotinaDia(@RequestBody RotinaDiaCreateDTO dto){
		return new ResponseEntity<RotinaDia>(rotinaService.postRotinaDia(dto), HttpStatus.CREATED);
	}
	@PostMapping("/esperada")
	public ResponseEntity<RotinaEsperada> postRotinaEsperada(@RequestBody RotinaEsperadaCreateDTO dto){
		return new ResponseEntity<>(rotinaService.postRotinaEsperada(dto), HttpStatus.CREATED);
	}
}
