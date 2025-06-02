package br.com.api_se.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api_se.entities.RotinaEsperada;

@Repository
public interface RotinaEsperadaRepository extends JpaRepository<RotinaEsperada, Long> {

}
