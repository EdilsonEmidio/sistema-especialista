package br.com.api_se.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_se.entities.Dica;
import br.com.api_se.repositories.DicaRepository;

@Service
public class DicaService {

	@Autowired
	private DicaRepository dicaRepository;
	
	public List<Dica> getDicas(){
		return dicaRepository.findAll();
	}
}
