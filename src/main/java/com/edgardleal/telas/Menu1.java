package com.edgardleal.telas;

import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.CenarioListener;
import com.edgardleal.engine.MediaCenter;

/**
 */
public class Menu1 extends Cenario {
  private Image imgFundo;
  /**
	 * 
	 */
  private static final long serialVersionUID = 7330968515069117294L;

  /**
   * Constructor for Menu1.
   * 
   * @param applet JApplet
   * @throws MalformedURLException
   */
  public Menu1(CenarioListener listener) throws MalformedURLException {
    super(listener);
    imgFundo = MediaCenter.instance().getImage("img/fundo_menu.png");
    this.setBackground(Color.black);
    super.setImgFundo(this.imgFundo);
    setBounds(0, 0, 500, 500);
    this.repaint();
  }

  /**
   * Method paint.
   * 
   * @param g java.awt.Graphics
   */
  @Override
  public void paint(java.awt.Graphics g) {
    super.paint(g);
    if (getTimer() % 300 == 0)
      cenarioListener.fim(this);
  }

  @Override
  public void reset() {
    super.reset();

  }
}
