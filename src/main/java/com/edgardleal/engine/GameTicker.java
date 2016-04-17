package com.edgardleal.engine;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author edgardleal
 *
 */
public class GameTicker extends Thread {
  private static final Logger LOGGER = LoggerFactory.getLogger(GameTicker.class);
  private int delay = 15;
  private long time = 0L;
  private Vector<Tickeable> items = new Vector<Tickeable>();

  public GameTicker() {
    super();
    this.setPriority(10);
    LOGGER.debug("Gameticker started");
  }

  /**
   * Method add.
   * 
   * @param t Tickeable
   */
  public synchronized void add(Tickeable t) {
    items.add(t);
  }

  /**
   * Method run.
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    while (this != null) {
      time = time + 1 == Long.MAX_VALUE ? 0 : ++time;
      try {
        synchronized (this) {
          for (Tickeable t : items) {
            t.update(null);
          }
        }
        Thread.sleep((int) (delay));
      } catch (Exception e) {
        LOGGER.error("conflito na Thread", e);
      }// catch
    }// while
  }// run

  /**
   * Method getDelay.
   * 
   * @return int
   */
  public synchronized int getDelay() {
    return delay;
  }

  /**
   * Method setDelay.
   * 
   * @param delay int
   */
  public synchronized void setDelay(int delay) {
    this.delay = delay;
  }


}// class
