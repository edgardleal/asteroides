package com.edgardleal.engine;

/**
 */
public interface Colidivel {
  /**
   * Method colidiu.
   * @param c Colidivel
   */
  void colidiu(Colidivel c);

  /**
   * Method getWidth.
   * @return int
   */
  public int getWidth();

  /**
   * Method getHeight.
   * @return int
   */
  public int getHeight();

  /**
   * Method getX.
   * @return int
   */
  public int getX();

  /**
   * Method getY.
   * @return int
   */
  public int getY();

  /**
   * Method getRaio.
   * @return int
   */
  public int getRaio();

  /**
   * Method isSolid.
   * @return boolean
   */
  public boolean isSolid();
}
