package br.com.limac.sysmac.domain.exception;

public class PaisNaoEncontradoException extends EntidadeNaoEncontradaException {
    public PaisNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public PaisNaoEncontradoException(Long id) {
        this(String.format("Não existe um pais cadastrado com código %d", id));
    }
}
