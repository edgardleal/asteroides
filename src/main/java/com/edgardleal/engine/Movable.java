package com.edgardleal.engine;


/**
 */
public class Movable implements Colidivel {
  private double x1, x2, y1, y2;
  private boolean solid = true;
  private double passo = 1;
  protected Vetor atrito, aceleracao, vDireita, vEsquerda;

  public Movable() {
    atrito = new Vetor(0, .2);
    aceleracao = new Vetor();
    vDireita = new Vetor(0.05, 0);
    vEsquerda = new Vetor(-0.05, 0);
  }

  /**
   * Method setVelocidadeMaxima.
   * @param v double
   */
  public void setVelocidadeMaxima(double v) {
    aceleracao.setRaioLimite(v);
  }

  /**
   * Method mover.
   * @param gravidade Vetor
   */
  public void mover(Vetor gravidade) {
    // if(gravidade.getRaio()+aceleracao.getRaio()<passo)
    aceleracao.somar(gravidade);
    mover();
  }

  public void mover() {
    atrito.setDirecao(aceleracao.getDirecao());
    if (aceleracao.getRaio() > atrito.getRaio())// aplica��o da gravidad3
      aceleracao.subtrair(atrito);
    setLocation(x1 + aceleracao.getX(), y1 + aceleracao.getY());
  }

  public void subir() {
    setLocation(x1, (y1 - getPasso()));
  }

  public void descer() {
    setLocation(x1, (y1 + getPasso()));
  }

  public void direita() {
    aceleracao.somar(vDireita);
  }

  public void esquerda() {
    aceleracao.somar(vEsquerda);
  }

  /**
   * Method setLocation.
   * @param x double
   * @param y double
   */
  public strictfp void setLocation(double x, double y) {
    setX2((x2 - x1) + x);
    setX1(x);
    setY2((y2 - y1) + y);
    setY1(y);
  }

  /**
   * Method setPosition.
   * @param x int
   * @param y int
   */
  public void setPosition(int x, int y) {
    setLocation(x, y);
  }

  /**
   * Method getX1.
   * @return int
   */
  public int getX1() {
    return (int) x1;
  }

  /**
   * Method setX1.
   * @param x1 double
   */
  public void setX1(double x1) {
    this.x1 = x1;
  }

  /**
   * Method getX2.
   * @return int
   */
  public int getX2() {
    return (int) x2;
  }

  /**
   * Method setX2.
   * @param x2 double
   */
  public void setX2(double x2) {
    this.x2 = x2;
  }

  /**
   * Method getY1.
   * @return int
   */
  public int getY1() {
    return (int) y1;
  }

  /**
   * Method setY1.
   * @param y1 double
   */
  public void setY1(double y1) {
    this.y1 = y1;
  }

  /**
   * Method getY2.
   * @return double
   */
  public double getY2() {
    return y2;
  }

  /**
   * Method setY2.
   * @param y2 double
   */
  public void setY2(double y2) {
    this.y2 = y2;
  }

  /**
   * Method getPasso.
   * @return double
   */
  public double getPasso() {
    return passo;
  }

  /**
   * Method setPasso.
   * @param passo double
   */
  public void setPasso(double passo) {
    this.passo = passo;
  }

  /**
   * Method getWidth.
   * @return int
   * @see com.edgardleal.engine.Colidivel#getWidth()
   */
  public int getWidth() {
    return (int) (x2 - x1);
  }

  /**
   * Method getHeight.
   * @return int
   * @see com.edgardleal.engine.Colidivel#getHeight()
   */
  public int getHeight() {
    return (int) (y2 - y1);
  }

  /**
   * Method setWidth.
   * @param w double
   */
  public void setWidth(double w) {
    setX2(getX1() + w);
  }

  /**
   * Method setHeight.
   * @param h double
   */
  public void setHeight(double h) {
    setY2(getY1() + h);
  }

  /**
   * Method getLeft.
   * @return int
   */
  public int getLeft() {
    return (int) getX1();
  }

  /**
   * Method getTop.
   * @return int
   */
  public int getTop() {
    return (int) getY1();
  }

  /**
   * Method main.
   * @param args String[]
   */
  public static void main(String[] args) {
    System.out.println(Math.acos(1) * 2);
    System.out.println(Math.asin(1) * 2);
  }

  /**
   * Method getX.
   * @return int
   * @see com.edgardleal.engine.Colidivel#getX()
   */
  @Override
  public int getX() {
    return (int) x1;
  }

  /**
   * Method getY.
   * @return int
   * @see com.edgardleal.engine.Colidivel#getY()
   */
  @Override
  public int getY() {
    return (int) y1;
  }

  /**
   * Method getRaio.
   * @return int
   * @see com.edgardleal.engine.Colidivel#getRaio()
   */
  @Override
  public int getRaio() {
    return getX1() + getWidth() / 2;
  }

  /**
   * Method isSolid.
   * @return boolean
   * @see com.edgardleal.engine.Colidivel#isSolid()
   */
  @Override
  public boolean isSolid() {
    return solid;
  }

  /**
   * Method setSolid.
   * @param value boolean
   */
  public void setSolid(boolean value) {
    this.solid = value;
  }

  /**
   * Method colidiu.
   * @param c Colidivel
   * @see com.edgardleal.engine.Colidivel#colidiu(Colidivel)
   */
  @Override
  public void colidiu(Colidivel c) {
    // TODO Auto-generated method stub
  }

  /**
   * Method toString.
   * @return String
   */
  public String toString() {
    return "X:" + getX1() + " Y:" + getY1() + " Width:" + getWidth() + " Height:" + getHeight();
  }
}
