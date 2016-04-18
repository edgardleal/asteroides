package com.edgardleal.engine.view;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.CenarioListener;
import com.edgardleal.engine.GameTicker;

/**
 */
public class TelaPadrao extends JFrame implements KeyListener, CenarioListener {

  /**
	 * 
	 */
  private static final long serialVersionUID = 2677448298977549188L;
  protected GameTicker gameTicker = new GameTicker();

  public TelaPadrao() {
    super();
    setUndecorated(true);
    setSize(Toolkit.getDefaultToolkit().getScreenSize());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.addKeyListener(this);
  }



  /**
   * Method keyPressed.
   * @param k KeyEvent
   * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
   */
  @Override
  public void keyPressed(KeyEvent k) {
    if (k.getKeyChar() == 27)
      System.exit(0);
  }

  /**
   * Method keyReleased.
   * @param k KeyEvent
   * @see java.awt.event.KeyListener#keyReleased(KeyEvent)
   */
  @Override
  public void keyReleased(KeyEvent k) {
    // TODO Auto-generated method stub

  }

  /**
   * Method keyTyped.
   * @param k KeyEvent
   * @see java.awt.event.KeyListener#keyTyped(KeyEvent)
   */
  @Override
  public void keyTyped(KeyEvent k) {
    // TODO Auto-generated method stub

  }



  /**
   * Method fim.
   * @param sender Cenario
   * @return int
   * @see com.edgardleal.engine.CenarioListener#fim(Cenario)
   */
  @Override
  public int fim(Cenario sender) {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * Method setFase.
   * @param fase Cenario
   */
  public void setFase(Cenario fase) {
    this.setContentPane(fase);
  }

}
