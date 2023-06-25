package br.com.limac.sysmac.domain.exception;

public class ContaNaoEncontradaException extends EntidadeNaoEncontradaException {
    public ContaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public ContaNaoEncontradaException(Long contaId) {
        this(String.format("Não existe um cadastro de conta com código %d", contaId));
    }
}
