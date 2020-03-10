import br.com.caelum.leilao.desafio.MatematicaMaluca;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MatematicaMalucaTest {

    @Test
    public void contaMalucaTesteMaiorQueTrinta(){
        int num = 43 * 4;
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        assertEquals(num, matematicaMaluca.contaMaluca(43));
    }

    @Test
    public void contaMalucaTesteMaiorQueDez(){
        int num = 15 * 3;
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        assertEquals(num, matematicaMaluca.contaMaluca(15));
    }

    @Test
    public void contaMalucaTesteMenorQueDez(){
        int num = 2 * 2;
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        assertEquals(num, matematicaMaluca.contaMaluca(2));
    }
}
