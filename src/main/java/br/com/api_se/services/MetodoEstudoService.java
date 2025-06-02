package br.com.api_se.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_se.entities.MetodoEstudo;
import br.com.api_se.entities.Perfil;
import br.com.api_se.entities.Problema;
import br.com.api_se.repositories.MetodoEstudoRepository;
import br.com.api_se.repositories.PerfilRepository;

@Service
public class MetodoEstudoService {

	@Autowired
	private MetodoEstudoRepository estudoRepository;
	
	private PerfilRepository perfilRepository;
	
	public MetodoEstudoService(PerfilRepository perfilRepository) {
		this.perfilRepository = perfilRepository;
	}
	
	public List<MetodoEstudo> getMetodoEstudo() {
		return estudoRepository.findAll();
	}

	public MetodoEstudo gerarMetodoEstudo(long perfilId) {
		
		Perfil perfil = perfilRepository.findById(perfilId).orElseThrow(
				()-> new RuntimeException("perfil não encontrado"));
		MetodoEstudo metodo = null;
		int quantidade = 0;
		List<MetodoEstudo> metodosEstudos = estudoRepository.findAll();
		for(MetodoEstudo m : metodosEstudos) {
			int cont = contarProblemas(perfil.getProblemas(), m.getProblemas());
			if(cont > quantidade) {
				metodo = m;
				quantidade = cont;
			}
		}
		return metodo;
	}
	
	public int contarProblemas(List<Problema> problemasPerfil, List<Problema> problemasMetodo) {
		int quantidade = 0;
		for(Problema p : problemasPerfil) {
			if(problemasMetodo.contains(p)) {
				quantidade+=1;
			}
		}
		return quantidade;
	}
	
}
