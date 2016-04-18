package com.edgardleal.engine;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author edgardleal
 *
 */
public class Cenario extends JPanel implements Runnable, Updateable {

  private static final Logger LOGGER = LoggerFactory.getLogger(Cenario.class);
  Image imgFundo;
  private Thread controle;
  private byte tecla = 0;
  private boolean isPaused = false;
  protected Vetor gravidade;
  private long timer = 0;
  private Vector<Printable> lista;// Lista de sprites que ser�o pintados na
  // tela
  protected CenarioListener cenarioListener;
  private Vector<Updateable> updateables = new Vector<Updateable>();

  /**
   * Lista dos objetos que estar�o sucetiveis de tratamento de colis�o.
   * 
   */
  private ColisionCenter colisionCenter = new ColisionCenter();

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for Cenario.
   * 
   * @param c CenarioListener
   */
  public Cenario(CenarioListener c) {// Construtor que recebe a imagem
    super(true);// Diz ao JPanel que ele ir� trabalhar com double Buffer
    this.setIgnoreRepaint(true);// ignora solicita��es de repaint do systema
    // operacinal
    lista = new Vector<Printable>();// Inicializa��o da lista
    cenarioListener = c;
    controle = new Thread(this, getClass().getSimpleName());
    controle.setPriority(7);
    gravidade = new Vetor(0, .05);
    controle.start();
    LOGGER.debug("Cene started");
  }

  /**
   * Method paint.
   * 
   * @param g Graphics
   */
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    if (isPaused) {
      // TODO: rebuild the pause screen
      // g.setColor(Color.blue);
      // g.fillRect(0, 0, 800, 600);
      // return;
    }
    if (imgFundo != null) {// Verifica se a imagem foi passada. Para evitar
      // erros
      g.drawImage(imgFundo, 0, 0, null);// Ele desenha a imagem na tela.
      // g.drawImage(imagem, x-tela,
      // y-tela,
      // largura-tela, altura-tela,
      // x-imagem,
      // y-imagem, largura-imagem,
      // altura-imagem, notifica��o)
    }
    for (Printable p : lista) { // Varredura da lista
      p.paint(g);
    }
  }

  /**
   * Method setImgFundo.
   * 
   * @param img Image
   */
  public void setImgFundo(Image img) {
    this.imgFundo = img;
  }

  /**
   * Method addPrintable.
   * 
   * @param p Printable
   */
  public synchronized void addPrintable(Printable p) {
    lista.add(p);
    if (p instanceof Colidivel) {
      colisionCenter.add((Colidivel) p);
    }
    if (p instanceof Tickeable) {
      updateables.add((Updateable) p);
    }
  }

  /**
   * Method run.
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    while (controle != null) {
      try {
        Thread.sleep(16);
        synchronized (this) {
          this.repaint();
        }
      } catch (Exception e) {
        LOGGER.error("Erro no gameLoop : ", e.getMessage());
      }
    }
  }

  public synchronized void pause() {
    isPaused = !isPaused;
  }

  /**
   * Method notifyTecla.
   * 
   * @param tecla byte
   */
  public void notifyTecla(byte tecla) {
    LOGGER.debug("Key notified: {}", tecla);
    this.tecla = tecla;
  }

  /**
   * Method keyDown.
   * 
   * @param tecla byte
   */
  public synchronized void keyDown(byte tecla) {
    LOGGER.debug("Key pressed: " + tecla);
    for (Printable p : lista) {
      if (p instanceof Sprite)
        ((Sprite) (p)).keyDown(tecla);
    }
  }

  /**
   * Method keyUp.
   * 
   * @param tecla byte
   */
  public synchronized void keyUp(byte tecla) {
    for (Printable p : lista) {
      if (p instanceof Sprite)
        ((Sprite) (p)).keyUp(tecla);
    }
  }

  /**
   * Distribui um ataque para todos os objetos Sprites da fase se estejam situados na �rea
   * delimitada pelas cordenadas informadas no par�metro.
   * 
   * @param m Objeto do tipo Moveble o qual disparaou o evento e n�o ser� afetado.
   * @param intencidade informa o n�vel do ataque.
   * @param x inicio a �rea a ser afetada pelo ataque.
   * @param y inicio superior da �rea a ser afetada pelo ataque.
   * @param x2 ponto lateral direito ada �rea a ser afetada pelo ataque.
   * @param y2 ponto vertical inferior da �rea a ser afetada pelo ataque.
   */
  public void ataque(Movable m, int intencidade, int x, int y, int x2, int y2) {
    for (Printable p : lista) {
      if (p instanceof Sprite && p != m) {
        if (x >= ((Sprite) p).getX1() && x2 <= ((Sprite) p).getX2()) {
          ((Sprite) p).ataque(intencidade);
        }
      }
    }
  }

  /**
   * Method getPrintables.
   * 
   * @return Vector<Printable>
   */
  public Vector<Printable> getPrintables() {
    if (lista == null)
      lista = new Vector<Printable>();
    return lista;
  }

  /**
   * Adiciona um item do tipo Colidivel para ser verificado e notificado quando estiver em estado de
   * colis�o.
   * 
   * @param c Objeto do tipo Colidivel
   */
  public synchronized void addColidivel(Colidivel c) {
    colisionCenter.add(c);
  }

  /**
   * Method getTimer.
   * 
   * @return long
   */
  public long getTimer() {
    return timer;
  }

  public void reset() {
    timer = 0;
  }

  /**
   * Method update.
   * 
   * @param lista ArrayList<Colidivel>
   * @see com.edgardleal.engine.Tickeable#update(ArrayList<Colidivel>)
   */
  public synchronized void update(ArrayList<Colidivel> lista) {
    if (isPaused) {
      return;
    }
    synchronized (this) {

      for (Updateable u : updateables) {
        u.update(null);
        if (u instanceof Sprite) {
          ((Sprite) u).notifyTecla(tecla);
          ((Sprite) u).mover(gravidade);
        }
      }
    }
    colisionCenter.verifica();
    timer = timer == Long.MAX_VALUE ? ++timer : 0L;
  }
}
