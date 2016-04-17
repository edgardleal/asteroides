package teste.edgardleal.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.edgardleal.engine.Printable;


public class GhostSprite extends GhostMovable implements Printable {
  private Image img;
  private boolean visible = false, mostraLife = true;
  protected byte tecla;
  private ArrayList<Rectangle> quadros;// lista de quadros mapeados
  private int quadroAtual = 0, life = 100, resistencia = 2;
  protected boolean teclas[] = new boolean[90];
  private long time = 0;

  public GhostSprite(Image img) {
    this.img = img;
    quadros = new ArrayList<Rectangle>();
  }

  @Override
  public strictfp void paint(Graphics g) {
    // Rectangle r = quadros.get(quadroAtual);
    time++;
    if (quadros.size() == 0)
      return;
    Rectangle r = quadros.get(quadroAtual);

    if (g == null || img == null)
      return;// usado desta forma para testes
    g.drawImage(img, getLeft(), getTop(), (int) getX2(), (int) getY2(), r.x, r.y, r.width,
        r.height, null);

    if (mostraLife) {
      g.setColor(Color.green);
      g.drawLine(getX1(), getY1(), getX1() + (int) ((getWidth() / 100f) * life), getY1());
      g.setColor(Color.red);
      if (life < 100)
        g.drawLine(getX1() + (int) ((getWidth() / 100f) * life), getY1(), getX2(), getY1());
    }

  }

  public void addQuadro(int x1, int y1, int x2, int y2) {
    quadros.add(new Rectangle(x1, y1, x2, y2));
  }

  public void notifyTecla(byte tecla) {
    this.tecla = tecla;
  }

  public void keyDown(byte tecla) {
    this.tecla = tecla;
    teclas[tecla] = true;
  }

  public void keyUp(byte tecla) {
    this.tecla = tecla;
    teclas[tecla] = false;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;

  }

  public void nextQuadro() {
    // T�cnica para reiniciar o arraylist
    if (time % 5 == 0)
      quadroAtual = ++quadroAtual % (quadros.size());
  }

  public void nextFrame() {
    nextQuadro();
  }

  public long getTime() {
    return time;
  }

  public int getQuadroAtual() {
    return quadroAtual;
  }

  public void setQuadroAtual(int quadroAtual) {
    this.quadroAtual = quadroAtual;
  }

  public int getQuadrosCount() {
    return quadros.size();
  }

  public int getLife() {
    return life;
  }

  public int getResistencia() {
    return resistencia;
  }

  public boolean ataque(int intencidade) {
    life -= life < intencidade ? intencidade + life - intencidade : intencidade;
    return life <= 0;
  }

  public void setLife(int life) {
    this.life = life;
  }

  public void setExibeLife(boolean value) {
    mostraLife = value;
  }
}
