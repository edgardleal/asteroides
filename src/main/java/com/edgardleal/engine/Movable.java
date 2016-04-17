package com.edgardleal.engine;


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

  public void setVelocidadeMaxima(double v) {
    aceleracao.setRaioLimite(v);
  }

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

  public strictfp void setLocation(double x, double y) {
    setX2((x2 - x1) + x);
    setX1(x);
    setY2((y2 - y1) + y);
    setY1(y);
  }

  public void setPosition(int x, int y) {
    setLocation(x, y);
  }

  public int getX1() {
    return (int) x1;
  }

  public void setX1(double x1) {
    this.x1 = x1;
  }

  public int getX2() {
    return (int) x2;
  }

  public void setX2(double x2) {
    this.x2 = x2;
  }

  public int getY1() {
    return (int) y1;
  }

  public void setY1(double y1) {
    this.y1 = y1;
  }

  public double getY2() {
    return y2;
  }

  public void setY2(double y2) {
    this.y2 = y2;
  }

  public double getPasso() {
    return passo;
  }

  public void setPasso(double passo) {
    this.passo = passo;
  }

  public int getWidth() {
    return (int) (x2 - x1);
  }

  public int getHeight() {
    return (int) (y2 - y1);
  }

  public void setWidth(double w) {
    setX2(getX1() + w);
  }

  public void setHeight(double h) {
    setY2(getY1() + h);
  }

  public int getLeft() {
    return (int) getX1();
  }

  public int getTop() {
    return (int) getY1();
  }

  public static void main(String[] args) {
    System.out.println(Math.acos(1) * 2);
    System.out.println(Math.asin(1) * 2);
  }

  @Override
  public int getX() {
    return (int) x1;
  }

  @Override
  public int getY() {
    return (int) y1;
  }

  @Override
  public int getRaio() {
    return getX1() + getWidth() / 2;
  }

  @Override
  public boolean isSolid() {
    return solid;
  }

  public void setSolid(boolean value) {
    this.solid = value;
  }

  @Override
  public void colidiu(Colidivel c) {
    // TODO Auto-generated method stub
  }

  public String toString() {
    return "X:" + getX1() + " Y:" + getY1() + " Width:" + getWidth() + " Height:" + getHeight();
  }
}
