package br.com.api_se.dtos;

import java.time.LocalDate;

public record RotinaDiaCreateDTO(
		String nome,
		LocalDate dataDia,
		int avaliacao,
		long usuarioId,
		long rotinaEsperadaId
		) {

}
