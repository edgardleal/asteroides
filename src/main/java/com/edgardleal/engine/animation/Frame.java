package com.edgardleal.engine.animation;

public class Frame {
  int delay = 1;
  int time = 0;
  int nextFrame = -1;
  int x, y, w, h;

  public Frame(int x, int y, int w, int h) {
    setBounds(x, y, w, h);
  }

  public Frame(int x, int y, int w, int h, int nextFrame) {
    setBounds(x, y, w, h);
    this.nextFrame = nextFrame;
  }

  public Frame(int x, int y, int w, int h, int nextFrame, int delay) {
    setBounds(x, y, w, h);
    this.nextFrame = nextFrame;
    this.delay = delay;
  }

  public void setBounds(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  public int getDelay() {
    return delay;
  }

  public int getTime() {
    return time;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getW() {
    return w;
  }

  public int getH() {
    return h;
  }
}
