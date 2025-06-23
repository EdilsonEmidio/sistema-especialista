package br.com.api_se.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api_se.entities.RotinaDia;
import br.com.api_se.entities.RotinaEsperada;
import br.com.api_se.entities.Usuario;

import java.util.List;


@Repository
public interface RotinaDiaRepository extends JpaRepository<RotinaDia, Long> {

	
	List<RotinaDia> findByRotinaEsperada(RotinaEsperada rotinaEsperada);
	
	List<RotinaDia> findByUsuario(Usuario usuario);
	
}
