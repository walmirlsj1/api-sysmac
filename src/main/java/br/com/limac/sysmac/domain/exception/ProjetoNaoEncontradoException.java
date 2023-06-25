package br.com.limac.sysmac.domain.exception;

public class ProjetoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ProjetoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProjetoNaoEncontradoException(Long projetoId) {
        this(String.format("Não existe um cadastro de projeto com código %d", projetoId));
    }
}
