/**
 * 
 */
package com.edgardleal.engine;


/**
 * A classe que implementar esta interface , tornase um listener para controlar ou ser noificado
 * sobre as transi��es de cenarios.
 * 
 * @author edgard.leal
 * @since 04/08/2011
 * @version $Revision: 1.0 $
 */
public interface CenarioListener {
  /**
   * Indica que este cenario chegou ao fim da execu��o e indica qual ser� o proximo cenario a ser
   * exibido.
   * 
   * @param sender
  
   * @return int
   */
  public int fim(Cenario sender);
}
