package analisadores;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Stack;

import tabelas.*;
import dados.*;

public class Lexico {

	// Os seguintes atributos e metodos sao static para que possam ser acessados
	// pela main.

	public static int pos; // Devera conter a posicao em que a leitura esta no momento.
	public static int linhaerro = 1, colunaerro = 0; // contem a linha e coluna que o arquivo ja leu
	public static int erro = 0;

	static BufferedReader lerArquivo; // Buffer para leitura de arquivo.
	
	public static Simbolo getLex(int posi) { // Funcao que le o arquivo, acha Lexemas, insere lexemas na tabela e
												// retorna lexemas.
	
		pos = posi; // A posicao atual da leitura eh atualizada.

		long tam;
		
		

		int S[] = { 128,  1,  0, 69, 43, 45, 42, 47, 62, 60, 61, 40, 41, 59, 34,129,123,125,130, 10, 32, 32, 46, 95,
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
					 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 132, 15, 15,132, 15, 15, 15, 15, 15, 
				  	 16,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
					 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 132, 17, 18,132, 17, 17, 17, 17, 17, 
					 18,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
					 19, 19,  0, 22,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 20,  0, 
					 20, 21,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132, 
					 21, 21,  0, 22,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
					 22, 24,132,132, 23, 23,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132, 
					 23, 24,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132, 
					 24, 24,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
					 25, 25, 25,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 25, 
					 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
					132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132};
		/*
		 * Legenda da TABELA: Primeira linha utilizei a Tabela ASCII para fazer
		 * referencia aos caracteres. A Tabela ASCII vai ate 127, entao usei numeros
		 * acima desse valor como auxiliares. Numeros: 128 e o vertice da tabela, tinha
		 * que colocar um numero peguei o 128; 129 representa qualquer digito diferente
		 * dos referenciados nas colunas; 130 representa EOF; 131 representa a linha do
		 * estado inicial; 132 representa a linha dos erros
		 */

		// Aqui eu crio a pilha dos estados da tabela de transição
		Stack<Integer> estados = new Stack<Integer>();

		// Aqui eu crio a tabela de transicao e passo os valores para ela
		TabeladeTransicao tabeladetransicao[][] = new TabeladeTransicao[29][24];

		int t = 0;
		for (int i = 0; i < 29; i++) {
			for (int j = 0; j < 24; j++) {
				tabeladetransicao[i][j] = new TabeladeTransicao(S[t]);
				t++;
			}
		}

		// Aqui eu crio a tabela hash de simbolos
		TabeladeSimbolos tabelahash = new TabeladeSimbolos();

		// Aqui eu crio os Simbolos das Palavras-Chaves e coloco na Hash
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
		TabeladeErros tabelahashe = new TabeladeErros();

		// Preencho a Tabela de Erros
		tabelahashe.tabeladeerros.put(1, "Identificador nao permitido");
		tabelahashe.tabeladeerros.put(16, "Constantes literais nao permitidas");
		tabelahashe.tabeladeerros.put(18, "Erro de foramatacao de comentario (chaves)");
		tabelahashe.tabeladeerros.put(21, "Constantes numericas nao permitidas");
		tabelahashe.tabeladeerros.put(23, "Constantes numericas nao permitidas");
		tabelahashe.tabeladeerros.put(24, "Constantes numericas nao permitidas");
		
		try {
			StringBuilder bffCaracter = new StringBuilder(); // Buffer para caracteres.
			FileInputStream stream = new FileInputStream(
					"C:/Users/Lucas Felipe/Documents/GitHub/Comp-em-Java/texto.txt");

			int caracter = 0, linha = 1, coluna = 0; // Caracter para leitura dos caracteres.			
			
			stream.getChannel().position(pos); // Seta a posicao na qual o arquivo stream deve comecar.

			lerArquivo = new BufferedReader(new InputStreamReader(stream, "UTF-8")); // A leitura do arquivo stream no																				 // formato UTF-8.
			
			tam = stream.getChannel().size();
						
			estados.push(tabeladetransicao[linha][coluna].getElemento());// Coloca o estado inicial na pilha.

			while (pos <= tam || erro == 0) {// Enquanto nao e o ultimo caracter e nao tiver erros.
					
				caracter = lerArquivo.read();	

				/*--------------------- COLOCANDO A COLUNA CORRESPONDENTE AO CARACTER-------------------------------*/
				int test = 0;
							
				// Quando nao e um digito ou uma letra, procure a coluna do caracter.
				for (int i = 0; i < 24; i++) {
					if (tabeladetransicao[0][i].getElemento() == caracter) {
						coluna = i;
						test = 1;
					}
				}
				
				//Se eu n encontro a coluna correspondente ao caracter, quer dizer que esse cararcter
				//eh de outro tipo que nao se encontra na tabela de transicao
				//logo coloco ele na coluna correspondente a "restante dos caracteres"
				if (test == 0 && caracter != 10 && caracter != 13 && caracter != 32) coluna = 17;
				
				//Verifico se o caracter eh espaco, tabulacao ou pula linha
				if (test == 0 && (caracter == 10 || caracter == 13 || caracter == 32)) coluna = 19;
				
				//Verifico se eh EOF
				if(caracter == -1) coluna = 18;
				
				// Se eh digito
				if (Character.isDigit((char)caracter)) coluna = 1;

				// Se eh letra
				if (Character.isLetter((char)caracter)) coluna = 2;
				
				//Tratamento do E(exponencial)
				if((caracter == 69 || caracter == 101) && (estados.peek()==19 || estados.peek()==21)) coluna = 3;

				// Procura na tabela a linha do estado atual
				for (int i = 0; i < 29; i++) {
					// Se o estado atual for encontrado.
					if (tabeladetransicao[i][0].getElemento() == estados.peek()) {
						linha = i;
					}
				}
				
				//Coloco o estado na pilha
				estados.push(tabeladetransicao[linha][coluna].getElemento());
				
				/*--------------------- VERIFICO SE O ESTADO ENCONTRADO EH VAZIO OU SE EH DE ERRO -------------------------------*/
				// Se o estado resultado e 0, desempilha a pilha toda e apaga o buffer de caracteres. 
				// Va para o estado inicial do proximo lexema.
				if (tabeladetransicao[linha][coluna].getElemento() == 0) {
							 			
					// Verifico se o lexema já esta na Tabela de Simbolos
					// Se sim, retorno o valor da chave(lexema) contido na Tabela de Simbolos
					if (tabelahash.tabeladesimbolos.containsKey(bffCaracter.toString()) == true) {
						Simbolo aux;
						aux = tabelahash.tabeladesimbolos.get(bffCaracter.toString());
						return aux;

					// Se nao, vejo qual o estado de aceitacao que o lexema parou, adiciono na
					// Tabela de Simbolos e retorno o Token*/
					} else {
						Simbolo simaux;
						estados.pop();
						switch (estados.peek()) {
						case 1:
							simaux = new Simbolo(bffCaracter.toString(), "opr", " ");
							return simaux;

						case 2:
							simaux = new Simbolo(bffCaracter.toString(), "opr", " ");
							return simaux;

						case 3:
							simaux = new Simbolo("EOF", "EOF", " ");
							return simaux;

						case 4:
							simaux = new Simbolo(bffCaracter.toString(), "opr", " ");
							return simaux;

						case 5:
							simaux = new Simbolo(bffCaracter.toString(), "opr", " ");
							return simaux;

						case 6:
							simaux = new Simbolo(bffCaracter.toString(), "rcb", " ");
							return simaux;

						case 7:
							simaux = new Simbolo(bffCaracter.toString(), "opr", " ");
							return simaux;

						case 8:
							simaux = new Simbolo(bffCaracter.toString(), "pt_v", " ");
							return simaux;

						case 9:
							simaux = new Simbolo(bffCaracter.toString(), "ab_p", " ");
							return simaux;

						case 10:
							simaux = new Simbolo(bffCaracter.toString(), "fc_p", " ");
							return simaux;

						case 11:
							simaux = new Simbolo(bffCaracter.toString(), "opm", " ");
							return simaux;

						case 12:
							simaux = new Simbolo(bffCaracter.toString(), "opm", " ");
							return simaux;

						case 13:
							simaux = new Simbolo(bffCaracter.toString(), "opm", " ");
							return simaux;

						case 14:
							simaux = new Simbolo(bffCaracter.toString(), "opm", " ");
							return simaux;

						case 26:
							simaux = new Simbolo(bffCaracter.toString(), "opm", " ");
							return simaux;

						case 16:
							simaux = new Simbolo(bffCaracter.toString(), "literal", "literal");
							return simaux;		
						
						case 18:
							System.out.println("Comentário: "+bffCaracter);

						case 19:
							simaux = new Simbolo(bffCaracter.toString(), "num", "inteiro");
							return simaux;

						case 21:
							simaux = new Simbolo(bffCaracter.toString(), "num", "real");
							return simaux;

						case 24:
							simaux = new Simbolo(bffCaracter.toString(), "num", "real");
							return simaux;

						case 25:
							simaux = new Simbolo(bffCaracter.toString(), "id", " ");
							tabelahash.tabeladesimbolos.put(simaux.getLexema(), simaux);
							return simaux;

						default:
							System.out.println("Erro na leitura da pilha ou formato de comentario errado!");
							System.out.println("\nLinha: "+linhaerro+" Coluna: "+colunaerro);
							erro = 1;							
													
						}

					}
					
					// Esvazia a pilha.
					while (!estados.isEmpty())
						estados.pop();
					
					// Volte o estado atual para inicial do proximo lexema.
					estados.push(tabeladetransicao[1][coluna].getElemento());
					
					// Apagar o conteúdo do buffer.
					bffCaracter.delete(0, bffCaracter.length());

				}
				
				/*--------------------- VERIFICANDO ERROS, ADICIONANDO CARACTER NO BUFFER E INCREMENTANDO AS VARIAVEIS -------------------------------*/
				// Verificando erros
				if (tabeladetransicao[linha][coluna].getElemento() == 132) {
					System.out.println("ERRO ENCONTRADO - " + tabelahashe.tabeladeerros.get(linha));
					System.out.println("\nLinha na tabela: "+linha+" Coluna: "+coluna);
					System.out.println("\nLinha: "+linhaerro+" Coluna: "+colunaerro);
					
					erro = 1;
					Simbolo simerro = new Simbolo ("ERRO","ERRO"," ");
					return simerro;
				}
				
				// Buffer de caracteres recebe o caractere atual.
				if (caracter != 10 && caracter != 13 && caracter != 32)
					bffCaracter.append((char) caracter);
				
				pos++; // Toda vez que um caracter eh lido, a posicao eh incrementada.
				
				//verifica se ele encontrou um pula linha, se sim incrementa o contador de linha e zera
				//o contador de coluna
				//Se nao incrementa o contador de coluna
				if (caracter == 13) {
					linhaerro++; 
					colunaerro=0;
				}else {
					colunaerro++;
				}				
			}

			stream.close();
			lerArquivo.close();

		} catch (

		IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		return null;
	}
	
	public static void getLinhaeColuna () {
		System.out.println("Linha: "+linhaerro+" Coluna: "+colunaerro);
	}
	
}
