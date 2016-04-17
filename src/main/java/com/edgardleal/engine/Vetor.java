package com.edgardleal.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JDialog;

public class Vetor {
	private double x=1, y=1;
    private double direcao=0, raio=0;
	private double raioLimite=0;
	
    public Vetor(double x,double y){
    	setXY(x, y);
    }
    public Vetor(){
    	setXY(0, 0);
    }
    
    public double produtoInterno(Vetor v){
    	return (v.getX()*x)+(v.getY()*y);
    }
    
    /**retorna o produto externo entre este e outro vetor.
     * 
     * @param v
     * @return
     */
    public Vetor produtoExterno(Vetor v){
    	/* 1  1  1 1  1
    	 * x1 y1 1 x1 y1
    	 * x2 y2 1 x2 y2
    	 * 
    	 * result = 1*y1*1 + 1*1*x2 + 1*x1*y2 - 
    	 *          1*x1*1 - 1*1*y2 - 1*y1*x2 
    	 */
 
    	return new Vetor(y-x,v.getX()-v.getY());
    }
    
    public String toString(){
    	return "X:" + getX() + " , Y:" + getY() + " Raio:" + getRaio() + " �ngulo : " + getDirecao()*(180/Math.PI);
    }
    
    
	public void somar(double x, double y){
		if(raioLimite!=0 && calcModulo(x, y)+getModulo()>raioLimite ) return;
		this.x+=x;
		this.y+=y;
		calcRaio();
		calcDirecao();
	}
	
	private double calcModulo(double x, double y) {
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	}
	
	public void somar(Vetor v){
		somar(v.getX(),v.getY());
	}
	
	public void subtrair(double x, double y){
		this.x-=x;
		this.y-=y;
		calcRaio();
		calcDirecao();
	}
	/**Este m�todo faz a subtra��o do raio informado e resolve o problema de 
	 * tamanho do raio subtraido ser maior que o raio do vetor em que ser� removido.
	 * 
	 * @param v
	 */
	public void subtrair(Vetor v){
		double raio_anterior = v.getRaio();
		if(raio_anterior>raio) v.setRaio(raio);
		subtrair(v.getX(),v.getY());
		v.setRaio(raio_anterior);//devolve o valor original para o objeto par�metro
	}
	
	/**Informar o valor em radianos : um círculo = 2*Pi
	 * 
	 * @param direcao
	 */
	public void setDirecao(double direcao){
		direcao = direcao==Double.NaN?0:direcao;
		this.direcao = direcao;
		calcXY();
	}
	
	/**Informar o valor em graus : 0 -> 360 </br>
	 * OBS: o ângulo 0(zero) fica ao lado direito (3 horas)
	 * 
	 * @param angulo
	 */
	public void setDirecaoGraus(double angulo){
		angulo = angulo==Double.NaN?0:angulo;
		setDirecao(((angulo%360)/180)*Math.PI);
	}
	
	
	public void setXY(double x, double y){
		this.x = x;
		this.y = y;
		calcDirecao();
		calcRaio();
	}
	
	public void setRaio(double raio){
		this.raio = raio;
		calcXY();
	}
	
	public void setX(double x){
		setXY(x,this.y);
	}
	
	public void setY(double y){
		setXY(this.x,y);
	}
	
	public double getRaio(){
		raio = raio==Double.NaN?0:raio;
		return raio;
	}
	
	public double getX(){
		return x;
	}
	/**Retorna o valor da posição X para o raio informado de acordo ângulo atual.
	 * 
	 * @param r
	 * @return
	 */
	public int getXParaORaio(double r){
		return round(Math.cos(getDirecao())*r);
	}
	
	/**Função para arredondamento(Encapsulamento do cast (int)2.5 ).
	 * 
	 * @param v
	 * @return
	 */
	private int round(double v){
		return (int)v;
	}
	
	public double getY(){
		return y;
	}	
	/**Retorna a direção do vetor em Radianos.
	 * 
	 * @return
	 */
	public double getDirecao(){
		direcao = direcao==Double.NaN?0:direcao;
		return direcao;
	}
	
	
	public void multiplicar(double v){
		x = v*x;
		y = v*y;
		calcRaio();//a direção continua a mesma
	}
	
	public void inverter(){
		inverter(this);
	}
	/**Inverte a direção do vetor.
	 * 
	 * @param v
	 */
	public void inverter(Vetor v){
		direcao = (v.getDirecao()+Math.PI);
		direcao = direcao>Math.PI*2?direcao-Math.PI*2:direcao;
		setDirecao(direcao);//para atualização dos calculos necessários
	}
	
	public double getModulo(){
		return raio;
	}
	
	
	private void calcRaio(){
		raio  = Math.sqrt(Math.pow(x,2d)+ Math.pow(y,2d));
	}
	
	private void calcDirecao(){
		//vetor para orientação (1,0)
		direcao = Math.acos((x*1+y*0)/(calcModulo(x, y)*1));
  direcao = y>0?direcao+Math.PI:direcao;
	}
	
	private void calcXY(){
		x = Math.cos(direcao)*raio;
		y = Math.sin(direcao)*raio;
	}
	
	public void paint(java.awt.Graphics g){
		g.drawLine(100, 100,100 +(int)x, 100+(int)y);
	}

	public void setRaioLimite(double l){
		raioLimite = l;
	}
	
	public static void main(String[] args) {
		Vetor v = new Vetor(20,-20), //teste de mudan�a de dire��o
		b = new Vetor(20,20);
		b.setDirecaoGraus(45);
		System.out.println("B= " + b);
		System.out.println(v);
		v.inverter();
		System.out.println(v);
		v.somar(b);
		System.out.println(v);
		v.exibir();
		System.exit(0);
	}
	
	public void exibir(){
		Tela tela = new Tela(this);
		tela.setVisible(true);
	}
}

class Tela extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -382822471525634591L;
	private Vetor v;
	public Tela(Vetor v){
		this.v = v;
		setSize(400,400);
		Dimension d  = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2-200, d.height/2-200);
		setResizable(false);
		setUndecorated(true);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				setVisible(false);
			}
		});
		this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.red));
		setModal(true);
	}
	
	@Override
	public void paint(java.awt.Graphics g){
		DecimalFormat formato = new DecimalFormat("0.0");
		super.paint(g);
		g.setColor(Color.blue);
		g.fillOval(197, 197, 7, 7);
		g.drawLine(200, 200, (int)v.getX()+200, (int)v.getY()+200);
		g.drawString("" + formato.format(v.getX()) + " - " + formato.format(v.getY()), (int)v.getX()+190, (int)v.getY()+190);
	}
}