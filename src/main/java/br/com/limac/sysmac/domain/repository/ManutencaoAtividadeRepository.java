package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.ManutencaoAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManutencaoAtividadeRepository extends JpaRepository<ManutencaoAtividade, Long> {

}
