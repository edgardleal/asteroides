package com.edgardleal.asteroide;

import java.util.ArrayList;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.Colidivel;
import com.edgardleal.engine.MediaCenter;
import com.edgardleal.engine.Sprite;
import com.edgardleal.telas.Fase01;

/**
 * 
 * @author edgardleal
 *
 */
public class Asteroide extends Sprite {
  private static int count = 0;
  private boolean explodindo = false;
  private int id = -1;
  private Cenario c = null;

  public Asteroide(Cenario c) {
    super(MediaCenter.instance().getImage("img/enemy1.png"));
    this.id = ++count;
    getTimeline().add(0, 1, 32, 31, 0, 5); // 0
    getTimeline().add(32, 1, 32 + 32, 31, 2, 5); // 1
    getTimeline().add(67, 3, 26 + 67, 26 + 3, 3, 5); // 2
    getTimeline().add(99, 3, 26 + 99, 26 + 3, 0, 5); // 3

    /* Explos�o */
    getTimeline().add(132, 5, 154, 28, 5, 9); // 4 vai mudar para o 5
    getTimeline().add(164, 5, 187, 28, 6, 9); // 5 vai mudar para o 6
    getTimeline().add(196, 5, 219, 28, 0, 9); // 6 vai mudar para o 1

    this.setHeight(32);
    this.setWidth(32);
    this.reset();
    setPasso(0.5);
    atrito.setRaio(1.5);
    c.addPrintable(this);
    aceleracao.setRaioLimite(1.0);
    this.c = c;
  }

  public void reset() {
    this.setLocation((int) (Math.random() * (500 - this.getWidth())), (int) (Math.random() * 60));
    explodindo = false;
    aceleracao.setXY(0, 0);// zera a movimenta��o
    setLife(100);
    setSolid(true);
  }

  public boolean isExplodindo() {
    return explodindo;
  }

  private void setExplodindo(boolean explodindo) {
    this.explodindo = explodindo;
  }

  public void explodir() {
    if (explodindo) {
      return;
    }
    // setSolid(false);
    setExplodindo(true);
    getTimeline().gotoFrame(4);
  }

  @Override
  public boolean ataque(int intencidade) {
    super.ataque(intencidade);
    if (getLife() <= 0 && !explodindo) {
      ((Fase01) c).acerto();
      explodir();
    }
    return false;
  }

  /**
   * Retorna o n�mero da instancia deste objeto , toda vez que � criado um novo Asteroide , � criado
   * um identificador �nico para instacia criada.
   * 
   * @return Valor inteiro que identifica de forma �nca este objeto
   */
  public int getId() {
    return this.id;
  }


  public void colidiu(Colidivel c) {
    explodir();
  }

  public void update(ArrayList<Colidivel> lista) {
    super.update(lista);
    if (getY() > 550)
      reset();
  }

  @Override
  public void onFrameEnd(int frame) {
    if (frame == 6) {
      reset();
    }
  }
}
