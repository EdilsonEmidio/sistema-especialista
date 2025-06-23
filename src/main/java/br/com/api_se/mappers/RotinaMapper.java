package br.com.api_se.mappers;

import br.com.api_se.dtos.RotinaDiaCreateDTO;
import br.com.api_se.dtos.RotinaEsperadaCreateDTO;
import br.com.api_se.entities.RotinaDia;
import br.com.api_se.entities.RotinaEsperada;

public class RotinaMapper {
	

	public static RotinaEsperada toEntityEsperado(RotinaEsperadaCreateDTO createDTO) {
		RotinaEsperada esperada = new RotinaEsperada();
		esperada.setNome(createDTO.nome());
		esperada.setDescricao(createDTO.descricao());
		esperada.setDataFinal(createDTO.dataFinal());
		esperada.setDataInicial(createDTO.dataInicial());
		
		return esperada;
	}
	
	public static RotinaDia toEntityDia(RotinaDiaCreateDTO createDTO) {
		RotinaDia dia = new RotinaDia();
		dia.setNome(createDTO.nome());
		dia.setDataDia(createDTO.dataDia());
		dia.setAvaliacao(createDTO.avaliacao());
		
		return dia;
	}

}
