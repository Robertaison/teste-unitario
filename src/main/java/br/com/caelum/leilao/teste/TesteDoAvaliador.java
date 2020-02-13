package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

    public static void main(String[] args) {
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");
        Usuario lucas = new Usuario("Lucas");

        Leilao leilao = new Leilao("PS 4 Pro");

        leilao.propoe(new Lance(joao, 300));
        leilao.propoe(new Lance(maria, 400));
        leilao.propoe(new Lance(lucas, 250.50));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        System.out.println(leiloeiro.getMaiorValor());
        System.out.println(leiloeiro.getMenorValor());
    }
}
