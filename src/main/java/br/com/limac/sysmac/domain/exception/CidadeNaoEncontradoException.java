package br.com.limac.sysmac.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {
    public CidadeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public CidadeNaoEncontradoException(Long id) {
        this(String.format("Não existe uma cidade cadastrada com código %d", id));
    }
}
