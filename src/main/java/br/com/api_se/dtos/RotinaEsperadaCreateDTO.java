package br.com.api_se.dtos;

import java.sql.Date;

public record RotinaEsperadaCreateDTO(
		String nome,
		String descricao,
		long usuarioId,
		Date dataInicial,
		Date dataFinal) {

}
