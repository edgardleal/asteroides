package com.edgardleal.telas;

import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.CenarioListener;

public class Menu1 extends Cenario {
	private Image imgFundo;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7330968515069117294L;

	public Menu1(JApplet applet)throws MalformedURLException {
		super((CenarioListener)applet);
		imgFundo = applet.getImage(new URL(applet.getDocumentBase(),"img/fundo_menu.png"));
		this.setBackground(Color.black);
		super.setImgFundo(this.imgFundo);
		setBounds(0, 0, 500, 500);
		this.repaint();
	}
	@Override
	public void paint(java.awt.Graphics g){
		super.paint(g);
		if(getTimer()%300==0) cenarioListener.fim(this);
	}
	
	@Override
	public void reset(){
		super.reset();
		
	}
}
