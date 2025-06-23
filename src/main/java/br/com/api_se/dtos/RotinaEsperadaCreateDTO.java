package br.com.api_se.dtos;

import java.time.LocalDate;

public record RotinaEsperadaCreateDTO(
		String nome,
		String descricao,
		long usuarioId,
		LocalDate dataInicial,
		LocalDate dataFinal) {

}
