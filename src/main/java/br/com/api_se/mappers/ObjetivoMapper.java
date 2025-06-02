package br.com.api_se.mappers;

import br.com.api_se.dtos.ObjetivoCreateDTO;
import br.com.api_se.entities.Objetivo;

public class ObjetivoMapper {

	
	public static Objetivo toEntity(ObjetivoCreateDTO dto) {
		Objetivo objetivo = new Objetivo();
		objetivo.setNome(dto.nome());
		objetivo.setDataObjetivo(dto.dataObjetivo());
		objetivo.setProgresso(dto.progresso());
		return objetivo;
	}
}
