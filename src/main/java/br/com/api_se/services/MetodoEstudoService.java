package br.com.api_se.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_se.entities.MetodoEstudo;
import br.com.api_se.repositories.MetodoEstudoRepository;

@Service
public class MetodoEstudoService {

	@Autowired
	private MetodoEstudoRepository estudoRepository;
	
	public List<MetodoEstudo> getMetodoEstudo() {
		return estudoRepository.findAll();
	}
	
}
