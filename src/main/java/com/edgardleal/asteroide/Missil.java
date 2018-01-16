package com.edgardleal.asteroide;

import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.edgardleal.engine.Colidivel;
import com.edgardleal.engine.MediaCenter;
import com.edgardleal.engine.Sprite;

public class Missil extends Sprite {
  private boolean read = true;

  /**
   * Constructor for Missil.
   * 
   * @param img Image
   */
  public Missil(Image img) {
    super(img);
  }

  /**
   * <p>
   * Constructor for Missil.
   * </p>
   *
   */
  public Missil() {
    super(MediaCenter.instance().getImage("img/misseis.png"));
    setX1(250);
    setY1(150);
    setWidth(14);
    setHeight(26);
    for (int i = 0; i < 13; i++) {
      addQuadro(0, 62 * i, 32, 62 * (i + 1));
    }
    atrito.setRaio(0.3);
    hide();
    setExibeLife(false);
  }

  public void show() {
    setVisible(true);
    setSolid(true);
    aceleracao.setXY(0, -4.0);
    read = false;
  }

  public void hide() {
    setVisible(false);
    aceleracao.setXY(0, 0);
    setSolid(false);
    read = true;
  }

  /**
   * Method colidiu.
   * 
   * @param c Colidivel
   * @see com.edgardleal.engine.Colidivel#colidiu(Colidivel)
   */
  @Override
  public void colidiu(Colidivel c) {
    if (c instanceof Nave) {
      return;
    }
    if (c instanceof Asteroide) {
      // TODO: modificar para que o asteriode seja notificado e processe a colisão
      ((Asteroide) c).ataque(41);
    }
    explodir();
  }

  public void explodir() {
    hide();
  }

  /**
   * Method isRead.
   * 
   * @return boolean
   */
  public boolean isRead() {
    return read;
  }



  /**
   * Method paint.
   * 
   * @param g Graphics
   * @see com.edgardleal.engine.Printable#paint(Graphics)
   */
  @Override
  public void paint(Graphics g) {
    if (!isVisible()) {
      return;
    } else {
      super.paint(g);
    }
  }

  /**
   * Method update.
   * 
   * @param lista ArrayList<Colidivel>
   * @see com.edgardleal.engine.Tickeable#update(ArrayList<Colidivel>)
   */
  @Override
  public void update(ArrayList<Colidivel> lista) {
    mover();
    if (getY1() < 0 || getY() > 500) {
      hide();
    }
    nextFrame();// muda para o proximo quadro
  }
}
