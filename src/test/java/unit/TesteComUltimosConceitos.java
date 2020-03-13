package unit;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import factory.CriadorDeLeilao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static matcher.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class TesteComUltimosConceitos {

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

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertThat(maiores.size(), equalTo(3));
        assertThat(maiores.get(0).getValor(), equalTo(400.0));
        assertThat(maiores.get(1).getValor(), equalTo(300.0));
        assertThat(maiores.get(2).getValor(), equalTo(200.0));
    }

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").constroi();
        assertEquals(0, leilao.getLances().size());

        Lance lance = new Lance(new Usuario("Steve Jobs"), 2000.0);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, temUmLance(lance));
    }
}
