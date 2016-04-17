/**
 * 
 */
package teste.edgardleal.engine;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.edgardleal.engine.Vetor;


/**
 * @author edgard.leal
 *
 * @version $Revision: 1.0 $
 */
public class TestVetor extends TestCase {
  static Vetor a, b;

  /**
  
   * @throws Exception
   * @throws java.lang.Exception */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    System.out.println("setUpBeforeClass");
  }

  /**
  
   * @throws Exception
   * @throws java.lang.Exception */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    System.out.println("tearDownAfterClass");
  }

  /**
  
   * @throws Exception
   * @throws java.lang.Exception */
  @Before
  public void setUp() throws Exception {}

  /**
   * Test method for {@link com.professordelphi.engine.Vetor#setXY(double, double)}.
   */
  @Test
  public void testSetXY() {
    Vetor a = new Vetor();
    a.setRaio(5);
    a.setRaio(.0);
    System.out.println(a);

  }

  /**
   * Test method for {@link com.professordelphi.engine.Vetor#inverter()}.
   */
  @Test
  public void testInverter() {
    int maximo_iteracoes = 0;
    a = new Vetor();

    b = new Vetor(-10, -10);
    a.setRaio(5);// acelera��o
    b.setRaio(0.8);// resistencia
    while (!(a.getModulo() == 0 || maximo_iteracoes >= 1000)) {
      a.setDirecao(Math.random() * Math.PI * 2);
      b.setDirecao(a.getDirecao());
      a.subtrair(b);
      maximo_iteracoes++;
    }
    System.out.println("Itera��es : " + maximo_iteracoes);
    System.out.println("Aceleracao : " + a);
    System.out.println("Atrito : " + b);
  }

}
