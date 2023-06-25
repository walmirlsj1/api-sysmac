package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

}
