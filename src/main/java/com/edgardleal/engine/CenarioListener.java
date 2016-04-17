/**
 * 
 */
package com.edgardleal.engine;


/**A classe que implementar esta interface , tornase um listener para controlar ou 
 * ser noificado sobre as transições de cenarios. 
 * @author edgard.leal
 * @since 04/08/2011
 */
public interface CenarioListener {
	/**Indica que este cenario chegou ao fim da execução e indica qual será o proximo cenario a 
	 * ser exibido.
	 * 
	 * @param sender
	 * @return
	 */
	public int fim(Cenario sender);
}
