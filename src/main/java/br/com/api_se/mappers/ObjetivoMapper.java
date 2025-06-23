package br.com.api_se.mappers;

import br.com.api_se.dtos.ObjetivoCreateDTO;
import br.com.api_se.entities.Objetivo;

public class ObjetivoMapper {

	
	public static Objetivo toEntity(ObjetivoCreateDTO dto) {
		Objetivo objetivo = new Objetivo();
		objetivo.setDescricao(dto.descricao());
		objetivo.setNome(dto.nome());
		objetivo.setDataObjetivo(dto.dataObjetivo());
		objetivo.setMeta(dto.meta());
		return objetivo;
	}
}
