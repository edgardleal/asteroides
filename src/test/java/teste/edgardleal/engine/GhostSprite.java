package teste.edgardleal.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.edgardleal.engine.Printable;


/**
 */
public class GhostSprite extends GhostMovable implements Printable {
  private Image img;
  private boolean visible = false, mostraLife = true;
  protected byte tecla;
  private ArrayList<Rectangle> quadros;// lista de quadros mapeados
  private int quadroAtual = 0, life = 100, resistencia = 2;
  protected boolean teclas[] = new boolean[90];
  private long time = 0;

  /**
   * Constructor for GhostSprite.
   * @param img Image
   */
  public GhostSprite(Image img) {
    this.img = img;
    quadros = new ArrayList<Rectangle>();
  }

  /**
   * Method paint.
   * @param g Graphics
   * @see com.edgardleal.engine.Printable#paint(Graphics)
   */
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

  /**
   * Method addQuadro.
   * @param x1 int
   * @param y1 int
   * @param x2 int
   * @param y2 int
   */
  public void addQuadro(int x1, int y1, int x2, int y2) {
    quadros.add(new Rectangle(x1, y1, x2, y2));
  }

  /**
   * Method notifyTecla.
   * @param tecla byte
   */
  public void notifyTecla(byte tecla) {
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

  public void nextQuadro() {
    // T�cnica para reiniciar o arraylist
    if (time % 5 == 0)
      quadroAtual = ++quadroAtual % (quadros.size());
  }

  public void nextFrame() {
    nextQuadro();
  }

  /**
   * Method getTime.
   * @return long
   */
  public long getTime() {
    return time;
  }

  /**
   * Method getQuadroAtual.
   * @return int
   */
  public int getQuadroAtual() {
    return quadroAtual;
  }

  /**
   * Method setQuadroAtual.
   * @param quadroAtual int
   */
  public void setQuadroAtual(int quadroAtual) {
    this.quadroAtual = quadroAtual;
  }

  /**
   * Method getQuadrosCount.
   * @return int
   */
  public int getQuadrosCount() {
    return quadros.size();
  }

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
}
