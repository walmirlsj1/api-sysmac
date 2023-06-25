package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

}
