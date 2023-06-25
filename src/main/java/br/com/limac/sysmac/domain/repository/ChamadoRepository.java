package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
