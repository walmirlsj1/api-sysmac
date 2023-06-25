package br.com.limac.sysmac.domain.service;

import br.com.limac.sysmac.domain.exception.ClienteNaoEncontradoException;
import br.com.limac.sysmac.domain.exception.EntidadeEmUsoException;
import br.com.limac.sysmac.domain.model.Cliente;
import br.com.limac.sysmac.domain.model.Contato;
import br.com.limac.sysmac.domain.model.Endereco;
import br.com.limac.sysmac.domain.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CadastroClienteService {
    private static final String MSG_CLIENTE_EM_USO
            = "Cliente com código %d não pode ser removida, pois está em uso.";
    private final ClienteRepository clienteRepository;
    private final CadastroCidadeService cadastroCidadeService;

    @Transactional
    public Cliente inserir(Cliente cliente) {
        validarCliente(cliente);

        return clienteRepository.save(cliente);
    }

    private void validarCliente(Cliente cliente) {
        List<Contato> contatos = cliente.getContatos();

        cliente.setContatos(null);

        for (Contato cts: contatos) {
            cts.setCliente(cliente);
        }
        cliente.setContatos(contatos);


//        List<Conjuge> conjuges = cliente.getConjuges();
//
//        cliente.setConjuges(null);
//
//        for (Conjuge cje: conjuges) {
//            cje.setCliente(cliente);
//        }
//        cliente.setConjuges(conjuges);

        List<Endereco> enderecos = cliente.getEnderecos();

        cliente.setEnderecos(null);

        for (Endereco edc: enderecos) {
            edc.setCliente(cliente);
            edc.setCidade(cadastroCidadeService.buscarOuFalhar(edc.getCidade().getId()));
        }
        cliente.setEnderecos(enderecos);


    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
        cliente.setDataAtualizacao(OffsetDateTime.now());
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
            clienteRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new ClienteNaoEncontradoException(clienteId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CLIENTE_EM_USO, clienteId));
        }

    }

    public Cliente buscarOuFalhar(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
    }


}
