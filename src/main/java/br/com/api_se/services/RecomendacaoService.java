package br.com.api_se.services;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.api_se.algoritmos.Hora;
import br.com.api_se.dtos.RecomendacaoCreateDTO;
import br.com.api_se.dtos.SaidaGenetico;
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
import br.com.api_se.repositories.UsuarioRepository;

@Service
public class RecomendacaoService {

	@Autowired
	private RecomendacaoRepository recomendacaoRepository;
	
	
	private PerfilRepository perfilRepository;
	private MetodoEstudoRepository metodoEstudoRepository;
	private RotinaEsperadaRepository rotinaEsperadaRepository;
	private RotinaDiaRepository rotinaDiaRepository;
	private HorarioDiaRepository horarioDiaRepository;
	private UsuarioRepository usuarioRepository;
	
	public RecomendacaoService(PerfilRepository perfilRepository, MetodoEstudoRepository metodoEstudoRepository,
			RotinaEsperadaRepository rotinaEsperadaRepository, RotinaDiaRepository rotinaDiaRepository,
			HorarioDiaRepository horarioDiaRepository, UsuarioRepository usuarioRepository) {
		this.perfilRepository = perfilRepository;
		this.metodoEstudoRepository = metodoEstudoRepository;
		this.rotinaEsperadaRepository = rotinaEsperadaRepository;
		this.rotinaDiaRepository = rotinaDiaRepository;
		this.horarioDiaRepository = horarioDiaRepository;
		this.usuarioRepository = usuarioRepository;
	}

	public SaidaGenetico recomendacaoGenetica(long usuarioId) {

		List<HorarioDia> horarios = horarioDiaRepository.findAll();
		
		List<HorarioDia> geracoes = new LinkedList<>(horarios);
		List<Hora> horas = fazerFitness(horarios);
		horas = fazerFitness(horarios);
		for(int i = 0; i<20; i++) {
			
			cruzar(geracoes, horas);
			if(testar(geracoes, horarios)) {
				break;
			}	
		}
		
		SaidaGenetico genetico = new SaidaGenetico(mostrarFitness(horarios), horarios);
		return genetico;
	}
	private boolean testar(List<HorarioDia> geracao, List<HorarioDia> horarios) {
		if(mostrarFitness(horarios) < mostrarFitness(geracao)) {
			horarios = geracao;
			return true;
		}
		return false;
	}
	
	private void cruzar(List<HorarioDia> horarios, List<Hora> horas) {
		Random rand = new Random();
		List<HorarioDia> horarios2 = new LinkedList<>();
		for(HorarioDia h : horarios) {
			
			int r = rand.nextInt(100);
			
			if(r< h.getFitness()*100){
				horarios2.add(h);
			}
		}
		for(int i=0; i< horarios.size() - horarios2.size(); ) {
			int r = rand.nextInt(100);
			for(Hora h : horas) {
				if(!contem0(horarios2,h.getHora())) {
					if(r< h.getFitness()*100){
						HorarioDia d = new HorarioDia();
						d.setAtividade("a definir");
						d.setAvaliacao(h.getAvaliacao());
						d.setFitness(h.getFitness());
						d.setHora(h.getHora());
						d.setProblemas(null);
						d.setProblemasTotal(h.getProblemas());
						horarios2.add(d);
						if(horarios.size() - horarios2.size() ==0)break;
					}
				}
			}
		}
		mutar(horarios2, horarios);
	}
	private void mutar(List<HorarioDia> horarios2, List<HorarioDia> horasAntigas) {
		int taxa = 30;
		if(horasAntigas.size() < horarios2.size()) {
			taxa = 30 / (horasAntigas.size() - horarios2.size())*2;
		}
		if(taxa<1)taxa=1;
		Random rand = new Random();

		for(HorarioDia h : horarios2) {
			if(!contem0(horarios2,h.getHora())) {
				int ra = rand.nextInt(100);
				if(ra < 100*h.getFitness()) {
					horarios2.add(h);
				}
			}
			
		}
	}
	private int mostrarFitness(List<HorarioDia> horarios) {
		int fitness=0;
		for(HorarioDia h :horarios) {
			fitness+= h.getFitness();
		}
		fitness+= fitness/horarios.size();
		return fitness;
	}
	private List<Hora> fazerFitness(List<HorarioDia> horarios) {
		List<Hora> horas = new LinkedList<>();
		for(HorarioDia h : horarios) {

			h.setProblemasTotal(totalProblemas(h));
			h.setFitness(h.getAvaliacao()/ (h.getAvaliacao()+h.getProblemasTotal()));
			
			if(!contem(horas, h.getHora())) {
				Hora hora =  new Hora();
				hora.setHora(h.getHora());
				hora.setAvaliacao(h.getAvaliacao());
				hora.setFitness(h.getFitness());
				hora.setProblemas(h.getProblemasTotal());
				horas.add(hora);
			}else {
				Hora hora = contem2(horas, h.getHora());
				hora.setAvaliacao((h.getAvaliacao()+hora.getAvaliacao()) /2);
				hora.setFitness((h.getFitness()+hora.getFitness() )/2);
				hora.setProblemas((h.getProblemasTotal()+hora.getProblemas() )/2);
			}
		}	
		return horas;
	}
	public boolean contem0(List<HorarioDia> horas, String hora) {
		for(HorarioDia h : horas) {
			if(h.getHora().equals(hora)) {
				return true;
			}
		}
		return false;
	}
	public boolean contem(List<Hora> horas, String hora) {
		for(Hora h : horas) {
			if(h.getHora().equals(hora)) {
				return true;
			}
		}
		return false;
	}
	public Hora contem2(List<Hora> horas, String hora) {
		for(Hora h : horas) {
			if(h.getHora().equals(hora)) {
				return h;
			}
		}
		throw new RuntimeException("erro função contem2 recomendacaoService");
	}
	public int totalProblemas(HorarioDia h) {
		int total = 0;
		for(Problema p : h.getProblemas()) {
			total+= converterPeso(p.getTipo());
		}
		return total;
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
		recomendacao.setDataCriada(dto.dataCriada());
		
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
	
	private List<RotinaDia> calcularDias(RotinaEsperada rotinaEsperada, LocalDate dataCriada) {
		List<RotinaDia> rotinasDia = rotinaDiaRepository.findByRotinaEsperada(rotinaEsperada);
		List<RotinaDia> rotinaDiaDepois =  new LinkedList<>();
		for(RotinaDia r : rotinasDia) {
			if(dataCriada.isBefore(r.getDataDia())) {
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
			}
			fitness += dia.getAvaliacao()/dia.getAvaliacao()+problemasDia;
		}
		fitness = fitness/diasDepois.size();
		return fitness;
	}
	
	public static int converterPeso(String tipo) {
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
