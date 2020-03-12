package br.com.caelum.leilao.exception;

public class LeilaoSemLanceException extends RuntimeException {

    public LeilaoSemLanceException() {
        super("Ainda não há lances para esse leilao");
    }
}
