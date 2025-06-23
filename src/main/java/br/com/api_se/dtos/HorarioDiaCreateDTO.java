package br.com.api_se.dtos;

public record HorarioDiaCreateDTO(
		String hora,
		String atividade,
		int avaliacao,
		long rotinaDiaId
		) {

}
