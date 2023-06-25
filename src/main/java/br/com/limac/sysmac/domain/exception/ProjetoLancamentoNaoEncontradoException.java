package br.com.limac.sysmac.domain.exception;

public class ProjetoLancamentoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ProjetoLancamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProjetoLancamentoNaoEncontradoException(Long lancamentoId) {
        this(String.format("Não existe um cadastro de lancamento com código %d", lancamentoId));
    }
}
