package br.com.api_se.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_se.entities.Problema;
import br.com.api_se.repositories.ProblemaRepository;

@Service
public class ProblemaService {

	@Autowired
	private ProblemaRepository problemaRepository;
	
	public List<Problema> getProblemas(){
		return problemaRepository.findAll();
	}
}
