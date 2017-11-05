package analisadores;

import tabelas.*;
import dados.*;

import java.util.Scanner;
import java.util.Stack;


public class Sintatico {
	
	private static Scanner s;

	public static void analisadorSintatico() {
		
		s = new Scanner(System.in);
		
		//Pilha de estados do analisador sintático.
		Stack<Integer> estados = new Stack<Integer>();
		
		//Para navegar nas tabelas:
		int state;

		//O primeiro símbolo do buffer.
		Simbolo simbolo = Lexico.getLex(0);		//simbolos são os lexemas que são retornados.
		
		//O primeiro estado na pilha.
		estados.push(0);
		
		while(true) {
			
			//Encontre o estado no topo da pilha.
			state = estados.peek();
			
			//Se haverá empilhamento:
				//Pela regra definida na tabela ACTION, se o numero é maior que 0, indica empilhamento.
				//Confira também se não é o estado de aceitação.
			if((getACTION(state, simbolo.getLexema()) > 0) && (getACTION(state, simbolo.getLexema()) != 151)) { 
				System.out.println(" Estado: "+state+" \tTerminal: "+simbolo.getLexema()+
						"\tEmpilha: "+getACTION(state, simbolo.getLexema()));
				estados.push(getACTION(state, simbolo.getLexema()));
				simbolo = Lexico.getLex(Lexico.pos);	//Procura o proximo Lexema.
				
			}
			
			//Se haverá redução:
				//Pela regra definida na tabela ACTION, se o numero é menor que 0, indica empilhamento.
				//Se o numero for 0 é um falso empilhamento. O tratamento é feito aqui????
			else if(getACTION(state, simbolo.getLexema()) <= 0) {
				//Para voltar ao numero verdadeiro da produção a ser reduzida.
				int reduce = getACTION(state, simbolo.getLexema()) * (-1);
				//Encontra a sentença que está sendo reduzida.
				String sentenca = getSentenca(reduce);
				System.out.println("\nSenten�a reduzida: "+sentenca);
				//Para encontrar a quantidade de símbolos de Beta.
				int qtdSimbolosBeta = 0;
				boolean isBeta = false;
				
				//Para guarda qual o não terminal.
				StringBuilder nonterminal = new StringBuilder();
				
				//Recupera a quantidade de simbolos Beta.
				for(int i = 0; i < sentenca.length(); i++) {
					//Só passa a considerar palavras após ->.
					if(sentenca.charAt(i) == '-' && sentenca.charAt(i + 1) == '>')
						isBeta = true;
				
					if(sentenca.charAt(i) == ' ' && isBeta == true)
						qtdSimbolosBeta++;
				
				}
				
				System.out.println("\nQuantidade de simbolos: "+qtdSimbolosBeta);
				
				int j = 0;
				//Para recuperar o não-terminal
				while(sentenca.charAt(j) != ' ') {
					nonterminal.append(sentenca.charAt(j));
					j++;
				}
				
				//Desempilha qtdSimbolosBeta de estados.
				for(int i = 0; i < qtdSimbolosBeta; i++) 
					estados.pop();
				
				//Faça o estado t ser o topo da pilha.
				state = estados.peek();
			
				//Empilhe GOTO[t, A].
				estados.push(getGOTO(state, nonterminal.toString()));

				System.out.println("\nEstado: "+state+" N�o terminal: "+nonterminal);
				System.out.println("Indo pro estado: "+getGOTO(state, nonterminal.toString()));
				
				//Imprima a produção A -> B.
				System.out.println("Senten�a reduzida:"+sentenca);
				s.nextLine();
			}
			
			//Se há aceitação
			else if(getACTION(state, simbolo.getLexema()) == 151) {
				System.out.println("Aceitação");
				s.nextLine();
			}
			
			//Senão, há erro.
			else{
				//Mostre o erro.
				System.out.println("Chame uma rotina de tratamento de erro");
				s.nextLine();
			}
		}
		
	}
	
	public static String getSentenca(int line) {//Recebe a linha a ser reduzida e devolve a produção

		EnumeracaoDaGramatica[] Enumeracao = new EnumeracaoDaGramatica[30];
		// Aten��o: A linha 1 da gram�tica � o elemento 0 da enumera��o. Por isso,
		//no retorno o elemento retornado � line - 1.
		Enumeracao[0]  = new EnumeracaoDaGramatica(1,"P' -> P");
		Enumeracao[1]  = new EnumeracaoDaGramatica(2,"P -> inicio V A");
		Enumeracao[2]  = new EnumeracaoDaGramatica(3,"V -> varinicio LV");
		Enumeracao[3]  = new EnumeracaoDaGramatica(4,"LV -> D LV");
		Enumeracao[4]  = new EnumeracaoDaGramatica(5,"LV -> varfim;");
		Enumeracao[5]  = new EnumeracaoDaGramatica(6,"D -> id TIPO;");
		Enumeracao[6]  = new EnumeracaoDaGramatica(7,"TIPO -> int");
		Enumeracao[7]  = new EnumeracaoDaGramatica(8,"TIPO -> real");
		Enumeracao[8]  = new EnumeracaoDaGramatica(9,"TIPO -> lit");
		Enumeracao[9] = new EnumeracaoDaGramatica(10,"A -> ES A");
		Enumeracao[10] = new EnumeracaoDaGramatica(11,"ES -> leia id;");
		Enumeracao[11] = new EnumeracaoDaGramatica(12,"ES -> escreva ARG;");
		Enumeracao[12] = new EnumeracaoDaGramatica(13,"ARG -> literal");
		Enumeracao[13] = new EnumeracaoDaGramatica(14,"ARG -> num");
		Enumeracao[14] = new EnumeracaoDaGramatica(15,"ARG -> id");
		Enumeracao[15] = new EnumeracaoDaGramatica(16,"A -> CMD A");
		Enumeracao[16] = new EnumeracaoDaGramatica(17,"CMD -> id rcb LD;");
		Enumeracao[17] = new EnumeracaoDaGramatica(18,"LD -> OPRD opm OPRD");
		Enumeracao[18] = new EnumeracaoDaGramatica(19,"LD -> OPRD");
		Enumeracao[19] = new EnumeracaoDaGramatica(20,"OPRD -> id");
		Enumeracao[20] = new EnumeracaoDaGramatica(21,"OPRD -> num");
		Enumeracao[21] = new EnumeracaoDaGramatica(22,"A -> COND A");
		Enumeracao[22] = new EnumeracaoDaGramatica(23,"COND -> CABEÇALHO CORPO");
		Enumeracao[23] = new EnumeracaoDaGramatica(24,"CABEÇALHO -> se (EXP_R) então");
		Enumeracao[24] = new EnumeracaoDaGramatica(25,"EXP_R -> OPRD opr OPRD");
		Enumeracao[25] = new EnumeracaoDaGramatica(26,"CORPO -> ES CORPO");
		Enumeracao[26] = new EnumeracaoDaGramatica(27,"CORPO -> CMD CORPO");
		Enumeracao[27] = new EnumeracaoDaGramatica(28,"CORPO -> COND CORP");
		Enumeracao[28] = new EnumeracaoDaGramatica(29,"CORPO -> fimse");
		Enumeracao[29] = new EnumeracaoDaGramatica(29,"A -> fim");
		
		return Enumeracao[line-1].getSentenca();

	}
	//Tabela GOTO]
	
	public static int getGOTO(int line, String nonTerminal) {
		//Onde Line é o estado atual e nonTerminal é o não-terminal
		int G[] = {
				128, 80, 86, 65,133, 68,134,135,136,137,138,139,140,141,142,143,
				0  ,  1,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				1  ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				2  ,132,  3,132,132,132,132,132,132,132,132,132,132,132,132,132,
				3  ,132,132,  5,132,132,132,  6,132,  7,132,132,  8,  9,132,132,
				4  ,132,132,132, 15, 16,132,132,132,132,132,132,132,132,132,132,
				5  ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				6  ,132,132, 19,132,132,132,  6,132,  7,132,132,  8,  9,132,132,
				7  ,132,132, 20,132,132,132,  6,132,  7,132,132,  8,  9,132,132,
				8  ,132,132, 21,132,132,132,  6,132,  7,132,132,  8,  9,132,132,
				9  ,132,132,132,132,132,132, 23,132, 24,132,132, 25,  9, 22,132,
				10 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				11 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				12 ,132,132,132,132,132,132,132, 28,132,132,132,132,132,132,132,
				13 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				14 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				15 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				16 ,132,132,132, 34, 16,132,132,132,132,132,132,132,132,132,132,
				17 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				18 ,132,132,132,132,132, 36,132,132,132,132,132,132,132,132,132,
				19 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				20 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				21 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				22 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				23 ,132,132,132,132,132,132, 23,132, 24,132,132, 25,  9, 40,132,
				24 ,132,132,132,132,132,132, 23,132, 24,132,132, 25,  9, 41,132,
				25 ,132,132,132,132,132,132, 23,132, 24,132,132, 25,  9, 50,132,
				26 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				27 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				28 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				29 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				30 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				31 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				32 ,132,132,132,132,132,132,132,132,132, 44, 45,132,132,132,132,
				33 ,132,132,132,132,132,132,132,132,132,132,132,132,132, 49, 48,
				34 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				35 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				36 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				37 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				38 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				39 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				40 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				41 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				42 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				43 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				44 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				45 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				46 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				47 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				48 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				49 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				50 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				51 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				52 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				53 ,132,132,132,132,132,132,132,132,132,132, 55,132,132,132,132,
				54 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				55 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				56 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132,
				57 ,132,132,132,132,132,132,132,132,132,132, 58,132,132,132,132,
				58 ,132,132,132,132,132,132,132,132,132,132,132,132,132,132,132
		};
		
		/*
		 * Legenda da TABELA GOTO: Primeira linha utilizei a Tabela ASCII para fazer
		 * referencia aos caracteres(Os não-terminais formados por mais de uma letra
		 * tem digitos especiais). 
		 * Os 59 estados(0 a 58 são a primeira coluna da tabela)
		 * A Tabela ASCII vai ate 127, entao usei numeros
		 * acima desse valor como auxiliares. Numeros: 128 e o vertice da tabela, tinha
		 * que colocar um numero peguei o 128;
		 * Os erros são marcados por 132
		 * Os não terminais de só uma letra, por exemplo 'A', são referenciados por seu
		 * próprio inteiro na tabela ASCII; no caso de A, 65.
		 * Legenda dos não-terminais de mais de uma letra:
		 * 		LV    = 133.	TIPO  = 134.	ES        = 135.
	 	 * 		ARG   = 136.   	CMD   = 137.	LD        = 138.
	 	 * 		OPRD  = 139.	COND  = 140.	CABECALHO = 141.
	 	 * 		CORPO = 142.	EXP_R = 143.	
	 	 */
	
	
		TabelaSintatica tabelaGOTO[][] = new TabelaSintatica[60][16];
	
		int t = 0;
	
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 16; j++) {
				tabelaGOTO[i][j] = new TabelaSintatica(G[t]); // Ta com erro pq eu n coloquei valores para S
				t++;
			}
		}
		int word = 0;
		int column = 0;
		//Em caso de não-terminais de mais de uma letra, verifique a qual decimal ele está relacionado.
		switch(nonTerminal) {
			case "LV":
				word = 133;
				break;
			case "TIPO":
				word = 134;
				break;
			case "ES":
				word = 135;
				break;
			case "ARG":
				word = 136;
				break;
			case "CMD":
				word = 137;
				break;
			case "LD":
				word = 138;
				break;
			case "OPRD":
				word = 139;
				break;
			case "COND":
				word = 140;
				break;
			case "CABECALHO":
				word = 141;
				break;
			case "CORPO":
				word = 142;
				break;
			case "EXP_R":
				word = 143;
				break;
		}
		for(int i = 0; i < 16; i++) {
			if(tabelaGOTO[0][i].getElemento() == word) {
				column = i;
			}
		}
		
		return tabelaGOTO[line+1][column].getElemento();
		//Line +1: A primeira linha da tabela é apenas de terminais.
		//Dessa forma, o estado 0 está na linha 1, o estado 1 na linha 2
		//e assim por diante.
	}
	
	public static int getACTION(int line, String terminal) {
		
		int A[] = {
				128, 133, 134, 135,  59, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146,  40,  41, 147, 148, 149, 150,  36,
				  0,   2, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				  1, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 151,
				  2, 132,   4, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				  3, 132, 132, 132, 132, 132, 132, 132,  11,  13,  12, 132, 132, 132, 132,  14, 132, 132, 132, 132, 132,  10, 132,
				  4, 132, 132,  17, 132, 132, 132, 132, 132,  18, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				  5,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,  -2,
				  6, 132, 132, 132, 132, 132, 132, 132,  11,  13,  12, 132, 132, 132, 132,  14, 132, 132, 132, 132, 132,  10, 132,
				  7, 132, 132, 132, 132, 132, 132, 132,  11,  13,  12, 132, 132, 132, 132,  14, 132, 132, 132, 132, 132,  10, 132,
				  8, 132, 132, 132, 132, 132, 132, 132,  11,  13,  12, 132, 132, 132, 132,  14, 132, 132, 132, 132, 132,  10, 132,
				  9, 132, 132, 132, 132, 132, 132, 132,  11,  13,  12, 132, 132, 132, 132,  14, 132, 132, 132, 132,  26, 132, 132,
				 10, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30, -30,
				 11, 132, 132, 132, 132, 132, 132, 132, 132,  27, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 12, 132, 132, 132, 132, 132, 132, 132, 132,  31, 132,  29,  30, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 13, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,  32, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 14, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,  33, 132, 132, 132, 132, 132, 132,
				 15,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,  -3,
				 16, 132, 132,  17, 132, 132, 132, 132, 132,  18, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 17, 132, 132, 132,  35, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 18, 132, 132, 132, 132,  37,  38, 132, 132, 132, 132,  39, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 19, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10,
				 20, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16, -16,
				 21, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22, -22,
				 22, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23, -23,
				 23, 132, 132, 132, 132, 132, 132, 132,  11,  13,  12, 132, 132, 132, 132,  14, 132, 132, 132, 132,  26, 132, 132,
				 24, 132, 132, 132, 132, 132, 132, 132,  11,  13,  12, 132, 132, 132, 132,  14, 132, 132, 132, 132,  26, 132, 132,
				 25, 132, 132, 132, 132, 132, 132, 132,  11,  13,  12, 132, 132, 132, 132,  14, 132, 132, 132, 132,  26, 132, 132,
				 26, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29, -29,
				 27, 132, 132, 132,  42, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 28, 132, 132, 132,  42, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 29, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13, -13,
				 30, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14, -14,
				 31, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15, -15,
				 32, 132, 132, 132, 132, 132, 132, 132, 132,  46, 132, 132,  47, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 33, 132, 132, 132, 132, 132, 132, 132, 132,  46, 132, 132,  47, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 34,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,  -4,
				 35,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,  -5,
				 36, 132, 132, 132,  51, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 37,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,  -7,
				 38,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,  -8,
				 39,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,
				 40, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26, -26,
				 41, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27, -27,
				 42, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11, -11,
				 43, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12, -12,
				 44, 132, 132, 132,  52, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 45, 132, 132, 132, -19, 132, 132, 132, 132, 132, 132, 132, 132, 132,  53, 132, 132, 132, 132, 132, 132, 132, 132,	
				 46, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20, -20,
				 47, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21, -21,
				 48, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,  54, 132, 132, 132, 132, 132,
				 49, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,  57, 132, 132, 132,
				 50, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28, -28,
				 51,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,  -6,
				 52, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17, -17,
				 53, 132, 132, 132, 132, 132, 132, 132, 132,  46, 132, 132,  47, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 54, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,  56, 132, 132, 132, 132,
				 55, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18, -18,
				 56, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24, -24,
				 57, 132, 132, 132, 132, 132, 132, 132, 132,  46, 132, 132,  47, 132, 132, 132, 132, 132, 132, 132, 132, 132, 132,
				 58, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25, -25
			};
		/*
		 * Legenda da TABELA ACTION: Primeira linha utilizei a Tabela ASCII para fazer
		 * referencia aos caracteres(Os terminais formados por mais de uma letra
		 * tem digitos especiais).
		 * Os Estados que são empilhados são positivos.
		 * Os Estados que serão reduzidos são negativos.  
		 * Os 59 estados(0 a 58 são a primeira coluna da tabela) são inseridos como 
		 * objetos Acao com string nula.
		 * A Tabela ASCII vai ate 127, entao usei numeros
		 * acima desse valor como auxiliares. Numeros: 128 e o vertice da tabela, tinha
		 * que colocar um numero peguei o 128;
		 * Os erros são marcados por 132
		 * A aceitação é marcada por 151 
		 * Os terminais de só uma letra (incluindo $), por exemplo '*', são referenciados 
		 * por seu próprio inteiro na tabela ASCII; no caso de *, 34.
		 * Legenda dos terminais de mais de uma letra:
		 * 		inicio   = 133.		varinicio  = 134.	varfim  = 135.
	 	 * 		int      = 136.   	real       = 137.	lit     = 138.
	 	 * 		leia     = 139.	    id         = 140.	escreva = 141.
	 	 * 		literal  = 142.	    num        = 143.	rcb     = 144.
	 	 * 		opm		 = 145.	    se		   = 146.   entao   = 147.
	 	 * 		opr		 = 148.	    fimse	   = 149.   fim     = 150.
		 *	
		 *	ERROS:
		 *
		 *		Os erros nas linhas que só possuem empilhamento são sinalizados por 132 e cada
		 *	uma dessas linhas tem seu retorno de erro (Frase que indica o que houve).
		 *		Os erros nas linhas em que há uma redução são marcados como 0. Para identificar
		 *	qual foi a redução propagada, é feita uma busca na linha até encontrar a redução. 
	 	 */
		
		TabelaSintatica tabelaACTION[][] = new TabelaSintatica[60][23];
		
		int t = 0;
	
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 23; j++) {
				tabelaACTION[i][j] = new TabelaSintatica(A[t]); // Ta com erro pq eu n coloquei valores para S
				t++;
			}
		}
		int word = 0;
		int column = 0;

		//Em caso de terminais de mais de uma letra, verifique a qual decimal ele está relacionado.
		switch(terminal) {
		case "inicio":
			word = 133;
			break;
		case "varinicio":
			word = 134;
			break;
		case "varfim":
			word = 135;
			break;
		case "int":
			word = 136;
			break;
		case "real":
			word = 137;
			break;
		case "lit":
			word = 138;
			break;
		case "leia":
			word = 139;
			break;
		case "escreva":
			word = 141;
			break;
		case "literal":
			word = 142;
			break;
		case "num":
			word = 143;
			break;
		case "rcb":
			word = 144;
			break;
		case "opm":
			word = 145;
			break;
		case "se":
			word = 146;
			break;
		case "entao":
			word = 147;
			break;
		case "opr":
			word = 148;
			break;
		case "fimse":
			word = 149;
			break;
		case "fim":
			word = 150;
			break;
		case ";":
			word = 59;
			break;
		default://Quando � um id
			word = 140;
			break;
		}
	
		for(int i = 0; i < 23; i++) {
			if(tabelaACTION[0][i].getElemento() == word) {		
				column = i;
			}
		}
		
		return tabelaACTION[line+1][column].getElemento();
			//Line +1: A primeira linha da tabela é apenas de terminais.
			//Dessa forma, o estado 0 está na linha 1, o estado 1 na linha 2
			//e assim por diante.
	}
	
	
	
	
	
	
}
