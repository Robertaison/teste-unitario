package unit;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.exception.LeilaoSemLanceException;
import br.com.caelum.leilao.servico.Avaliador;
import factory.CriadorDeLeilao;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AvaliadorTest {

    private Leilao leilao;
    private Avaliador leiloeiro;
    private CriadorDeLeilao criadorDeLeilao;
    
    //Compradores
    private Usuario joao;
    private Usuario maria;
    private Usuario lucas;
    private Usuario gabi;
    private Usuario leo;
    private Usuario nati;

    @Before
    public void contextLoad(){

        this.leilao = new Leilao("PS 4 Pro");
        this.leiloeiro = new Avaliador();

        this.joao = new Usuario("Joao");
        this.maria = new Usuario("Maria");
        this.lucas = new Usuario("Lucas");
        this.gabi = new Usuario("Gabi");
        this.leo = new Usuario("Leo");
        this.nati = new Usuario("Nati");
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente(){

        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));
        leilao.propoe(new Lance(lucas, 200.0));

        leiloeiro.avalia(leilao);

        //Validacao
        double menorValorEsperado = 200;
        double maiorValorEsperado = 400;
        double mediaEsperada = 300;

        assertThat(leiloeiro.getMaiorValor(), equalTo(maiorValorEsperado));
        assertThat(menorValorEsperado, equalTo(leiloeiro.getMenorValor()));
        assertThat(mediaEsperada, equalTo(leiloeiro.media(leilao.getLances())));
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {

        leilao.propoe(new Lance(joao, 1000.0));

        leiloeiro.avalia(leilao);

        assertEquals(1000.0, leiloeiro.getMaiorValor(), 0.0001);
        assertEquals(1000.0, leiloeiro.getMenorValor(), 0.0001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveEntenderLancesEmOrdemAleatoriaComOutrosValores(){

        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(maria, 450.0));
        leilao.propoe(new Lance(lucas, 120.0));
        leilao.propoe(new Lance(gabi, 700.0));
        leilao.propoe(new Lance(leo, 630.0));
        leilao.propoe(new Lance(nati, 230.0));

        leiloeiro.avalia(leilao);

        double menorValorEsperado = 120;
        double maiorValorEsperado = 700;

        assertEquals(maiorValorEsperado, leiloeiro.getMaiorValor(), 0.000001);
        assertEquals(menorValorEsperado, leiloeiro.getMenorValor(), 0.000001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLancesEntreCinco() {

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));
        leilao.propoe(new Lance(joao, 250.0));

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertThat(maiores, hasItems(
                new Lance(maria, 400.0),
                new Lance(joao,300.0),
                new Lance(joao, 250.0)
        ));
    }

    @Test
    public void deveEncontrarOsDoisLances() {

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(2, maiores.size());
        assertEquals(200.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(100.0, maiores.get(1).getValor(), 0.00001);
    }

    @Test(expected = LeilaoSemLanceException.class)
    public void deveLancarExcessaoCasoNaoHajaLances(){

        leiloeiro.avalia(leilao);
    }
}
