package com.edgardleal.engine;

import java.util.ArrayList;


/**
 */
public class ColisionCenter {
  private ArrayList<Colidivel> lista;

  public ColisionCenter() {
    super();
  }

  /**
   * Method getRaio.
   * @param c Colidivel
   * @return int
   */
  private int getRaio(Colidivel c) {
    return c.getWidth() / 2;
  }

  /**
   * Method getDistancia.
   * @param a Colidivel
   * @param b Colidivel
   * @return int
   */
  private int getDistancia(Colidivel a, Colidivel b) {
    int x1 = a.getX() + a.getWidth() / 2, y1 = a.getY() + a.getHeight() / 2, x2 =
        b.getX() + b.getWidth() / 2, y2 = b.getY() + b.getHeight() / 2;

    return ((int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
  }

  /**
   * Verifica se h� algum objeto em estado de colis�o , caso haja, os dois objetos em colis�o ser�o
   * notificados;
   * 
   * @return boolean
   */
  public boolean verifica() {
    boolean result = false;
    for (int i = 0; i < getLista().size(); i++) {
      if (!getLista().get(i).isSolid())
        continue;
      for (int j = 0; j < getLista().size(); j++) {
        if (!getLista().get(j).isSolid() || getLista().get(i) == getLista().get(j))
          continue;
        // System.out.println("Comparando : " + getLista().get(i));
        // System.out.println("E " + getLista().get(j) + "\n");
        if (colidindo(getLista().get(i), getLista().get(j))) {
          getLista().get(i).colidiu(getLista().get(j));
          result = true;
        }// if
      }// for j
    }// for i
    return result;
  }// verifica

  /**
   * Method colidindo.
   * @param a Colidivel
   * @param b Colidivel
   * @return boolean
   */
  private boolean colidindo(Colidivel a, Colidivel b) {
    return getDistancia(a, b) <= getRaio(a) + getRaio(b);
  }

  /**
   * Adiciona um item a lista para ser usado na virifica��o de colis�o, Para que seja verificada a
   * colis�o � preciso que os objetos tenham sua propiedade isSolid esteja com o valor true.
   * 
   * @param c Colidivel
   */
  public void add(Colidivel c) {
    getLista().add(c);
  }

  /**
   * Retorna a lista de todos os objetos adicionados para a verifica��o de colis�o.
   * 
  
   * @return ArrayList<Colidivel>
   */
  public ArrayList<Colidivel> getLista() {
    if (lista == null) {
      lista = new ArrayList<Colidivel>();
    }
    return lista;
  }

  /**
   * M�todo implementado apenas para verifica��o de funcionalidade de forma local.
   * 
   * @param args
   */
  public static void main(String[] args) {
    ColisionCenter c = new ColisionCenter();
    Colidivel a = new Colidivel() {

      @Override
      public void colidiu(Colidivel c) {}

      @Override
      public int getHeight() {
        return 10;
      }

      @Override
      public int getRaio() {
        return 5;
      }

      @Override
      public int getWidth() {
        return 10;
      }

      @Override
      public int getX() {
        return 0;
      }

      @Override
      public int getY() {
        return 0;
      }

      @Override
      public boolean isSolid() {
        return false;
      }
    };
    Colidivel b = new Colidivel() {

      @Override
      public void colidiu(Colidivel c) {}

      @Override
      public int getHeight() {
        return 10;
      }

      @Override
      public int getRaio() {
        return 5;
      }

      @Override
      public int getWidth() {
        return 10;
      }

      @Override
      public int getX() {
        return 20;
      }

      @Override
      public int getY() {
        return 0;
      }

      @Override
      public boolean isSolid() {
        return true;
      }

    };
    System.out.println(c.getDistancia(a, b));
    System.out.println("Colidindo : " + c.colidindo(a, b));
  }
}
