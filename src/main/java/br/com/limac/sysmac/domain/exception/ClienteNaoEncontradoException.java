package br.com.limac.sysmac.domain.exception;

public class ClienteNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ClienteNaoEncontradoException(Long clienteId) {
        this(String.format("Não existe um cadastro de cliente com código %d", clienteId));
    }
}
