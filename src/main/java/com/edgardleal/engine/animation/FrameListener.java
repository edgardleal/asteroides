package com.edgardleal.engine.animation;

/**
 * Implementa uma classe escutadora para ser notificada a cada inicio e fim dos Quadros da anima��o,
 * isto pode ser utilizado para disparar eventos antes e apos o inicio de cada Quadro.
 * 
 * @author Edgard
 *
 * @version $Revision: 1.0 $
 */
public interface FrameListener {
  /**
   * Method onFrameStart.
   * @param frame int
   */
  public void onFrameStart(int frame);

  /**
   * Method onFrameEnd.
   * @param frame int
   */
  public void onFrameEnd(int frame);
}
