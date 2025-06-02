package br.com.api_se.mappers;

import br.com.api_se.dtos.UsuarioCreateDTO;
import br.com.api_se.entities.Usuario;

public class UsuarioMapper {

	
	public static Usuario toEntity(UsuarioCreateDTO usuarioCreateDTO) {
		Usuario usuario = new Usuario();
		usuario.setName(usuarioCreateDTO.nome());
		
		return usuario;
	}
	/*
	public static UsuarioResponseDTO toDTO(Usuario usuario) {
		UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(usuario.getId(),usuario.getName());
		
		return usuarioResponseDTO;
	}
	*/
}
