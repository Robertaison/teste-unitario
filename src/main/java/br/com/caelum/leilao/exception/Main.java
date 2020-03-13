package br.com.caelum.leilao.exception;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class Main {

    public static void main(String[] args) {
        Leilao leilao = new Leilao("Play 4");
        leilao.propoe(new Lance(new Usuario("maria"), 1.0));
        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);
    }
}
