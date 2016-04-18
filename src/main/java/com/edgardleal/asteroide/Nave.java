package com.edgardleal.asteroide;

import java.awt.Color;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.Colidivel;
import com.edgardleal.engine.MediaCenter;
import com.edgardleal.engine.Sprite;
import com.edgardleal.engine.Vetor;
import com.edgardleal.telas.Fase01;

public class Nave extends Sprite {

  private static final Logger LOGGER = LoggerFactory.getLogger(Nave.class);
  private boolean atirando = false;
  private Missil missil;
  private ArrayList<Missil> misseis;
  private Cenario cenario = null;
  /**
   * Indica um limite para lancamento de m�sseis.
   * 
   */
  private long timerDeLancamento = 0;

  // private Cenario cenario; pode ser utilizado posteriormente para tratamento
  // de explos�es

  /**
   * Constructor for Nave.
   * 
   * @param a JApplet
   * @param c Cenario
   * @throws MalformedURLException
   */
  public Nave(Cenario c) throws MalformedURLException {
    super(MediaCenter.instance().getImage("img/nave2.png"));
    addQuadro(10, 0, 42, 40);
    addQuadro(10, 50, 41, 90);
    addQuadro(9, 100, 41, 140);
    setLocation(250, 400);
    setWidth(24);
    setHeight(32);
    missil = new Missil();
    // this.cenario = c;
    setPasso(1.5);
    c.addPrintable(missil);
    this.cenario = c;
    vDireita.setXY(.4, 0);
    atrito.setRaio(0.02);
    vEsquerda.setXY(-0.4d, 0);
    aceleracao.setRaioLimite(8);
    aceleracao.setXY(.0, .0);
    // ---------------------------------------------
  }

  @Override
  public void direita() {
    if (getX2() >= 500) {
      return;
    } else {
      this.setLocation(getX1() + 5, getY1());
      // super.direita();
    }
  }

  public void lancar() {
    if (timerDeLancamento != 0) {
      return;
    }
    for (Missil m : getMisseis())
      if (m.isRead()) {
        m.setLocation(this.getX1() + (getWidth() / 2) - 6, this.getY1());
        m.show();
        timerDeLancamento = 3;
        break;
      }
  }

  @Override
  public void esquerda() {
    if (getX1() <= 0) {
      return;
    } else {
      // super.esquerda();
      this.setLocation(getX1() - 5, getY1());
    }
  }

  /**
   * Anula a gravidade.
   * 
   * @param gravidade Vetor
   */
  @Override
  public void mover(Vetor gravidade) {
    mover();
  }

  @Override
  public void mover() {
    if (getX2() >= 500)
      aceleracao.setXY(-0.5, 0);
    else if (getX1() <= 0)
      aceleracao.setXY(0.5, 0);
    // if(aceleracao.getX()>0) atrito.setXY(-.04, 0);
    // else if(aceleracao.getX()<0) atrito.setXY(.04, 0);
    // else
    // atrito.setXY(0, 0);
    super.mover();
  }

  /**
   * Method keyDown.
   * 
   * @param tecla byte
   */
  public void keyDown(byte tecla) {
    super.keyDown(tecla);
  }

  /**
   * Method getMisseis.
   * 
   * @return ArrayList<Missil>
   */
  public ArrayList<Missil> getMisseis() {
    if (misseis == null) {
      misseis = new ArrayList<Missil>();
      try {
        for (int i = 0; i < 20; i++) {
          misseis.add(new Missil());
          cenario.addPrintable(misseis.get(i));
        }
      } catch (Exception ex) {
        LOGGER.error("Erro processando os misseis", ex);
      }
    }
    return misseis;
  }

  /**
   * Method update.
   * 
   * @param lista ArrayList<Colidivel>
   * @see com.edgardleal.engine.Tickeable#update(ArrayList<Colidivel>)
   */
  @Override
  public synchronized void update(ArrayList<Colidivel> lista) {

    if (teclas[37]) {
      esquerda();
    }
    if (teclas[39]) {
      direita();
    }
    if (teclas[32]) {
      atirando = true;
    }
    if (teclas[66] && missil.isRead()) {
      lancar();
    }
    if (teclas[10])
      ((Fase01) cenario).ataque(this, 100, 0, 0, 500, 500);

    nextFrame();// muda para o proximo quadro
  }

  /**
   * Method paint.
   * 
   * @param g Graphics
   * @see com.edgardleal.engine.Printable#paint(Graphics)
   */
  @Override
  public void paint(Graphics g) {
    timerDeLancamento = timerDeLancamento > 0 ? --timerDeLancamento : 0;


    if (atirando) {
      g.setColor(Color.cyan);
      g.drawLine(getX1() + getWidth() / 2 - 1, getY1(), getX1() + getWidth() / 2 - 1, 0);
      g.setColor(Color.white);
      g.drawLine(getX1() + getWidth() / 2, getY1(), getX1() + getWidth() / 2, 0);
      g.setColor(Color.cyan);
      g.drawLine(getX1() + getWidth() / 2 + 1, getY1(), getX1() + getWidth() / 2 + 1, 0);
      atirando = false;
      cenario.ataque(this, 5, getX1() + getWidth() / 2, getY1(), getX1() + getWidth() / 2, 0);
    }
    g.setColor(Color.red);
    g.fillRect(30, 10, (int) (aceleracao.getRaio() * 50), 5);

    super.paint(g);
  }// paint
}// class
