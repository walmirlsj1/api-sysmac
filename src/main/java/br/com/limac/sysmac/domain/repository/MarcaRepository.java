package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.Cliente;
import br.com.limac.sysmac.domain.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
