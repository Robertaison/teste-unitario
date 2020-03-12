import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    private Leilao leilao;
    private Usuario compradorUm;
    private Usuario compradorDois;

    @Before
    public void contextLoad(){
        this.leilao = new Leilao("Macbook Pro 15");
        this.compradorUm = new Usuario("CompradorUm");
        this.compradorDois = new Usuario("CompradorDois");
    }

    @Test
    public void deveReceberUmLance() {
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(compradorUm, 2000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosLances() {
        leilao.propoe(new Lance(compradorUm, 2000.0));
        leilao.propoe(new Lance(compradorDois, 3000.0));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        leilao.propoe(new Lance(compradorUm, 2000.0));
        leilao.propoe(new Lance(compradorUm, 3000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(compradorUm, 2000.0));
        leilao.propoe(new Lance(compradorDois, 3000.0));
        leilao.propoe(new Lance(compradorUm, 4000.0));
        leilao.propoe(new Lance(compradorDois, 5000.0));
        leilao.propoe(new Lance(compradorUm, 6000.0));
        leilao.propoe(new Lance(compradorDois, 7000.0));
        leilao.propoe(new Lance(compradorUm, 8000.0));
        leilao.propoe(new Lance(compradorDois, 9000.0));
        leilao.propoe(new Lance(compradorUm, 10000.0));
        leilao.propoe(new Lance(compradorDois, 11000.0));

        // deve ser ignorado
        leilao.propoe(new Lance(compradorUm, 12000.0));

        assertEquals(10, leilao.getLances().size());
        assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
    }

    @Test
    public void deveDobrarUltimoLanceDoUsuario() {
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(compradorUm, 2000.0));
        leilao.propoe(new Lance(compradorDois, 3000.0));
        leilao.propoe(new Lance(compradorUm, 4000.0));
        leilao.propoe(new Lance(compradorDois, 5000.0));
        leilao.propoe(new Lance(compradorUm, 6000.0));
        leilao.propoe(new Lance(compradorDois, 7000.0));
        leilao.propoe(new Lance(compradorUm, 8000.0));
        leilao.propoe(new Lance(compradorDois, 9000.0));
        leilao.propoe(new Lance(compradorUm, 10000.0));
        leilao.propoe(new Lance(compradorDois, 11000.0));

        Double ultimoLanceDoUsuarioDobrado = leilao.dobraLance(compradorUm);
        Double valorEsperado = 20000.0;
        assertEquals(valorEsperado, ultimoLanceDoUsuarioDobrado);
    }
}
