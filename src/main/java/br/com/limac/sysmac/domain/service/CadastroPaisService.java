package br.com.limac.sysmac.domain.service;

import br.com.limac.sysmac.domain.exception.EntidadeEmUsoException;
import br.com.limac.sysmac.domain.exception.PaisNaoEncontradoException;
import br.com.limac.sysmac.domain.model.Pais;
import br.com.limac.sysmac.domain.repository.PaisRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class CadastroPaisService {
    private static final String MSG_PAIS_EM_USO
            = "Pais com código %d não pode ser removida, pois está em uso.";
    private final PaisRepository paisRepository;

    @Transactional
    public Pais inserir(Pais pais) {
        return paisRepository.save(pais);
    }

    @Transactional
    public Pais atualizar(Pais pais) {
        pais.setDataAtualizacao(OffsetDateTime.now());
        return paisRepository.save(pais);
    }

    @Transactional
    public void excluir(Long paisId) {
        try {
            paisRepository.deleteById(paisId);
            paisRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new PaisNaoEncontradoException(paisId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_PAIS_EM_USO, paisId));
        }

    }

    public Pais buscarOuFalhar(Long paisId) {
        return paisRepository.findById(paisId).orElseThrow(() -> new PaisNaoEncontradoException(paisId));
    }


}
