package com.edgardleal.engine.animation;
/**Implementa uma classe escutadora para ser notificada a cada 
 *  inicio e fim dos Quadros da animação, isto pode ser utilizado para 
 *  disparar eventos antes e apos o inicio de cada Quadro.
 * 
 * @author Edgard
 *
 */
public interface FrameListener {
	public void onFrameStart(int frame);
	public void onFrameEnd(int frame);
}
