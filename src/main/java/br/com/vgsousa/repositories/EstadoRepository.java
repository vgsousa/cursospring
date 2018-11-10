package br.com.vgsousa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vgsousa.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	
}
