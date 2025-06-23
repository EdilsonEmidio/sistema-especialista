package br.com.api_se.dtos;

import java.time.LocalDate;

public record ObjetivoCreateDTO(
		String nome,
		String descricao,
		LocalDate dataObjetivo,
		int meta,
		long usuarioId) {

}
