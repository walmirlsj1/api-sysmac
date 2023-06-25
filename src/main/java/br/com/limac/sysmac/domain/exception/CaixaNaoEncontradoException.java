package br.com.limac.sysmac.domain.exception;

public class CaixaNaoEncontradoException extends EntidadeNaoEncontradaException {
    public CaixaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public CaixaNaoEncontradoException(Long caixaId) {
        this(String.format("Não existe um cadastro de caixa com código %d", caixaId));
    }
}
