package com.edgardleal.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import com.edgardleal.engine.animation.Frame;
import com.edgardleal.engine.animation.FrameListener;
import com.edgardleal.engine.animation.TimeLine;


/**
 */
public class Sprite extends Movable implements Printable, Updateable, FrameListener {
  private Image img;
  private boolean visible = true, mostraLife = true;
  protected byte tecla;
  private int life = 100, resistencia = 2;
  private TimeLine timeLine = new TimeLine(this);
  protected boolean teclas[] = new boolean[90];
  private long time = 0;

  /**
   * Constructor for Sprite.
   * @param img Image
   */
  public Sprite(Image img) {
    this.img = img;
  }

  /**
   * Method paint.
   * @param g Graphics
   * @see com.edgardleal.engine.Printable#paint(Graphics)
   */
  @Override
  public void paint(Graphics g) {
    if (!visible)
      return;
    Frame r = timeLine.getFrame();
    g.drawImage(img, getLeft(), getTop(), (int) getX2(), (int) getY2(), r.getX(), r.getY(),
        r.getW(), r.getH(), null);

    if (mostraLife) {
      g.setColor(Color.green);
      g.drawLine(getX1(), getY1(), getX1() + (int) ((getWidth() / 100f) * life), getY1());
      g.setColor(Color.red);
      if (life < 100)
        g.drawLine(getX1() + (int) ((getWidth() / 100f) * life), getY1(), getX2(), getY1());
    }
  }

  /**
   * Method addQuadro.
   * @param x1 int
   * @param y1 int
   * @param x2 int
   * @param y2 int
   */
  public void addQuadro(int x1, int y1, int x2, int y2) {
    timeLine.add(x1, y1, x2, y2);
  }

  /**
   * Method notifyTecla.
   * @param tecla byte
   */
  public synchronized void notifyTecla(byte tecla) {
    this.tecla = tecla;
  }

  /**
   * Method keyDown.
   * @param tecla byte
   */
  public void keyDown(byte tecla) {
    this.tecla = tecla;
    teclas[tecla] = true;
  }

  /**
   * Method keyUp.
   * @param tecla byte
   */
  public void keyUp(byte tecla) {
    this.tecla = tecla;
    teclas[tecla] = false;
  }

  /**
   * Method isVisible.
   * @return boolean
   */
  public boolean isVisible() {
    return visible;
  }

  /**
   * Method setVisible.
   * @param visible boolean
   */
  public void setVisible(boolean visible) {
    this.visible = visible;

  }

  public void nextFrame() {
    timeLine.nextFrame();
    onFrameChange(timeLine.getCurrentFrame());
  }

  /**
   * Method getTime.
   * @return long
   */
  public long getTime() {
    return time;
  }

  /*
   * public int getQuadroAtual() { return quadroAtual; }
   */

  /*
   * public int getQuadrosCount(){ return timeLine.getSize(); }
   */
  /**
   * Method getLife.
   * @return int
   */
  public int getLife() {
    return life;
  }

  /**
   * Method getResistencia.
   * @return int
   */
  public int getResistencia() {
    return resistencia;
  }

  /**
   * Method ataque.
   * @param intencidade int
   * @return boolean
   */
  public boolean ataque(int intencidade) {
    life -= life < intencidade ? intencidade + life - intencidade : intencidade;
    return life <= 0;
  }

  /**
   * Method setLife.
   * @param life int
   */
  public void setLife(int life) {
    this.life = life;
  }

  /**
   * Method setExibeLife.
   * @param value boolean
   */
  public void setExibeLife(boolean value) {
    mostraLife = value;
  }

  /**
   * Method update.
   * @param lista ArrayList<Colidivel>
   * @see com.edgardleal.engine.Tickeable#update(ArrayList<Colidivel>)
   */
  @Override
  public synchronized void update(ArrayList<Colidivel> lista) {
    nextFrame();
    time++;
  }

  /**
   * Retorna o timeline de anima��o deste sprite.
   * 
  
   * @return TimeLine
   */
  public TimeLine getTimeline() {
    return timeLine;
  }

  /**
   * Metodo para ser utilizado como eveto para as classes decendentes, ser� invorcado toda vez que
   * houver mudan�a de quadro na anima��o.
   * 
   * @param frame
   */
  protected void onFrameChange(int frame) {

  }

  /**
   * Method onFrameEnd.
   * @param frame int
   * @see com.edgardleal.engine.animation.FrameListener#onFrameEnd(int)
   */
  @Override
  public void onFrameEnd(int frame) {
    // TODO Auto-generated method stub

  }

  /**
   * Method onFrameStart.
   * @param frame int
   * @see com.edgardleal.engine.animation.FrameListener#onFrameStart(int)
   */
  @Override
  public void onFrameStart(int frame) {
    // TODO Auto-generated method stub
  }

  /**
   * Method rotate.
   * @param g Graphics
   */
  public void rotate(Graphics g) {
    // Graphics2D g2 = (Graphics2D)g.create();
    // Graphics2D g2d = g2.create();
    // g2d.translate(445/2-10, 505);
    // g2d.rotate(angulo.getValue());
    // g2d.translate(-(445/2-10), -505);
    // g2d.drawImage(canhao, 445/2-40, 434, this);
    // g2d.dispose();
    //
    // aceleracao.getDirecao();
  }

  /**
   * Method setBounds.
   * @param x int
   * @param y int
   * @param w int
   * @param h int
   */
  public void setBounds(int x, int y, int w, int h) {
    setX2(x + w);
    setY2(h + y);
    setX1(x);
    setY1(y);
  }
}
