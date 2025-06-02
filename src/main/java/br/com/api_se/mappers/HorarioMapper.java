package br.com.api_se.mappers;

import br.com.api_se.dtos.HorarioDiaCreateDTO;
import br.com.api_se.dtos.HorarioEsperadoCreateDTO;
import br.com.api_se.entities.HorarioDia;
import br.com.api_se.entities.HorarioEsperado;

public class HorarioMapper {

	
	public static HorarioEsperado toEntityEsperado(HorarioEsperadoCreateDTO createDTO) {
		HorarioEsperado esperado = new HorarioEsperado();
		esperado.setAtividade(createDTO.atividade());
		esperado.setHora(createDTO.hora());
		
		return esperado;
	}
	
	public static HorarioDia toEntityDia(HorarioDiaCreateDTO createDTO) {
		HorarioDia dia = new HorarioDia();
		dia.setAtividade(createDTO.atividade());
		dia.setHora(createDTO.hora());
		dia.setAvaliacao(createDTO.avalicao());
		
		return dia;
	}
}
