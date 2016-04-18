package com.edgardleal.engine.animation;

import java.util.Vector;

/**
 */
public class TimeLine implements FrameListener {
  private Vector<Frame> frames = new Vector<Frame>();
  private int currentFrame = 0;
  private FrameListener frameListener = this;

  public TimeLine() {

  }

  /**
   * Constructor for TimeLine.
   * @param frameListener FrameListener
   */
  public TimeLine(FrameListener frameListener) {
    setFrameListener(frameListener);
  }

  /**
   * Atualiza o frame para o proximo passo do timelinne.
   * 
   */
  public void nextFrame() {
    if (frames.size() == 0)
      return;
    Frame f = frames.get(currentFrame);

    if (f.time == f.delay) {
      f.time = 0;
      frameListener.onFrameEnd(currentFrame);
      if (f.nextFrame == -1)
        currentFrame = ++currentFrame % frames.size();
      else
        currentFrame = f.nextFrame;
    } else {
      frameListener.onFrameStart(currentFrame);
      f.time++;
    }
  }

  /**
   * Method add.
   * @param f Frame
   */
  public void add(Frame f) {
    frames.add(f);
  }

  /**
   * Method add.
   * @param x int
   * @param y int
   * @param w int
   * @param h int
   */
  public void add(int x, int y, int w, int h) {
    add(new Frame(x, y, w, h));
  }

  /**
   * Method add.
   * @param x int
   * @param y int
   * @param w int
   * @param h int
   * @param nextFrame int
   */
  public void add(int x, int y, int w, int h, int nextFrame) {
    add(new Frame(x, y, w, h, nextFrame));
  }

  /**
   * Method add.
   * @param x int
   * @param y int
   * @param w int
   * @param h int
   * @param nextFrame int
   * @param delay int
   */
  public void add(int x, int y, int w, int h, int nextFrame, int delay) {
    add(new Frame(x, y, w, h, nextFrame, delay));
  }


  /**
   * Method getFrame.
   * @return Frame
   */
  public Frame getFrame() {
    return frames.get(currentFrame);
  }

  /**
   * Method getSize.
   * @return int
   */
  public int getSize() {
    return frames.size();
  }

  /**
   * Method getCurrentFrame.
   * @return int
   */
  public int getCurrentFrame() {
    return currentFrame;
  }

  /**
   * Method gotoFrame.
   * @param frame int
   */
  public void gotoFrame(int frame) {
    currentFrame = frame;
  }

  /**
   * Method setFrameListener.
   * @param frameListener FrameListener
   */
  public void setFrameListener(FrameListener frameListener) {
    this.frameListener = frameListener;
  }

  /**
   * Method onFrameEnd.
   * @param frame int
   * @see com.edgardleal.engine.animation.FrameListener#onFrameEnd(int)
   */
  @Override
  public void onFrameEnd(int frame) {
    // TODO Auto-generated method stub

  }

  /**
   * Method onFrameStart.
   * @param frame int
   * @see com.edgardleal.engine.animation.FrameListener#onFrameStart(int)
   */
  @Override
  public void onFrameStart(int frame) {
    // TODO Auto-generated method stub

  }
}
