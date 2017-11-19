package analisadores;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Semantico {
	
	public static int pos;
	
	static BufferedWriter escreverArquivo;
	
	public static void callSemantico (int reduce, int aux) {
		
		try {
			FileOutputStream stream = new FileOutputStream(
				"C:/Users/Matheus Paiva/Documents/GitHub/Comp-em-Java/PROGRAMA.txt");
			
			stream.getChannel().position(pos);
			
			escreverArquivo = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
		
			//Switch do reduce, para saber qual a redicao (Os return com espacos, terao acoes, os sem espacos n faz nd)
			switch(reduce) {
				case 1: return;
				case 2: return;
				case 3: return;
				case 4: return;
				case 5: 	escreverArquivo.newLine(); 
							escreverArquivo.newLine();
							escreverArquivo.newLine();
							escreverArquivo.newLine();
							escreverArquivo.write( "Texto a ser escrito no txt" );
							return;
				case 6: 	return;
				case 7: 	return;
				case 8: 	return;
				case 9: 	return;
				case 10: return;
				case 11: 	return;
				case 12: 	return;
				case 13: 	return;
				case 14: 	return;
				case 15: 	return;
				case 16: return;
				case 17: 	return;
				case 18: 	return;
				case 19: 	return;
				case 20: 	return;
				case 21: 	return;
				case 22: return;
				case 23: 	return;
				case 24: 	return;
				case 25: 	return;
				case 26: return;
				case 27: return;
				case 28: return;
				case 29: return;
				case 30: return;	
			}
			stream.close();
		} catch(IOException e) {
					System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
	}
}
