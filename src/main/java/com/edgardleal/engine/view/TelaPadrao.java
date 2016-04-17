package com.edgardleal.engine.view;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.CenarioListener;
import com.edgardleal.engine.GameTicker;

public class TelaPadrao extends JFrame implements KeyListener, CenarioListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2677448298977549188L;
	protected GameTicker gameTicker  = new GameTicker();
	
	public TelaPadrao(){
		super();
		setUndecorated(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
	}



	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyChar()==27) System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int fim(Cenario sender) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setFase(Cenario fase){
		this.setContentPane(fase);
	}

}
