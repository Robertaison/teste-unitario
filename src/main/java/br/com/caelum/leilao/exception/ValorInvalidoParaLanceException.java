package br.com.caelum.leilao.exception;

public class ValorInvalidoParaLanceException extends RuntimeException{
    public ValorInvalidoParaLanceException() {
        super("Valor inválido para Lance");
    }
}
