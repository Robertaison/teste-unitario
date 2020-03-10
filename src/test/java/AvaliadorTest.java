import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AvaliadorTest {

    @Test
    public void deveEntenderLancesEmOrdemCrescente(){

        //Cenario
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");
        Usuario lucas = new Usuario("Lucas");

        Leilao leilao = new Leilao("PS 4 Pro");

        leilao.propoe(new Lance(joao, 300));
        leilao.propoe(new Lance(maria, 400));
        leilao.propoe(new Lance(lucas, 200));

        //Acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //Validacao
        double menorValorEsperado = 200;
        double maiorValorEsperado = 400;
        double mediaEsperada = 300;

        assertEquals(maiorValorEsperado, leiloeiro.getMaiorValor(), 0.000001);
        assertEquals(menorValorEsperado, leiloeiro.getMenorValor(), 0.000001);
        assertEquals(mediaEsperada, leiloeiro.media(leilao.getLances()), 0.000001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Usuario joao = new Usuario("João");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 1000.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(1000.0, leiloeiro.getMaiorValor(), 0.0001);
        assertEquals(1000.0, leiloeiro.getMenorValor(), 0.0001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveEntenderLancesEmOrdemAleatoriaComOutrosValores(){

        //Cenario
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");
        Usuario lucas = new Usuario("Lucas");
        Usuario gabi = new Usuario("Gabi");
        Usuario leo = new Usuario("Leo");
        Usuario nati = new Usuario("Nati");

        Leilao leilao = new Leilao("PS 4 Pro");

        leilao.propoe(new Lance(joao, 200));
        leilao.propoe(new Lance(maria, 450));
        leilao.propoe(new Lance(lucas, 120));
        leilao.propoe(new Lance(gabi, 700));
        leilao.propoe(new Lance(leo, 630));
        leilao.propoe(new Lance(nati, 230));


        //Acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //Validacao
        double menorValorEsperado = 120;
        double maiorValorEsperado = 700;

        assertEquals(maiorValorEsperado, leiloeiro.getMaiorValor(), 0.000001);
        assertEquals(menorValorEsperado, leiloeiro.getMenorValor(), 0.000001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLancesEntreCinco() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));
        leilao.propoe(new Lance(maria, 250.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(250.0, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveEncontrarOsDoisLances() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(2, maiores.size());
        assertEquals(200.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(100.0, maiores.get(1).getValor(), 0.00001);
    }

    @Test
    public void deveTerListaVazia() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
        assertEquals(true, maiores.isEmpty());

    }
}
