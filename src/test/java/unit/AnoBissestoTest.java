package unit;

import br.com.caelum.leilao.desafio.AnoBissexto;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AnoBissestoTest {

    @Test
    public void deveTestarMultiplosDeQuatroEPassar(){
        assertTrue(AnoBissexto.ehBissexto(16));
    }

    @Test
    public void deveTestarMultiplosDeCemEFalhar(){
        assertTrue(!AnoBissexto.ehBissexto(100));
    }

    @Test
    public void deveTestarMultiplosDeQuatrocentrosEPassar(){
        assertTrue(AnoBissexto.ehBissexto(2000));
    }
}
