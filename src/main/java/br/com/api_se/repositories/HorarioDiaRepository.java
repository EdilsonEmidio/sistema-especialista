package br.com.api_se.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api_se.entities.HorarioDia;
import br.com.api_se.entities.RotinaDia;

import java.util.List;


@Repository
public interface HorarioDiaRepository extends JpaRepository<HorarioDia, Long>{

	
	List<HorarioDia> findByRotinaDia(RotinaDia rotinaDia);
}
