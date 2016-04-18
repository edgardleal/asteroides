package com.edgardleal.engine.animation;

/**
 */
public class Frame {
  int delay = 1;
  int time = 0;
  int nextFrame = -1;
  int x, y, w, h;

  /**
   * Constructor for Frame.
   * @param x int
   * @param y int
   * @param w int
   * @param h int
   */
  public Frame(int x, int y, int w, int h) {
    setBounds(x, y, w, h);
  }

  /**
   * Constructor for Frame.
   * @param x int
   * @param y int
   * @param w int
   * @param h int
   * @param nextFrame int
   */
  public Frame(int x, int y, int w, int h, int nextFrame) {
    setBounds(x, y, w, h);
    this.nextFrame = nextFrame;
  }

  /**
   * Constructor for Frame.
   * @param x int
   * @param y int
   * @param w int
   * @param h int
   * @param nextFrame int
   * @param delay int
   */
  public Frame(int x, int y, int w, int h, int nextFrame, int delay) {
    setBounds(x, y, w, h);
    this.nextFrame = nextFrame;
    this.delay = delay;
  }

  /**
   * Method setBounds.
   * @param x int
   * @param y int
   * @param w int
   * @param h int
   */
  public void setBounds(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  /**
   * Method getDelay.
   * @return int
   */
  public int getDelay() {
    return delay;
  }

  /**
   * Method getTime.
   * @return int
   */
  public int getTime() {
    return time;
  }

  /**
   * Method getX.
   * @return int
   */
  public int getX() {
    return x;
  }

  /**
   * Method getY.
   * @return int
   */
  public int getY() {
    return y;
  }

  /**
   * Method getW.
   * @return int
   */
  public int getW() {
    return w;
  }

  /**
   * Method getH.
   * @return int
   */
  public int getH() {
    return h;
  }
}
