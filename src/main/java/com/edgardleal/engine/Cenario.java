package com.edgardleal.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;

public class Cenario extends JPanel implements Runnable, Updateable {

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

  private GameTicker gameTicker = new GameTicker();

  /**
   * Lista dos objetos que estar�o sucetiveis de tratamento de colis�o.
   * 
   */
  private ColisionCenter colisionCenter = new ColisionCenter();

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  public Cenario(CenarioListener c) {// Construtor que recebe a imagem
    super(true);// Diz ao JPanel que ele ir� trabalhar com double Buffer
    gameTicker.add(this);
    this.setIgnoreRepaint(true);// ignora solicita��es de repaint do systema
    // operacinal
    lista = new Vector<Printable>();// Inicializa��o da lista
    cenarioListener = c;
    controle = new Thread(this);
    controle.setPriority(7);
    gravidade = new Vetor(0, .05);
    gameTicker.setDelay(30);
    gameTicker.start();
    controle.start();
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    if (isPaused) {
      g.setColor(Color.blue);
      g.fillRect(0, 0, 800, 600);
      return;
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

  public void setImgFundo(Image img) {
    this.imgFundo = img;
  }

  public synchronized void addPrintable(Printable p) {
    lista.add(p);
    if (p instanceof Colidivel)
      colisionCenter.add((Colidivel) p);
    if (p instanceof Tickeable)
      updateables.add((Updateable) p);
  }

  @Override
  public void run() {
    while (controle != null) {
      try {
        Thread.sleep(16);
        synchronized (this) {
          this.repaint();
        }
      } catch (Exception e) {
        System.out.println("Erro no gameLoop : " + e.getMessage());
      }
    }
  }

  public void pause() {
    isPaused = !isPaused;
  }

  public void notifyTecla(byte tecla) {
    this.tecla = tecla;
  }

  public synchronized void keyDown(byte tecla) {
    System.out.println("Key pressed: " + tecla);
    for (Printable p : lista) {
      if (p instanceof Sprite)
        ((Sprite) (p)).keyDown(tecla);
    }
  }

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

  public long getTimer() {
    return timer;
  }

  public void reset() {
    timer = 0;
  }

  public synchronized void update(ArrayList<Colidivel> lista) {
    synchronized (gameTicker) {
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
