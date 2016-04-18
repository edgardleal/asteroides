package com.edgardleal.canhao;

import java.awt.Toolkit;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.Sprite;
import com.edgardleal.engine.Vetor;

/**
 */
public class Canhao extends Sprite {
  Bala balas[] = new Bala[10];
  /**
	 * 
	 */
  private static final long serialVersionUID = -4182748336163526433L;
  private Cenario cenario;

  /**
   * Constructor for Canhao.
   * @param c Cenario
   */
  public Canhao(Cenario c) {
    super(Toolkit.getDefaultToolkit().getImage("img\\Cannon2.png"));
    this.cenario = c;
    for (int i = 0; i < 21; i++)
      getTimeline().add(i * 50, 0, i * 50 + 50, 55, i == 4 ? 0 : -1, 5);
    for (int i = 0; i < balas.length; i++)
      balas[i] = new Bala();

    for (int i = 0; i < balas.length; i++)
      cenario.addPrintable(getBala());

    this.atrito.setRaio(4);
    this.setBounds(400, 400, 50, 55);
  }

  /**
   * Method mover.
   * @param gravidade Vetor
   */
  @Override
  public void mover(Vetor gravidade) {

  }

  /**
   * Method onFrameChange.
   * @param frame int
   */
  @Override
  public void onFrameChange(int frame) {
    if (frame == 4) {
      Bala temp = getBala();
      if (temp != null) {
        // temp.getAceleracao().setXY(2, 2);
        temp.getAceleracao().setRaio(1.45);
        temp.getAceleracao().setDirecaoGraus(350);
        temp.setBounds(425, 425, 16, 16);
        temp.setVisible(true);
      } else
        System.out.println("");
    }
  }

  /**
   * Method getBala.
   * @return Bala
   */
  public Bala getBala() {
    // System.out.println("Balas no canh�o : " + balas.length);
    for (int i = 0; i < balas.length; i++) {
      if (balas[i] == null) {
        balas[i] = new Bala();
        return balas[i];
      } else if (!balas[i].isVisible())
        return balas[i];
    }
    return null;
  }
}
