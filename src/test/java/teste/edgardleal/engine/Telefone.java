package teste.edgardleal.engine;

import java.util.HashMap;

public class Telefone {
	private static final String numeroBase = "45264878";
	private static int NUMERO_COUNT = 0;
	public Telefone() {
		// TODO Auto-generated constructor stub
	}

	public static String getNumero(){
		String result = "";
		for (int i = 0; i < 8; i++) {
			result += String.valueOf((Integer.valueOf(numeroBase.substring(i,i+1))
					+NUMERO_COUNT++)%10
					);
		}
		return result;
	}
	public static String getNumeroFormatado(){
		String result = Telefone.getNumero();
		return result.substring(0,4) + "-"+result.substring(4);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, String> mapa  = new HashMap<String, String>();
		String numero = "";
		for (int i = 0; i < 20; i++) {
			numero = Telefone.getNumeroFormatado();
			if (mapa.get(numero)!=null) {
				System.out.println("Numero repetido : " + numero);
				System.out.println(i+1+" números gerados ");
				return;
			}
			mapa.put(numero, "ok");
		}
	}
}
