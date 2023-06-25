package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
