package com.edgardleal.engine;

import java.util.Vector;


public class GameTicker extends Thread {
	private int delay = 15;
	private long time = 0L;
	private Vector<Tickeable> items = new Vector<Tickeable>();

	public GameTicker() {
		super();
		this.setPriority(10);

	}
	public void add(Tickeable t){
		items.add(t);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	@Override 
	public void run(){
		while (this!=null) {
			time = time+1==Long.MAX_VALUE?0:++time;
			try {
				synchronized(this){
					for (Tickeable t : items)
						t.update(null);
				}
				Thread.sleep((int)(delay));
			} catch (Exception e) {
				e.printStackTrace();
			}//catch
		}//while
	}//run

	public synchronized int getDelay() {
		return delay;
	}

	public synchronized void setDelay(int delay) {
		this.delay = delay;
	}


}//class
