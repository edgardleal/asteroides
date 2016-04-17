package com.edgardleal.asteroide;

import java.net.URL;

import javax.swing.JApplet;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.Sprite;

public class Mosquito extends Sprite {

  /**
   * Constructor for Mosquito.
   * @param applet JApplet
   * @param c Cenario
   * @throws Exception
   */
  public Mosquito(JApplet applet, Cenario c) throws Exception {
    super(applet.getImage(new URL(applet.getDocumentBase(), "img/mosquito.png")));

    addQuadro(0, 0, 32, 32); // - 0
    addQuadro(32, 0, 64, 32); // - 1

    addQuadro(64, 0, 96, 32); // - 2
    addQuadro(96, 0, 128, 32); // - 3
    addQuadro(128, 0, 160, 32); // - 4

    this.setHeight(32);
    this.setWidth(32);
    this.reset();
    setPasso(0.5);
    atrito.setRaio(0.9d);
    aceleracao.setRaioLimite(1);
    c.addPrintable(this);
  }

  /**
   * Reinicia o Objeto
   * 
   */
  public void reset() {
    this.setLocation((int) ((Math.random() * 500) - this.getWidth()) + getWidth(),
        (int) (Math.random() * 60));
    setLife(100);
  }

  /**
   * Method paint.
   * @param g java.awt.Graphics
   * @see com.edgardleal.engine.Printable#paint(java.awt.Graphics)
   */
  @Override
  public void paint(java.awt.Graphics g) {
    super.paint(g);
    /*
     * if(getTop()>=500-100) ataque(50);
     * 
     * if(getTime()%5==0) if(!explodindo){ if(getTime()%10==0)
     * setQuadroAtual(getQuadroAtual()==0?1:0); descer(); } else{ if (getQuadroAtual()<4)
     * if(getTime()%20==0) setQuadroAtual(getQuadroAtual()+1); else descer(); else reset(); }
     */
  }


  /**
   * Method ataque.
   * @param intencidade int
   * @return boolean
   */
  @Override
  public boolean ataque(int intencidade) {
    super.ataque(intencidade);
    return false;
  }

}
