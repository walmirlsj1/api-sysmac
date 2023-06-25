package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.ContaReceber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaReceberRepository extends JpaRepository<ContaReceber, Long> {

}
