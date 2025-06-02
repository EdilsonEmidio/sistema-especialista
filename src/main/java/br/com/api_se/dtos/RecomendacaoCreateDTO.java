package br.com.api_se.dtos;

import java.sql.Date;

public record RecomendacaoCreateDTO(
		long metodoEstudoId,
		long perfilId,
		long rotinaId,
		Date dataCriada) {

}
