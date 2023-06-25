package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
