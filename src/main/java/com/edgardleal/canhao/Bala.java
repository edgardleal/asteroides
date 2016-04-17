package com.edgardleal.canhao;

import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import com.edgardleal.engine.Colidivel;
import com.edgardleal.engine.Sprite;
import com.edgardleal.engine.Vetor;

/**
 */
public class Bala extends Sprite {


  public Bala() {
    super(Toolkit.getDefaultToolkit().getImage("img\\bol.png"));
    if (!new File("img\\bol.png").exists()) {
      System.out.println("O arquivo n�o existe");
      System.exit(0);
    }
    setBounds(425, 425, 16, 16);
    getTimeline().add(0, 0, 16, 16, 0, 5);
    atrito.setRaio(0.8);
    this.setVisible(false);
    aceleracao.setRaio(1);
    aceleracao.setDirecaoGraus(350);
    aceleracao.setRaioLimite(3);
  }

  /**
   * Method getAceleracao.
   * @return Vetor
   */
  public Vetor getAceleracao() {
    return aceleracao;
  }

  /**
   * Method mover.
   * @param gravidade Vetor
   */
  @Override
  public void mover(Vetor gravidade) {
    super.mover(gravidade);
    if (this.getY1() > 8000 || this.getX1() > 8000 || this.getX1() < 0)
      this.setVisible(false);
  }

  /**
   * Method onFrameChange.
   * @param frame int
   */
  @Override
  public void onFrameChange(int frame) {
    super.onFrameChange(frame);
  }

  /**
   * Method update.
   * @param lista ArrayList<Colidivel>
   * @see com.edgardleal.engine.Tickeable#update(ArrayList<Colidivel>)
   */
  @Override
  public void update(ArrayList<Colidivel> lista) {
    if (!this.isVisible())
      return;
    super.update(lista);
  }

}
