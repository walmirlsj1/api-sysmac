package br.com.limac.sysmac.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public EstadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public EstadoNaoEncontradoException(Long id) {
        this(String.format("Não existe um estado cadastrado com código %d", id));
    }
}
