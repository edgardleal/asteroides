package com.edgardleal.canhao;

import com.edgardleal.engine.view.TelaPadrao;

public class AntiMisseis extends TelaPadrao {

  /**
	 * 
	 */
  private static final long serialVersionUID = -4182748336163526433L;
  private Fase01 fase01 = new Fase01(this);

  public AntiMisseis() {
    this.setFase(fase01);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    new AntiMisseis().setVisible(true);

  }

}
