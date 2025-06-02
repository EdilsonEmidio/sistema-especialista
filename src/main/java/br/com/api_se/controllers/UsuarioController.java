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

import br.com.api_se.dtos.UsuarioCreateDTO;
import br.com.api_se.entities.Usuario;
import br.com.api_se.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getUsuarios(){
		
		return new ResponseEntity<>(usuarioService.getUsuarios(),HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Usuario> postUsuarios(@RequestBody UsuarioCreateDTO usuarioCreateDTO){
		
		return new ResponseEntity<>(usuarioService.postUsuario(usuarioCreateDTO),HttpStatus.OK);
	}
}
