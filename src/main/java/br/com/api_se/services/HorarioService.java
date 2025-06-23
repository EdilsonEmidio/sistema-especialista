package br.com.api_se.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_se.dtos.HorarioDiaCreateDTO;
import br.com.api_se.dtos.HorarioEsperadoCreateDTO;
import br.com.api_se.dtos.HorarioEsperadoUpdateDTO;
import br.com.api_se.entities.HorarioDia;
import br.com.api_se.entities.HorarioEsperado;
import br.com.api_se.entities.Problema;
import br.com.api_se.entities.RotinaDia;
import br.com.api_se.entities.RotinaEsperada;
import br.com.api_se.mappers.HorarioMapper;
import br.com.api_se.repositories.HorarioDiaRepository;
import br.com.api_se.repositories.HorarioEsperadoRepository;
import br.com.api_se.repositories.ProblemaRepository;
import br.com.api_se.repositories.RotinaDiaRepository;
import br.com.api_se.repositories.RotinaEsperadaRepository;

@Service
public class HorarioService {

	@Autowired
	private HorarioDiaRepository horarioDiaRepository;
	@Autowired
	private HorarioEsperadoRepository horarioEsperadoRepository;
	
	private RotinaDiaRepository rotinaDiaRepository;
	private RotinaEsperadaRepository rotinaEsperadaRepository;
	private ProblemaRepository problemaRepository;
	
	public HorarioService(RotinaDiaRepository rotinaDiaRepository,
			RotinaEsperadaRepository rotinaEsperadaRepository,
			ProblemaRepository problemaRepository) {
		
		this.rotinaDiaRepository = rotinaDiaRepository;
		this.rotinaEsperadaRepository = rotinaEsperadaRepository;
		this.problemaRepository = problemaRepository;
	}

	public List<HorarioEsperado> getHorarioEsperado(){
		
		return horarioEsperadoRepository.findAll();
	}
	
	public HorarioEsperado postHorarioEsperado(HorarioEsperadoCreateDTO dto){
		
		HorarioEsperado horario =  HorarioMapper.toEntityEsperado(dto);
		RotinaEsperada esperada = rotinaEsperadaRepository.findById(dto.rotinaEsperadaId()).orElseThrow(
				()-> new RuntimeException("Rotina não encontrada"));
				
		horario.setRotinaEsperada(esperada);
		return horarioEsperadoRepository.save(horario);
	}
	
	public List<HorarioDia> getHorarioDia(){
		
		return horarioDiaRepository.findAll();
	}
	public HorarioDia postHorarioDia(HorarioDiaCreateDTO dto){
		
		HorarioDia horario =  HorarioMapper.toEntityDia(dto);
		RotinaDia dia = rotinaDiaRepository.findById(dto.rotinaDiaId()).orElseThrow(
				()-> new RuntimeException("Rotina não encontrada"));
				
		horario.setRotinaDia(dia);
		return horarioDiaRepository.save(horario);
	}
	
	public HorarioDia addProblemaHorarioDia(long HorarioId, long problemaId){
		
		HorarioDia horario =  horarioDiaRepository.findById(HorarioId).orElseThrow(
				()-> new RuntimeException("horario não encontrado"));
		
		Problema problema = problemaRepository.findById(problemaId).orElseThrow(
				()-> new RuntimeException("problema não encontrado"));
		
		horario.addProblema(problema);
		return horarioDiaRepository.save(horario);
	}
	public HorarioEsperado updateHorarioEsperado(HorarioEsperadoUpdateDTO dto){
		
		HorarioEsperado horario =  horarioEsperadoRepository.findById(dto.id()).orElseThrow(
				()-> new RuntimeException("horario não encontrado"));
		
		
		horario.setAtividade(dto.atividade());
		horario.setHora(dto.hora());
		return horarioEsperadoRepository.save(horario);
	}
	public void deleteHorarioEsperado(long id){
		
		HorarioEsperado horario =  horarioEsperadoRepository.findById(id).orElseThrow(
				()-> new RuntimeException("horario não encontrado"));
		
		
		horarioEsperadoRepository.delete(horario);
	}
}
