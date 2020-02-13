import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.Assert;
import org.junit.Test;

public class TesteDoAvaliador {

    @Test
    public void teste(){

        //Cenario
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");
        Usuario lucas = new Usuario("Lucas");

        Leilao leilao = new Leilao("PS 4 Pro");

        leilao.propoe(new Lance(joao, 300));
        leilao.propoe(new Lance(maria, 400));
        leilao.propoe(new Lance(lucas, 250.50));

        //Acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //Validacao
        double menorValorEsperado = 250.5;
        double maiorValorEsperado = 400
                ;
        Assert.assertEquals(maiorValorEsperado, leiloeiro.getMaiorValor(), 0.000001);
        Assert.assertEquals(menorValorEsperado, leiloeiro.getMenorValor(), 0.000001);

    }
}
