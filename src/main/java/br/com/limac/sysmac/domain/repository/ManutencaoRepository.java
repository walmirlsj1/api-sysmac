package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

}
