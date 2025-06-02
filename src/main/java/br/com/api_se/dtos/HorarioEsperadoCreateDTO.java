package br.com.api_se.dtos;

public record HorarioEsperadoCreateDTO(
		String hora,
		String atividade,
		long rotinaEsperadaId) {

}
