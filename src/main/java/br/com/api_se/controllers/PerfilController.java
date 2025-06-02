package br.com.api_se.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api_se.dtos.PerfilCreateDTO;
import br.com.api_se.entities.Problema;
import br.com.api_se.entities.Perfil;
import br.com.api_se.services.PerfilService;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	
	@GetMapping
	public ResponseEntity<List<Perfil>> getPerfis(){
		return new ResponseEntity<>(perfilService.getPerfil(),HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Perfil> postPerfil(@RequestBody PerfilCreateDTO dto){
		return new ResponseEntity<>(perfilService.postPerfil(dto),HttpStatus.CREATED);
	}
	@PostMapping("/add/{id}")
	public ResponseEntity<Perfil> addProblema(@PathVariable long id, @RequestBody Problema problema){
		return new ResponseEntity<>(perfilService.addProblema(id, problema),HttpStatus.OK);
	}
	@PostMapping("/remove/{id}")
	public ResponseEntity<Perfil> removeProblema(@PathVariable long id, @RequestBody Problema problema){
		return new ResponseEntity<>(perfilService.removeProblema(id, problema),HttpStatus.OK);
	}
	
}
