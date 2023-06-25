package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

}
