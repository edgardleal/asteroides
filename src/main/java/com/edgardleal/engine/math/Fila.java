package com.edgardleal.engine.math;

/**Implementação do algoritmo de pilha para verificação de estisticas 
 * com alto desempenho e de tamaho personalizavel.
 * 
 * @author Edgard
 *
 */
public class Fila {
	private double items[];
	private double max = 0.0, min = Double.MAX_VALUE,
	totalItems = 0.0, totalValor = 0.0;
	
	public Fila(){
		items = new double[10];
		init();
	}
	
	public Fila(int size){
		items = new double[size];
		init();
	}
	
	public void put(double item){
		min = min>item?item:min;
		max = max<item?item:max;
		totalItems = totalItems+1==Double.MAX_VALUE?1:++totalItems;
		totalValor += item;
		alloc();
		items[items.length-1] = item;
	}
	
	public double get(int i){
		return items[i];
	}
	
	private void alloc(){
		for (int i = 0; i < items.length-1; items[i] = items[++i]); 
	}
	
	private void init(){
		for (int i = 0; i < items.length; i++) 
			items[i] = 0.0;
	}
	
	public double getMax(){
		return max;
	}
	
	public double getMin(){
		return min;
	}
	
	public double getTotal(){
		return totalValor;
	}
	
	public double getMedia(){
		return totalValor / totalItems;
	}
	
	public static void main(String[] args) {
		Fila fila  = new Fila();
		fila.put(5);
		fila.put(2);
		fila.put(4);
		fila.put(1);
		System.out.println(fila);
	}
	
	public String toString(){
		return "Max : " + max + "; Min : " + min + "; Items : " + totalItems + "; Total : " +
		 totalValor + "; Media : " + getMedia();
	}
}
