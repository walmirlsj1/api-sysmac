package br.com.limac.sysmac.domain.exception;

public class ContaAPagarNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ContaAPagarNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ContaAPagarNaoEncontradoException(Long id) {
        this(String.format("Não existe conta a pagar com código %d", id));
    }
}
