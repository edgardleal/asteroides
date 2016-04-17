package com.edgardleal.engine;

public interface Colidivel {
	void colidiu(Colidivel c);
	public int getWidth();
	public int getHeight();
	public int getX();
	public int getY();
	public int getRaio();
	public boolean isSolid();
}
