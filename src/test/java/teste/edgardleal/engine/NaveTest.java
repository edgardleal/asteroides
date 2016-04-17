package teste.edgardleal.engine;

import junit.framework.TestCase;

import org.junit.Test;

/**
 */
public class NaveTest extends TestCase {
  GhostSprite nave;
  long tempo_para_deslocamento = 1000l;
  int posicao_inicial = 200, posicao_final = 140;

  private void start() {
    nave = new GhostSprite(null);
    nave.addQuadro(1, 1, 10, 10);
    nave.setWidth(30);
    nave.setHeight(30);

    nave.setPasso(1.5);
    nave.vDireita.setXY(.2, 0);
    nave.atrito.setRaio(0.002);
    nave.vEsquerda.setXY(-0.2d, 0);
    nave.aceleracao.setRaioLimite(8);
    nave.aceleracao.setXY(.0, .0);
  }

  private void end() {
    nave = null;
  }

  @Test
  public void testDireita() {
    start();
    System.out.println("Localiza��o antes de mover: " + nave.getLeft());
    System.out.println("Antes de mover para a direita: " + nave.aceleracao);
    nave.direita();
    try {
      Thread.sleep(tempo_para_deslocamento);
    } catch (Exception e) {
      // TODO: handle exception
    }
    System.out.println("Ap�s mover para a direira: " + nave.aceleracao);
    System.out.println("Localiza��o ap�s a movimenta��o: " + nave.getLeft());
    assertEquals(posicao_final, nave.getLeft() + 120L);
    end();
  }

  @Test
  public void testEsquerda() {
    start();
    nave.esquerda();
    assertEquals(posicao_final, nave.getLeft());
    end();
  }

  /**
   * Method main.
   * @param args String[]
   */
  public static void main(String[] args) {
    NaveTest teste = new NaveTest();
    teste.testDireita();
  }

}
