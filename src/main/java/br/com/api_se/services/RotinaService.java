package br.com.api_se.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_se.dtos.RotinaDiaCreateDTO;
import br.com.api_se.dtos.RotinaEsperadaCreateDTO;
import br.com.api_se.entities.RotinaDia;
import br.com.api_se.entities.RotinaEsperada;
import br.com.api_se.entities.Usuario;
import br.com.api_se.mappers.RotinaMapper;
import br.com.api_se.repositories.RotinaDiaRepository;
import br.com.api_se.repositories.RotinaEsperadaRepository;
import br.com.api_se.repositories.UsuarioRepository;

@Service
public class RotinaService {

	@Autowired
	private RotinaDiaRepository rotinaDiaRepository;
	@Autowired
	private RotinaEsperadaRepository rotinaEsperadaRepository;
	
	private UsuarioRepository usuarioRepository;
	
	public RotinaService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<RotinaEsperada> getRotinaEsperada() {
		return rotinaEsperadaRepository.findAll();
	}
	public List<RotinaDia> getRotinaDia() {
		return rotinaDiaRepository.findAll();
	}
	public RotinaEsperada postRotinaEsperada(RotinaEsperadaCreateDTO dto) {
		RotinaEsperada rotinaEsperada = RotinaMapper.toEntityEsperado(dto);
		Usuario usuario = usuarioRepository.findById(dto.usuarioId()).orElseThrow(
				()-> new RuntimeException("Usuario não encontrado"));
		
		rotinaEsperada.setUsuarioId(usuario);
		return rotinaEsperadaRepository.save(rotinaEsperada);
	}
	
	public RotinaDia postRotinaDia(RotinaDiaCreateDTO dto) {
		
		RotinaDia rotinaDia = RotinaMapper.toEntityDia(dto);
		Usuario usuario = usuarioRepository.findById(dto.usuarioId()).orElseThrow(
				()-> new RuntimeException("Usuario não encontrado"));
		
		rotinaDia.setUsuario(usuario);
		return rotinaDiaRepository.save(rotinaDia);
	}
}
