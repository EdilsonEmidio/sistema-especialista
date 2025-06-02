package br.com.api_se.dtos;

import java.sql.Date;

public record RotinaDiaCreateDTO(
		String nome,
		Date dataDia,
		int avalicao,
		long usuarioId
		) {

}
