package br.com.api_se.services;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_se.dtos.RecomendacaoCreateDTO;
import br.com.api_se.entities.HorarioDia;
import br.com.api_se.entities.MetodoEstudo;
import br.com.api_se.entities.Perfil;
import br.com.api_se.entities.Problema;
import br.com.api_se.entities.Recomendacao;
import br.com.api_se.entities.RotinaDia;
import br.com.api_se.entities.RotinaEsperada;
import br.com.api_se.repositories.HorarioDiaRepository;
import br.com.api_se.repositories.MetodoEstudoRepository;
import br.com.api_se.repositories.PerfilRepository;
import br.com.api_se.repositories.RecomendacaoRepository;
import br.com.api_se.repositories.RotinaDiaRepository;
import br.com.api_se.repositories.RotinaEsperadaRepository;

@Service
public class RecomendacaoService {

	@Autowired
	private RecomendacaoRepository recomendacaoRepository;
	
	private PerfilRepository perfilRepository;
	private MetodoEstudoRepository metodoEstudoRepository;
	private RotinaEsperadaRepository rotinaEsperadaRepository;
	private RotinaDiaRepository rotinaDiaRepository;
	private HorarioDiaRepository horarioDiaRepository;
	
	public RecomendacaoService(PerfilRepository perfilRepository, MetodoEstudoRepository metodoEstudoRepository,
			RotinaEsperadaRepository rotinaEsperadaRepository, RotinaDiaRepository rotinaDiaRepository,
			HorarioDiaRepository horarioDiaRepository) {
		this.perfilRepository = perfilRepository;
		this.metodoEstudoRepository = metodoEstudoRepository;
		this.rotinaEsperadaRepository = rotinaEsperadaRepository;
		this.rotinaDiaRepository = rotinaDiaRepository;
		this.horarioDiaRepository = horarioDiaRepository;
	}

	public List<Recomendacao> getRecomendacao(){
		
		return recomendacaoRepository.findAll();
	}
	
	public Recomendacao postRecomendacao(RecomendacaoCreateDTO dto) {
		Perfil perfil = perfilRepository.findById(dto.perfilId()).orElseThrow(
				()-> new RuntimeException("Perfil não encontrado"));
		
		RotinaEsperada rotina = rotinaEsperadaRepository.findById(dto.rotinaId()).orElseThrow(
				()-> new RuntimeException("rotina não encontrado"));
		
		MetodoEstudo estudo = metodoEstudoRepository.findById(dto.metodoEstudoId()).orElseThrow(
				()-> new RuntimeException("estudo não encontrado"));
		
		Recomendacao recomendacao = new Recomendacao();
		recomendacao.setMetodoEstudo(estudo);
		recomendacao.setPerfil(perfil);
		recomendacao.setRotinaEsperada(rotina);
		
		return recomendacaoRepository.save(recomendacao);
	}
	
	public Recomendacao updateRecomendacao(long id) {
		Recomendacao recomendacao = recomendacaoRepository.findById(id).orElseThrow(
				()-> new RuntimeException("Recomendação não encontrada"));
		
		List<RotinaDia> totalDias = calcularDias(recomendacao.getRotinaEsperada(), recomendacao.getDataCriada());
		int fitness = calcularFitness(recomendacao.getRotinaEsperada(), totalDias);
		
		recomendacao.setTotalDias(totalDias.size());
		recomendacao.setFitness(fitness);
		return recomendacaoRepository.save(recomendacao);
	}
	
	private List<RotinaDia> calcularDias(RotinaEsperada rotinaEsperada, Date dataCriada) {
		List<RotinaDia> rotinasDia = rotinaDiaRepository.findByRotinaEsperada(rotinaEsperada);
		List<RotinaDia> rotinaDiaDepois =  new LinkedList<>();
		for(RotinaDia r : rotinasDia) {
			if(dataCriada.before(r.getDataDia())) {
				rotinaDiaDepois.add(r);
			}
		}
		return rotinaDiaDepois;
	}
	private int calcularFitness(RotinaEsperada rotinaEsperada, List<RotinaDia> diasDepois) {
		int fitness = 0;
		int problemasDia = 0;
		for(RotinaDia dia : diasDepois) {
			problemasDia = 0;
			List<HorarioDia> horariosDia = horarioDiaRepository.findByRotinaDia(dia);
			for(HorarioDia horario : horariosDia) {
				for(Problema p : horario.getProblemas()) {
					problemasDia += converterPeso(p.getTipo());
				}
				// antigo isso aqui da media ponderada
				// 5-5 7-3 8-2 = 25+21+16/10 = 62/10 = 6,2
				// 6-2 4-5 3-5 = 12+20+15/12 = 47/12 = 3,91
				// 3-7 4-6 4-5 = 21+24+20/18 = 65/18 = 3,61
				// 6-7 5-6 5-5 = 42+30+25/18 = 97/18 = 5,3
			}
			fitness += dia.getAvaliacao()/dia.getAvaliacao()+problemasDia;
		}
		fitness = fitness/diasDepois.size();
		return fitness;
	}
	
	private int converterPeso(String tipo) {
		if(tipo.equals("mental")) {
			return 3;
		}
		if(tipo.equals("mal habito")) {
			return 2;
		}
		if(tipo.equals("comportamental")) {
			return 2;
		}
		if(tipo.equals("ambiente")) {
			return 2;
		}
		if(tipo.equals("estrategia")) {
			return 1;
		}
		if(tipo.equals("crenca limitante")) {
			return 3;
		}
		throw new RuntimeException("tipo não existente");
	}
}
