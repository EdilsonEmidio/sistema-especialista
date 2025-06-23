package br.com.api_se.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_se.dtos.PerfilCreateDTO;
import br.com.api_se.entities.Problema;
import br.com.api_se.entities.Perfil;
import br.com.api_se.entities.Usuario;
import br.com.api_se.repositories.PerfilRepository;
import br.com.api_se.repositories.UsuarioRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	private UsuarioRepository usuarioRepository;
	
	public PerfilService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	
	public List<Perfil> getPerfil() {
		return perfilRepository.findAll();
	}
	
	public Perfil postPerfil(PerfilCreateDTO dto) {
		
		Perfil perfil= new Perfil();
		Usuario usuario = usuarioRepository.findById(dto.usuarioId()).orElseThrow(
				()-> new RuntimeException("Usuario não encontrado"));
		
		perfil.setUsuario(usuario);
		return perfilRepository.save(perfil);
	}
	public Perfil addProblema(long perfilId, Problema caracteristica) {
		
		Perfil perfil= perfilRepository.findById(perfilId).orElseThrow(
				()-> new RuntimeException("Perfil não encontrado"));
		
		perfil.addProblema(caracteristica);
		return perfilRepository.save(perfil);
	}
	
	public Perfil removeProblema(long perfilId, Problema caracteristica) {
		
		Perfil perfil= perfilRepository.findById(perfilId).orElseThrow(
				()-> new RuntimeException("Perfil não encontrado"));
		
		perfil.removeProblema(caracteristica);
		return perfilRepository.save(perfil);
	}
	
}
