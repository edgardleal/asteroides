package teste.edgardleal.engine;

public class Nomes {
	private static int PRIMEIRO_NOME_COUNT=0;
	private static int SEGUNDO_NOME_COUNT=0;
	private static String primeiro_nome[] = {"ANTONIO","ANTONIA","BRUNO","BRUNA","CARLOS","CARLA","DIEGO","DEBORA",
			                          "EDUARDO","EDUARDA","FATIMA","FABIO","GABRIEL","GABRIELA","JEREMIAS",
			                          "JISELDA","MARTA","MIGUEL","ABEL","ABIGAIL","ABELARDO","ADALGIAS",
			                          "SORAIA","YASMIN","FUGITSU","DIEGO"};
	private static String segundo_nome[] = {"SANTOS","SILVA","MEDEIROS","ALMEIDA","BELTRÃO","FIGUEREDO","DUARTE",
			                         "MACHADO","MARTACENIA","AVELAR","FERREIRA"};
	public Nomes() {
		
	}
	public static int getPossibilidades(){
		return primeiro_nome.length * segundo_nome.length;
	}
	
	public static String getNome(){
		return primeiro_nome[PRIMEIRO_NOME_COUNT++%primeiro_nome.length] + " " +
		       segundo_nome[SEGUNDO_NOME_COUNT++%segundo_nome.length];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < Nomes.getPossibilidades(); i++) {
			System.out.println(Nomes.getNome());
		}
		System.out.println("Possibilidades : " + Nomes.getPossibilidades());
	}
}
