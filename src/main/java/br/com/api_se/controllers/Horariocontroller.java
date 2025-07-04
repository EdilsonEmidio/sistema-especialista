package br.com.api_se.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api_se.dtos.HorarioDiaCreateDTO;
import br.com.api_se.dtos.HorarioEsperadoCreateDTO;
import br.com.api_se.dtos.HorarioEsperadoUpdateDTO;
import br.com.api_se.entities.HorarioDia;
import br.com.api_se.entities.HorarioEsperado;
import br.com.api_se.services.HorarioService;

@RestController
@RequestMapping("/horarios")
public class Horariocontroller {

	@Autowired
	private HorarioService horarioService;
	
	@GetMapping("/esperado")
	public ResponseEntity<List<HorarioEsperado>> getHorarioEsperado(){
		
		return new ResponseEntity<>(horarioService.getHorarioEsperado(),HttpStatus.ACCEPTED);
	}
	@GetMapping("/dia")
	public ResponseEntity<List<HorarioDia>> getHorarioDia(){
		
		return new ResponseEntity<>(horarioService.getHorarioDia(),HttpStatus.ACCEPTED);
	}
	@PostMapping("/esperado")
	public ResponseEntity<HorarioEsperado> postHorarioEsperado(@RequestBody HorarioEsperadoCreateDTO horario){
		
		return new ResponseEntity<>(horarioService.postHorarioEsperado(horario),HttpStatus.CREATED);
	}
	@PutMapping("/esperado")
	public ResponseEntity<HorarioEsperado> updateHorarioEsperado(@RequestBody HorarioEsperadoUpdateDTO horario){
		
		return new ResponseEntity<>(horarioService.updateHorarioEsperado(horario),HttpStatus.CREATED);
	}
	@DeleteMapping("/esperado/{id}")
	public ResponseEntity<String> deleteHorarioEsperado(long id){
		try {
			horarioService.deleteHorarioEsperado(id);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CREATED);
		}
		return new ResponseEntity<>("deletado com sucesso",HttpStatus.OK);
	}
	@PostMapping("/dia")
	public ResponseEntity<HorarioDia> postHorarioDia(@RequestBody HorarioDiaCreateDTO horario){
		
		return new ResponseEntity<>(horarioService.postHorarioDia(horario),HttpStatus.CREATED);
	}
	@PutMapping("/dia/{id}/{problema}")
	public ResponseEntity<HorarioDia> updateHorarioDia(@PathVariable long id, @PathVariable long problema){
		
		return new ResponseEntity<>(horarioService.addProblemaHorarioDia(id, problema),HttpStatus.CREATED);
	}

	
	
}
