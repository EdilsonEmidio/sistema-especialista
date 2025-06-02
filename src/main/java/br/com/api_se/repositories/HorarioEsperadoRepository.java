package br.com.api_se.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api_se.entities.HorarioEsperado;

@Repository
public interface HorarioEsperadoRepository extends JpaRepository<HorarioEsperado, Long>{

}
