package br.com.limac.sysmac.domain.exception;

public class BancoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public BancoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public BancoNaoEncontradoException(Long bancoId) {
        this(String.format("Não existe um cadastro de banco com código %d", bancoId));
    }
}
