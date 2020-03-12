package br.com.caelum.leilao.dominio;

import br.com.caelum.leilao.desafio.AnoBissexto;
import br.com.caelum.leilao.servico.Avaliador;

import java.util.List;

public class Main {

    public static void main(String[] args) {
         Usuario joao;
         Usuario maria;
         Usuario lucas;
         Usuario gabi;
         Usuario leo;
         Usuario nati;

        Leilao leilao = new Leilao("PS 4 Pro");
        Avaliador leiloeiro = new Avaliador();

        joao = new Usuario("Joao");
        maria = new Usuario("Maria");
        lucas = new Usuario("Lucas");
        gabi = new Usuario("Gabi");
        leo = new Usuario("Leo");
        nati = new Usuario("Nati");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));
        leilao.propoe(new Lance(maria, 250.0));

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        System.out.println(maiores);
    }
}
