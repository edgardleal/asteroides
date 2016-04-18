package com.edgardleal.telas;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.CenarioListener;
import com.edgardleal.engine.GameTicker;

/**
 */
public class Main extends JFrame implements KeyListener, CenarioListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
  Fase01 fase01;
  Menu1 menu;
  Container tela;
  int x = 0;
  Graphics g;
  Image imgFundo;
  URL urlFundo;
  GameTicker gameTicker = new GameTicker();

  private static final long serialVersionUID = 1L;


  public Main() {
    init();
  }

  /**
   * Coment�rios para o Javadoc para o m�todo Init
   * 
   */
  public void init() {
    setSize(500, 500);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    tela = this.getContentPane();

    this.setLayout(null);/* Desativa o gerenciador de Layout */
    this.addKeyListener(this);
    this.requestFocus();
    try {
      iniciar();
      LOGGER.debug("Applet inicializada");
    } catch (Exception e) {
      LOGGER.error("Erro ao inicializar a applet", e);
    }
    this.setVisible(true);
  }

  public void stop() {

  }

  /**
   * Method iniciar.
   * 
   * @throws Exception
   */
  private void iniciar() throws Exception {
    getFase01().addKeyListener(this);
    gameTicker.add(getFase01());
    gameTicker.setDelay(50);
    gameTicker.start();
    tela.add(getFase01());
  }

  /**
   * Method keyPressed.
   * 
   * @param key KeyEvent
   * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
   */
  @Override
  public void keyPressed(KeyEvent key) {
    LOGGER.debug("keyPressed: {}", key.getKeyCode());
    getFase01().keyDown((byte) key.getKeyCode());
    getFase01().notifyTecla((byte) key.getKeyCode());
    if (key.getKeyChar() == 27) {
      getFase01().pause();
    }
  }

  /**
   * Method keyReleased.
   * 
   * @param key KeyEvent
   * @see java.awt.event.KeyListener#keyReleased(KeyEvent)
   */
  @Override
  public void keyReleased(KeyEvent key) {
    LOGGER.debug("keyReleased: {}", key.getKeyCode());
    getFase01().keyUp((byte) key.getKeyCode());
  }

  /**
   * Method keyTyped.
   * 
   * @param key KeyEvent
   * @see java.awt.event.KeyListener#keyTyped(KeyEvent)
   */
  public void keyTyped(KeyEvent key) {

    LOGGER.debug("Key pressed: {}", key.getKeyCode());
  }

  /**
   * Method fim.
   * 
   * @param sender Cenario
   * @return int
   * @see com.edgardleal.engine.CenarioListener#fim(Cenario)
   */
  @Override
  public int fim(Cenario sender) {
    tela.remove(menu);
    menu = null;
    tela.add(getFase01());// Adicionando o cen�rio 'inicio' no container
    return 0;
  }

  public void nextFase() {
    try {
      if (tela == getMenu1()) {
        getFase01().reset();
        tela = getFase01();
      } else if (tela == getFase01()) {
        getMenu1().reset();
        tela = getMenu1();
      } else {
        getMenu1().reset();
        tela = getMenu1();
      }
    } catch (Exception e) {
    }
  }

  /**
   * Method getFase01.
   * 
   * @return Fase01
   */
  public Fase01 getFase01() {
    if (fase01 == null)
      fase01 = new Fase01(this);
    return fase01;
  }

  /**
   * Method getMenu1.
   * 
   * @return Menu1
   * @throws Exception
   */
  public Menu1 getMenu1() throws Exception {
    if (menu == null)
      menu = new Menu1(this);
    return menu;
  }

  public static void main(String[] args) {
    new Main();
  }
}
