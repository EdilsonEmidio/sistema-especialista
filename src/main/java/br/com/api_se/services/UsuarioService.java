package br.com.api_se.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_se.dtos.UsuarioCreateDTO;
import br.com.api_se.entities.Usuario;
import br.com.api_se.mappers.UsuarioMapper;
import br.com.api_se.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> getUsuarios(){
		
		return usuarioRepository.findAll();
	}
	
	public Usuario postUsuario(UsuarioCreateDTO usuarioCreateDTO){
		
		Usuario usuario =  UsuarioMapper.toEntity(usuarioCreateDTO);
		
		return usuarioRepository.save(usuario);
	}
	
}
