package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.ChamadoAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoAtividadeRepository extends JpaRepository<ChamadoAtividade, Long> {

}
