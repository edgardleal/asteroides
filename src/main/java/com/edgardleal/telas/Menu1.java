package com.edgardleal.telas;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.CenarioListener;
import com.edgardleal.engine.MediaCenter;

import java.awt.*;

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
   */
  public Menu1(CenarioListener listener) {
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
