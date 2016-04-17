package com.edgardleal.telas;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.JApplet;

import com.edgardleal.engine.Cenario;
import com.edgardleal.engine.CenarioListener;
import com.edgardleal.engine.GameTicker;
import com.edgardleal.engine.MediaCenter;

public class AppletGeral extends JApplet implements KeyListener, CenarioListener{

	Fase01 fase01;
	Menu1 menu;
	Container tela;
	int x = 0;
	Graphics g;
	Image imgFundo;
	URL urlFundo;
	MediaCenter mediaCenter;
	GameTicker gameTicker = new GameTicker();

	private static final long serialVersionUID = 1L;

	/**Comentários para o Javadoc para o método Init
	 * 
	 */
	@Override 
	public void init(){
		setSize(500, 500);
		//mediaCenter = new MediaCenter(this);

		tela = this.getContentPane();
		this.setLayout(null);/*Desativa o gerenciador de Layout*/
		this.addKeyListener(this);
		this.requestFocus();
		try {
			iniciar();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop(){

	}

	private void iniciar() throws Exception{
		gameTicker.add(getFase01());
		gameTicker.setDelay(20);
		gameTicker.start();
		tela.add(getFase01());
	}

	public void keyPressed(KeyEvent key) {
		getFase01().keyDown((byte)key.getKeyCode());
		getFase01().notifyTecla((byte)key.getKeyCode());
		if (key.getKeyChar()==27) {
			getFase01().pause();
		}
	}

	public void keyReleased(KeyEvent key) {
		getFase01().keyUp((byte)key.getKeyCode());
	}

	public void keyTyped(KeyEvent key) {
	}

	@Override
	public int fim(Cenario sender) {
		tela.remove(menu);
		menu = null;
		tela.add(getFase01());// Adicionando o cenário 'inicio' no container
		return 0;
	}

	public void nextFase(){
		try {
			if (tela==getMenu1()) {
				getFase01().reset();
				tela = getFase01();
			}
			else if (tela==getFase01()) {
				getMenu1().reset();
				tela = getMenu1();
			}
			else{
				getMenu1().reset();
				tela = getMenu1();
			}
		} catch (Exception e) {
		}
	}

	public Fase01 getFase01(){
		if (fase01==null) 
			fase01 = new Fase01(this);
		return fase01;
	}
	public Menu1 getMenu1()throws Exception{
		if (menu==null) 
			menu = new Menu1(this);
		return menu;
	}
}
