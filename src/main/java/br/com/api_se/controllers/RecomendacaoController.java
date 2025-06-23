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

import br.com.api_se.dtos.RecomendacaoCreateDTO;
import br.com.api_se.dtos.SaidaGenetico;
import br.com.api_se.entities.Recomendacao;
import br.com.api_se.services.RecomendacaoService;

@RestController
@RequestMapping("/recomendacoes")
public class RecomendacaoController {

	@Autowired
	private RecomendacaoService recomendacaoService;
	
	@GetMapping
	public ResponseEntity<List<Recomendacao>> getRecomendacao(){
		return new ResponseEntity<List<Recomendacao>>(recomendacaoService.getRecomendacao(),HttpStatus.ACCEPTED);
	}
	@PostMapping
	public ResponseEntity<Recomendacao> postRecomendacao(@RequestBody RecomendacaoCreateDTO dto){
		return new ResponseEntity<Recomendacao>(recomendacaoService.postRecomendacao(dto),HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Recomendacao> updateRecomendacao(@PathVariable long id){
		return new ResponseEntity<Recomendacao>(recomendacaoService.updateRecomendacao(id),HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<SaidaGenetico> getGenetico(@PathVariable long id){
		return new ResponseEntity<SaidaGenetico>(recomendacaoService.recomendacaoGenetica(id),HttpStatus.OK);
	}
	
}
