package br.com.limac.sysmac.domain.service;

import br.com.limac.sysmac.domain.exception.EntidadeEmUsoException;
import br.com.limac.sysmac.domain.exception.EstadoNaoEncontradoException;
import br.com.limac.sysmac.domain.model.Estado;
import br.com.limac.sysmac.domain.model.Pais;
import br.com.limac.sysmac.domain.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class CadastroEstadoService {
    private static final String MSG_ESTADO_EM_USO
            = "Estado com código %d não pode ser removida, pois está em uso.";
    private final EstadoRepository estadoRepository;
    private final CadastroPaisService cadastroPaisService;

    private void validarEstado(Estado estado) {
        Pais pais = cadastroPaisService.buscarOuFalhar(estado.getPais().getId());
        estado.setPais(pais);
    }

    @Transactional
    public Estado inserir(Estado estado) {
        validarEstado(estado);
        return estadoRepository.save(estado);
    }


    @Transactional
    public Estado atualizar(Estado estado) {
        validarEstado(estado);
        estado.setDataAtualizacao(OffsetDateTime.now());
        return estadoRepository.save(estado);
    }

    @Transactional
    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, estadoId));
        }

    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }


}
