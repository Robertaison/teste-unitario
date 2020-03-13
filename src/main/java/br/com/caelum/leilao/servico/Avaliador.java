package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.exception.LeilaoSemLanceException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private List<Lance> maiores;

    public void avalia(Leilao leilao){

        if(leilao.getLances().size() == 0){
            throw new LeilaoSemLanceException();
        }

        for (Lance lance : leilao.getLances()) {
            if(lance.getValor() > maiorDeTodos) {
                maiorDeTodos = lance.getValor();
            }if (lance.getValor() < menorDeTodos) {
                menorDeTodos = lance.getValor();
            }
        }

        pegaOsMaioresNo(leilao);
    }

    public double getMaiorValor() {
        return maiorDeTodos;
    }

    public double getMenorValor() {
        return menorDeTodos;
    }

    public List<Lance> getTresMaiores() {
        return this.maiores;
    }

    public Double media(List<Lance> lances) {
        Double total = 0.0;
        for(Lance lance: lances){
            total += lance.getValor();
        }

        Double media = total/lances.size();
        return media;
    }

    private void pegaOsMaioresNo(Leilao leilao) {
        maiores = new ArrayList<>(leilao.getLances());
        maiores.sort(
                Comparator
                        .comparingDouble(Lance::getValor)
                        .reversed()
        );

        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
    }
}
