package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

}
