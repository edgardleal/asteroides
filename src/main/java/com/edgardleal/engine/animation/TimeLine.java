package com.edgardleal.engine.animation;

import java.util.Vector;

public class TimeLine implements FrameListener {
  private Vector<Frame> frames = new Vector<Frame>();
  private int currentFrame = 0;
  private FrameListener frameListener = this;

  public TimeLine() {

  }

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

  public void add(Frame f) {
    frames.add(f);
  }

  public void add(int x, int y, int w, int h) {
    add(new Frame(x, y, w, h));
  }

  public void add(int x, int y, int w, int h, int nextFrame) {
    add(new Frame(x, y, w, h, nextFrame));
  }

  public void add(int x, int y, int w, int h, int nextFrame, int delay) {
    add(new Frame(x, y, w, h, nextFrame, delay));
  }


  public Frame getFrame() {
    return frames.get(currentFrame);
  }

  public int getSize() {
    return frames.size();
  }

  public int getCurrentFrame() {
    return currentFrame;
  }

  public void gotoFrame(int frame) {
    currentFrame = frame;
  }

  public void setFrameListener(FrameListener frameListener) {
    this.frameListener = frameListener;
  }

  @Override
  public void onFrameEnd(int frame) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onFrameStart(int frame) {
    // TODO Auto-generated method stub

  }
}
