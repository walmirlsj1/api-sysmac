package br.com.limac.sysmac.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {
    public EntidadeNaoEncontradaException(String mensagem){
        super((mensagem));
    }
}
