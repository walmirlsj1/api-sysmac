package br.com.limac.sysmac.domain.exception;

public class ContatoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ContatoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public ContatoNaoEncontradoException(Long id) {
        this(String.format("Não existe um contato cadastrado com código %d", id));
    }
}
