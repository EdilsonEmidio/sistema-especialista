package br.com.api_se.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api_se.entities.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

}
