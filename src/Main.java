import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import tabelas.*;
import dados.*;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int S[] = {128,  1,  0, 69, 43, 45, 42, 47, 62, 60, 61, 40, 41, 59, 34,129,123,126,130, 10, 32, 32, 46, 95,
				   131, 19, 25,132, 11, 12, 13, 14,  4,  1, 26,  9, 10,  8, 15,132, 17,132,  3,131,131,131,132,132,
				   	 1,  0,  0,  0,  0,  6,  0,  0,  7,  0,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	 2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	 3,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	 4,  0,  0,  0,  0,  0,  0,  0,  0,  0,  5,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	 5,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	 6,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	 7,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	 8,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	 9,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	10,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	11,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	12,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	13,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	14,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 15, 15, 15,132, 15, 15, 15, 15, 15,
				   	16,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18,132, 17, 17, 17, 17, 17,
				   	18,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	19, 19,  0, 22,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 20,  0,
				   	20, 21,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				   	21, 21,  0, 22,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	22, 24,132,132, 23, 23,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				   	23, 24,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				   	24, 24,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	25, 25,  25,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 25,
				   	26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				   	132,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}; 
		/*
		 * Legenda da TABELA: Primeira linha utilizei a Tabela ASCII para fazer
		 * refer√™ncia aos caracteres. A Tabela ASCII vai at√© 127, ent√£o usei n√∫meros
		 * acima desse valor como auxiliares. N√∫meros: 128 √© o v√©rtice da tabela,
		 * tinha que colocar um n√∫mero peguei o 128 129 representa qualquer digito
		 * diferente dos refer√™nciados nas colunas 130 representa EOF 131 representa a
		 * linha do estado inicial 132 representa a linha dos erros
		 */

		// Aqui eu crio a pilha dos estados da tabela de transiÁ„o
		Stack<Integer> estados = new Stack<Integer>();

		// Aqui eu crio a tabela de transicao e passo os valores para ela
		TabeladeTransicao tabeladetransicao[][] = new TabeladeTransicao[29][24];

		int t = 0;
		for (int i = 0; i < 29; i++) {
			for (int j = 0; j < 24; j++) {
				tabeladetransicao[i][j] = new TabeladeTransicao(S[t]); // Ta com erro pq eu n coloquei valores para S
				t++;
			}
		}

		// Aqui eu crio a tabela hash de simbolos
		TabeladeSimbolos tabelahash = new TabeladeSimbolos();

		// Aqui eu crio os Simbolos das Palavras-Chaves e coloco na Hash - P.S. -> N√£o
		// testei essa Hash, temos que testar...
		Simbolo sim1 = new Simbolo("inicio", "inicio", " ");
		tabelahash.tabeladesimbolos.put("inicio", sim1);
		Simbolo sim2 = new Simbolo("varinicio", "varinicio", " ");
		tabelahash.tabeladesimbolos.put("varinicio", sim2);
		Simbolo sim3 = new Simbolo("varfim", "varfim", " ");
		tabelahash.tabeladesimbolos.put("varfim", sim3);
		Simbolo sim4 = new Simbolo("escreva", "escreva", " ");
		tabelahash.tabeladesimbolos.put("escreva", sim4);
		Simbolo sim5 = new Simbolo("leia", "leia", " ");
		tabelahash.tabeladesimbolos.put("leia", sim5);
		Simbolo sim6 = new Simbolo("se", "se", " ");
		tabelahash.tabeladesimbolos.put("se", sim6);
		Simbolo sim7 = new Simbolo("entao", "entao", " ");
		tabelahash.tabeladesimbolos.put("entao", sim7);
		Simbolo sim8 = new Simbolo("fimse", "fimse", " ");
		tabelahash.tabeladesimbolos.put("fimse", sim8);
		Simbolo sim9 = new Simbolo("fim", "fim", " ");
		tabelahash.tabeladesimbolos.put("fim", sim9);
		Simbolo sim10 = new Simbolo("inteiro", "inteiro", " ");
		tabelahash.tabeladesimbolos.put("inteiro", sim10);
		Simbolo sim11 = new Simbolo("literal", "literal", " ");
		tabelahash.tabeladesimbolos.put("literal", sim11);
		Simbolo sim12 = new Simbolo("real", "real", " ");
		tabelahash.tabeladesimbolos.put("real", sim12);

		// Aqui eu crio a tabela hash de erros
		TabeladeErros tabelahasherros = new TabeladeErros();

		try {
			FileReader arquivo = new FileReader("C:/Users/Matheus Paiva/Documents/GitHub/Comp-em-Java/texto.txt");
			BufferedReader lerArquivo = new BufferedReader(arquivo);// Buffer para arquivo.
			StringBuilder bffCaracter = new StringBuilder(); // Buffer para caracteres.

			int caracter = 0, linha = 1, coluna = 0; // Caracter para leitura dos caracteres.
			Scanner s = new Scanner(System.in); // Para ler algo do teclado e iterar o while.

			estados.push(tabeladetransicao[linha][coluna].getElemento());// Coloca o estado inicial na pilha.
			System.out.println("Estado inicial: " + estados.peek());

			while ((caracter = lerArquivo.read()) != -1) {// Enquanto n√o È o ˙ltimo caractere.

				// Se o caracter estiver entre 48 e 57 È um digito. Portanto coluna dos digitos
				if (caracter >= 48 && caracter <= 57)
					coluna = 1;

				// Se o caracter estiver entre 97 e 122 ou entre 65 e 90 È uma Letra. Portanto
				// coluna das letras
				if ((caracter >= 97 && caracter <= 122) || (caracter >= 65 && caracter <= 90))
					coluna = 2;

				// Quando n„o È um digito ou uma letra, procure a coluna do caracter.
				else if(caracter != 10 && caracter != 13 && caracter != 32)
					for (int i = 0; i < 24; i++)
						if (tabeladetransicao[0][i].getElemento() == caracter)
							coluna = i;

				if (caracter != 10 && caracter != 13 && caracter != 32) {
					// Procura na tabela a linha do estado atual
					for (int i = 0; i < 29; i++)
						// Se o estado atual for encontrado.
						if (tabeladetransicao[i][0].getElemento() == estados.peek()) {
							// Se o estado resultado n„o for o estado atual.
							if (tabeladetransicao[i][coluna].getElemento() != estados.peek()) {
								// Se o estado resultado È 0, apague a pilha e o buffer. Volte ao inÌcio.
								if (tabeladetransicao[i][coluna].getElemento() == 0) {
									// Esvazia a pilha.
									while (!estados.isEmpty())
										estados.pop();
									// Volte o estado atual para inicial.
									estados.push(tabeladetransicao[1][0].getElemento());
									// Enviar o conte˙do do buffer para tabela de sÌmbolos.
									// Falta essa parte aqui
									// Apagar o conte˙do do buffer.
									bffCaracter.delete(0, bffCaracter.length());

								}
								// Sen„o atualize o estado atual.
								else
									estados.push(tabeladetransicao[i][coluna].getElemento());

							}
						}
					
					//Buffer de caracteres recebe o caractere atual. 
            		bffCaracter.append((char)caracter);
            		
					// Todo esse print È apenas para provar o funcionamento da pilha.
					System.out.println(" - Caracter: " + (char) caracter + " - Dec: " + caracter + " - Estado atual: "
							+ estados.peek() + " - Lexema atual: " + bffCaracter);
					
					s.nextLine();// Para iterar na leitura do arquivo.
				}
			}

			s.close();
			arquivo.close();

		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

	}

}
