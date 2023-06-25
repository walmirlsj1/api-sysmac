package br.com.limac.sysmac.domain.exception;

public class PlanoContaNaoEncontradoException extends EntidadeNaoEncontradaException {
    public PlanoContaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public PlanoContaNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro de plano de contas com código %d", id));
    }
}
