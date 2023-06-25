package br.com.limac.sysmac.domain.exception;

public class EnderecoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public EnderecoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public EnderecoNaoEncontradoException(Long id) {
        this(String.format("Não existe um endereço cadastrado com código %d", id));
    }
}
