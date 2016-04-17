package teste.edgardleal.engine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPF {
	private static int CPF_COUNT=0;
	private String numero="";
	public CPF(String numero) {
		this.numero = numero;
	}
	public CPF(){
		this.numero = CPF.getCPF();
	}
	
	public boolean validar(String numero){
		if(!isCPF(numero)) return false;
		int total=0;
		numero = numero.replaceAll("\\D", "");//limpesa de caracteres não númericos 
		String digitoOriginal=numero.substring(9, 11), digitoCalculado="";
		for (int i = 2; i > 0; i--) {
			total = 0;
			for (int j = 0; j < 9+(2-i); j++) 
				total += Integer.parseInt(numero.substring(j,j+1))*(j+(i-1));
			digitoCalculado+=String.valueOf((total%11)%10);
		}
		return digitoOriginal.equals(digitoCalculado);
	}
	
	
	/**Verifica a validade do número interno desta classe.
	 * 
	 * @return
	 */
	public boolean validar(){
		return validar(numero);
	}
	
	public static String getCPF(){
		int numero[] = new int[11],
		    total=0;
		String result = "";
		for (int i = 0; i < 9; i++){ 
			numero[i] = (int)(Math.random()*9);
			total += numero[i]*(i+1);
		}
		numero[9] = (total%11)%10;
		total =0 ;
		for (int i = 0; i < 10; i++){ 
			total += numero[i]*i;
			result += String.valueOf(numero[i]);
		}
		CPF.CPF_COUNT++;
		result = result + String.valueOf((total%11)%10);
		return result;
	}
	
	/**verifica os formado para cpf inclusive o tamanho.
	 * 
	 * @param cpf
	 * @return
	 */
	public boolean isCPF(String cpf){
		Pattern p = Pattern.compile("(\\d{11})|(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})");
		Matcher m  = p.matcher(cpf);
		return m.matches();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(CPF.getCPF());	
		}
		
	}
}
