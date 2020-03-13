package unit;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.FiltroDeLances;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FiltroDeLancesTest {

    private FiltroDeLances filtro;
    private Usuario joao;

    @Before
    public void contextLoad(){
        this.filtro = new FiltroDeLances();
        this.joao = new Usuario("Joao");
    }

    @Test
    public void deveSelecionarLancesEntre1000E3000() {

        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,2000.0),
                new Lance(joao,1000.0),
                new Lance(joao,3000.0),
                new Lance(joao, 800.0)));

        assertEquals(1, resultado.size());
        assertEquals(2000, resultado.get(0).getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesEntre500E700() {

        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,600.0),
                new Lance(joao,500.0),
                new Lance(joao,700.0),
                new Lance(joao, 800.0)));

        assertEquals(1, resultado.size());
        assertEquals(600, resultado.get(0).getValor(), 0.00001);
    }


    @Test
    public void deveSelecionarLancesAcimaDe5000() {

        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,5000.0),
                new Lance(joao,5001.0),
                new Lance(joao,6000.0),
                new Lance(joao, 800.0)));

        assertEquals(2, resultado.size());
        assertEquals(5001.0, resultado.get(0).getValor(), 0.00001);
        assertEquals(6000.0, resultado.get(1).getValor(), 0.00001);
    }
}
