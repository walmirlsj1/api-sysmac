package br.com.limac.sysmac.domain.exception;

public class ContaAReceberNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ContaAReceberNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ContaAReceberNaoEncontradoException(Long id) {
        this(String.format("Não existe conta a receber com código %d", id));
    }
}
