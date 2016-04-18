package com.edgardleal.telas;

import java.awt.Color;
import java.awt.Image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edgardleal.asteroide.Asteroide;
import com.edgardleal.asteroide.Missil;
import com.edgardleal.asteroide.Nave;
import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.CenarioListener;
import com.edgardleal.engine.Colidivel;
import com.edgardleal.engine.MediaCenter;
import com.edgardleal.engine.Movable;
import com.edgardleal.engine.Printable;
import com.edgardleal.engine.Sprite;

/**
 */
public class Fase01 extends Cenario {
  private static final Logger LOGGER = LoggerFactory.getLogger(Fase01.class);
  private Image imgFundo;
  public Nave nave;
  public Asteroide asteroide1, asteroide2;
  public Missil missil;
  private int perdas = 0, acertos = 0;

  /**
   * Constructor for Fase01.
   * 
   * @param applet JApplet
   */
  public Fase01(CenarioListener listener) {
    super(listener);
    try {
      nave = new Nave(this);
      addPrintable(nave);// diciona a nave na lista de objetos da fase
      for (int i = 0; i < 10; i++) {
        addPrintable(new Asteroide(this));
      }

      // addPrintable(new Mosquito(applet, this));

      imgFundo = MediaCenter.instance().getImage("img/fundo ok.jpg");
      setImgFundo(imgFundo);
      setBounds(0, 0, 500, 500);

      this.repaint();

    } catch (Exception e) {
      LOGGER.error("erro ao iniciar o fase1", e);
    }
  }

  /**
   * Method addPrintable.
   * 
   * @param p Printable
   */
  @Override
  public void addPrintable(Printable p) {
    if (p instanceof Colidivel)
      addColidivel((Colidivel) p);
    super.addPrintable(p);
  }

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  /**
   * Registra um ataque , a intencidade diz quando de life a nave ir� perder.
   * 
   * @param intencidade
   */
  public void perda(int intencidade) {
    nave.ataque(intencidade);
    perdas++;
  }

  public void acerto() {
    acertos++;
  }

  /**
   * Method paint.
   * 
   * @param g java.awt.Graphics
   */
  @Override
  public void paint(java.awt.Graphics g) {
    super.paint(g);
    g.setColor(Color.black);
    g.drawString("Pontos : " + acertos, 10, 10);
    g.drawString("Perdas : " + perdas, 10, 30);
  }

  /**
   * Method ataque.
   * 
   * @param m Movable
   * @param intencidade int
   * @param x int
   * @param y int
   * @param x2 int
   * @param y2 int
   */
  @Override
  public void ataque(Movable m, int intencidade, int x, int y, int x2, int y2) {
    for (Printable p : getPrintables()) {
      if (p instanceof Asteroide && p != m) {
        if (x >= ((Sprite) p).getX1() && x2 <= ((Sprite) p).getX2()) {
          ((Asteroide) p).ataque(intencidade);
        }
      }
    }
  }
}
