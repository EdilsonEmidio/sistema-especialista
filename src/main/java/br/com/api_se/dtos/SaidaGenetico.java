package br.com.api_se.dtos;

import java.util.List;

import br.com.api_se.entities.HorarioDia;

public record SaidaGenetico(
		int fitness,
		List<HorarioDia> horarios
		) {

}
