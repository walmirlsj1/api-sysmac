package br.com.limac.sysmac.domain.service;


import br.com.limac.sysmac.domain.exception.CidadeNaoEncontradoException;
import br.com.limac.sysmac.domain.exception.EntidadeEmUsoException;
import br.com.limac.sysmac.domain.model.Cidade;
import br.com.limac.sysmac.domain.model.Estado;
import br.com.limac.sysmac.domain.repository.CidadeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class CadastroCidadeService {
    private static final String MSG_CIDADE_EM_USO
            = "Cidade com código %d não pode ser removida, pois está em uso.";
    private final CidadeRepository cidadeRepository;
    private final CadastroEstadoService cadastroEstadoService;

    private void validarEstado(Cidade cidade) {
        Estado estado = cadastroEstadoService.buscarOuFalhar(cidade.getEstado().getId());
        cidade.setEstado(estado);
    }

    @Transactional
    public Cidade inserir(Cidade cidade) {
        validarEstado(cidade);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Cidade atualizar(Cidade cidade) {
        validarEstado(cidade);
        cidade.setDataAtualizacao(OffsetDateTime.now());
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
            cidadeRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradoException(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, cidadeId));
        }

    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradoException(cidadeId));
    }


}
