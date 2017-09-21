import java.util.Scanner;

import dados.*;
import analisadores.*;
public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Simbolo simbolo = Lexico.getLex(0);		//simbolobolos são os lexemas que são retornados.
		Scanner s = new Scanner(System.in);		//Scanner para iterar sobre o retorno dos lexemas.
	
		int aux=0;
		
		do {	//Enquanto não é o ultimo lexema ou nao houver erros na abertura do arquivo.
			
				System.out.println
				("Lexema: "+simbolo.getLexema()+	//Imprime o Lexema.
				 "\nToken: "+simbolo.getToken()+	//Imprime o Token.
				 "\nTipo: "+simbolo.getTipo());	//Imprime o Tipo.
			
			
			s.nextLine();	//Ir para proximo.
			
			simbolo = Lexico.getLex(Lexico.pos);	//Procura o proximo Lexema.
			
			if(simbolo.getToken().equals("EOF") || simbolo.getToken().equals("ERRO") || simbolo == null) aux=1;
			
		}while(aux==0);
		
		if(!(simbolo.getToken().equals("ERRO"))){
			System.out.println
			("Lexema: "+simbolo.getLexema()+	//Imprime o Lexema.
			"\nToken: "+simbolo.getToken()+	//Imprime o Token.
		 	"\nTipo: "+simbolo.getTipo());	//Imprime o Tipo.
		}
		
		s.close();	//Fechar Scanner.
			
	}

}
