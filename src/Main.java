/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

*/

import java.util.Scanner;

import dados.*;
import analisadores.*;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Simbolo parada = Lexico.getLex(0);		//Simbolos são os lexemas que são retornados.
		Scanner s = new Scanner(System.in);		//Scanner para iterar sobre o retorno dos lexemas.
	
		
		do {	//Enquanto não é o ultimo lexema ou nao houver erros na abertura do arquivo.
			System.out.println
				("Lexema: "+parada.getLexema()+	//Imprime o Lexema.
				 "\nToken: "+parada.getToken()+		//Imprime o Token.
				 "\nTipo: "+parada.getTipo());		//Imprime o Tipo.
			

			s.nextLine();	//Ir para proximo.
			
			parada = Lexico.getLex(Lexico.pos);	//Procura o proximo Lexema.
			
		}while(parada != null);
		
		s.close();	//Fechar Scanner.
			
	}

}
