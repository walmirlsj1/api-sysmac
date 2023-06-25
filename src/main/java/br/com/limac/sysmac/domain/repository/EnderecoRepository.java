package br.com.limac.sysmac.domain.repository;

import br.com.limac.sysmac.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByClienteId(Long id);
}
