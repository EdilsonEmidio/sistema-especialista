package br.com.api_se.algoritmos;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.com.api_se.entities.HorarioDia;
import br.com.api_se.entities.MetodoEstudo;
import br.com.api_se.entities.Perfil;
import br.com.api_se.entities.Problema;
import br.com.api_se.entities.Recomendacao;
import br.com.api_se.entities.RotinaDia;
import br.com.api_se.entities.RotinaEsperada;
import br.com.api_se.entities.Usuario;
import br.com.api_se.repositories.HorarioDiaRepository;
import br.com.api_se.repositories.MetodoEstudoRepository;
import br.com.api_se.repositories.PerfilRepository;
import br.com.api_se.repositories.RecomendacaoRepository;
import br.com.api_se.repositories.RotinaDiaRepository;
import br.com.api_se.repositories.RotinaEsperadaRepository;
import br.com.api_se.repositories.UsuarioRepository;
import br.com.api_se.services.MetodoEstudoService;
import br.com.api_se.services.RecomendacaoService;

public class Genetico {

	private UsuarioRepository usuarioRepository;
	private RecomendacaoRepository recomendacaoRepository;
	private RotinaEsperadaRepository rotinaEsperadaRepository;
	private RotinaDiaRepository rotinaDiaRepository;
	private HorarioDiaRepository horarioDiaRepository;
	private PerfilRepository perfilRepository;
	private MetodoEstudoRepository estudoRepository;
	
	
	public Genetico(UsuarioRepository usuarioRepository, RecomendacaoRepository recomendacaoRepository,
			RotinaEsperadaRepository rotinaEsperadaRepository, RotinaDiaRepository rotinaDiaRepository,
			HorarioDiaRepository horarioDiaRepository, PerfilRepository perfilRepository,
			MetodoEstudoRepository estudoRepository) {
		
		this.usuarioRepository = usuarioRepository;
		this.recomendacaoRepository = recomendacaoRepository;
		this.rotinaEsperadaRepository = rotinaEsperadaRepository;
		this.rotinaDiaRepository = rotinaDiaRepository;
		this.horarioDiaRepository = horarioDiaRepository;
		this.perfilRepository = perfilRepository;
		this.estudoRepository = estudoRepository;
	}


	public Recomendacao genetica(long usuarioId, LocalDate data) {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
				()-> new RuntimeException("usuario não encontrado"));
		
		List<RotinaDia> dias = rotinaDiaRepository.findByUsuario(usuario);
		List<RotinaDia> diasApos = diasApos(dias, data);
		List<Dia> diasResultado = calcularHoras(diasApos);
		
		Dia pai2 = melhores(diasResultado);
		for(Dia d: diasResultado) {
			Dia pai1 = torneio(d);
			
			//d = crossover(pai1,pai2);
		}
		
		RotinaEsperada rotinaEsperada = new RotinaEsperada();
		rotinaEsperada.setNome("rotina recomendada");
		rotinaEsperada.setDescricao("rotina feita baseado em algoritmo genetico");
		rotinaEsperada.setUsuarioId(usuario);
		
		Recomendacao recomendacao = new Recomendacao();
		recomendacao.setPerfil(perfilRepository.findByUsuario(usuario));
		recomendacao.setRotinaEsperada(rotinaEsperada);
		recomendacao.setMetodoEstudo(gerarMetodoEstudo(perfilRepository.findByUsuario(usuario).getId()));
		
		
		return recomendacao;
	}
	private Dia melhores(List<Dia> dias) {
		List<Hora> melhores = new LinkedList<>();
		Dia dia = new Dia();
		for(Dia d : dias) {
			for(Hora h : d.getHoras()) {
				melhores.add(h);
			}
		}
		Collections.sort(melhores, Comparator.comparing(Hora::getAvaliacao));
		dia.setHoras(melhores);
		return dia;
	}
	private Dia torneio(Dia dia) {
		Dia diaNovo = new Dia();
		List<Hora> horas = dia.getHoras();
		Random rand = new Random();
		List<Integer> podeNao = new LinkedList<>();
		
		int pare = 0;
		while(pare < horas.size()) {
			int lixo = 0;
			do {
				lixo = rand.nextInt(horas.size());
			}while(podeNao.contains(lixo));
			int lixo2 = 0;
			do {
				lixo2 = rand.nextInt(horas.size());
			}while(podeNao.contains(lixo2));
				
			if(horas.get(lixo).getAvaliacao()>horas.get(lixo2).getAvaliacao()) {
				podeNao.add(lixo);
				horas.add(horas.remove(lixo));
			}else {
				podeNao.add(lixo2);
				horas.add(horas.remove(lixo2));
			}
			pare++;
		}
		/*
		for(int i=0;i<)
		do {
			lixo2 = rand.nextInt(horas.size());
		}while(podeNao.contains(lixo2));
		diaNovo.setHoras(horas);
		*/
		return diaNovo;
		
	}
	
	private MetodoEstudo gerarMetodoEstudo(long perfilId) {
		
		Perfil perfil = perfilRepository.findById(perfilId).orElseThrow(
				()-> new RuntimeException("perfil não encontrado"));
		MetodoEstudo metodo = null;
		int quantidade = 0;
		List<MetodoEstudo> metodosEstudos = estudoRepository.findAll();
		for(MetodoEstudo m : metodosEstudos) {
			int cont = MetodoEstudoService.contarProblemas(perfil.getProblemas(), m.getProblemas());
			if(cont > quantidade) {
				metodo = m;
				quantidade = cont;
			}
		}
		return metodo;
	}
	
	private List<Dia> calcularHoras(List<RotinaDia> diasApos) {
		List<Dia> dias = new LinkedList<>();
		
		for(RotinaDia dia : diasApos) {
			List<Hora> horas = new LinkedList<>();
			Dia d = new Dia();
			d.setDia(dia.getDataDia().getDayOfWeek().toString());
			List<HorarioDia> horario = horarioDiaRepository.findByRotinaDia(dia);
			int avaliacaoTotal = 0;
			for(HorarioDia h : horario){
				Hora hora = new Hora();
				hora.setHora(h.getHora());
				hora.setAvaliacao(h.getAvaliacao());
				hora.setProblemas(contarProblemas(h));
				hora.setFitness(calcularFitness(hora));
				avaliacaoTotal += h.getAvaliacao(); 
				horas.add(hora);
			}
			d.setHoras(horas);
			d.setAvaliacaoTotal(avaliacaoTotal/d.getHoras().size());
			dias.add(d);
			avaliacaoTotal = 0;
		}
		return dias;
	}
	private int calcularFitness(Hora hora) {
		return hora.getAvaliacao()/ ( hora.getAvaliacao()+ hora.getProblemas());
	}
	private Hora procurarHora(List<Hora> horas, String hora) {
		for(Hora h : horas) {
			if(h.getHora().equals(hora)) {
				return h;
			}
		}
		throw new RuntimeException("não existe hora nessa lista");
	}
	private int contarProblemas(HorarioDia dia) {
		int problema = 0;
		for(Problema p : dia.getProblemas()){
			problema += RecomendacaoService.converterPeso(p.getNome());
		}
		return problema;
	}
	private boolean contemHora(String hora, List<Hora> horas) {
		boolean flag = false;
		for(Hora h : horas) {
			if(h.getHora().equals(hora)) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}
	
	private List<RotinaDia> diasApos(List<RotinaDia> dias, LocalDate data){
		
		List<RotinaDia> diasApos = new LinkedList<>();
		for(RotinaDia d : dias) {
			if(data.isAfter(d.getDataDia()) || data.isEqual(d.getDataDia())) {
				diasApos.add(d);
			}
		}
		return diasApos;
	}
	
	
}
