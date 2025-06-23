package br.com.api_se.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_se.dtos.ObjetivoCreateDTO;
import br.com.api_se.dtos.ObjetivoUpdateDTO;
import br.com.api_se.entities.Objetivo;
import br.com.api_se.entities.Usuario;
import br.com.api_se.mappers.ObjetivoMapper;
import br.com.api_se.repositories.ObjetivoRepository;
import br.com.api_se.repositories.UsuarioRepository;

@Service
public class ObjetivoService {

	@Autowired
	private ObjetivoRepository objetivoRepository;
	
	private UsuarioRepository usuarioRepository;
	
	public ObjetivoService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Objetivo> getObjetivos(){
		
		return objetivoRepository.findAll();
	}
	
	public Objetivo postObjetivo(ObjetivoCreateDTO dto){
		Objetivo objetivo = ObjetivoMapper.toEntity(dto);
		Usuario usuario = usuarioRepository.findById(dto.usuarioId()).orElseThrow(
				()-> new RuntimeException("Usuario não encontrado"));
		objetivo.setProgresso(0);
		objetivo.setUsuarioId(usuario);
		objetivo.setConcluido(false);
		return objetivoRepository.save(objetivo);
	}
	public Objetivo updateObjetivo(ObjetivoUpdateDTO dto){
		Objetivo objetivo = objetivoRepository.findById(dto.id()).orElseThrow(
				()-> new RuntimeException("objetivo não encontrado"));
		
		objetivo.setProgresso(dto.progresso());
		if(objetivo.getMeta() < objetivo.getProgresso()) {
			objetivo.setConcluido(true);
		}
		return objetivoRepository.save(objetivo);
	}
	
	public Objetivo verificarObjetivo(long id){
		Objetivo objetivo = objetivoRepository.findById(id).orElseThrow(
				()-> new RuntimeException("objetivo não encontrado"));

		return objetivo;
	}
}
