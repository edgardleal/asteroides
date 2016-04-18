package com.edgardleal.canhao;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.CenarioListener;

/**
 */
public class Fase01 extends Cenario {

  /**
   * Constructor for Fase01.
   * @param c CenarioListener
   */
  public Fase01(CenarioListener c) {
    super(c);
    this.gravidade.setXY(0, 0.001);
    this.addPrintable(new Projetil());
    this.addPrintable(new Canhao(this));
  }

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

}
