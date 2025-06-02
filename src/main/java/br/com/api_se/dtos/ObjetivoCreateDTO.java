package br.com.api_se.dtos;

import java.sql.Date;

public record ObjetivoCreateDTO(
		String nome,
		Date dataObjetivo,
		int progresso,
		long usuarioId) {

}
