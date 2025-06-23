package br.com.api_se.dtos;

import java.time.LocalDate;

public record RecomendacaoCreateDTO(
		long metodoEstudoId,
		long perfilId,
		long rotinaId,
		LocalDate dataCriada) {

}
