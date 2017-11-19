package analisadores;

import tabelas.*;
import dados.*;

import java.util.Scanner;
import java.util.Stack;


public class Sintatico {
	
	private static Scanner s;

	public static void analisadorSintatico() {
		
		s = new Scanner(System.in);
		
		//Pilha de estados do analisador sintatico.
		Stack<Integer> estados = new Stack<Integer>();
		
		//Para navegar nas tabelas:
		int state;

		//O primeiro simbolo do buffer.
		Simbolo simbolo = Lexico.getLex(0);		//simbolos sao os lexemas que serao retornados.
		
		//O primeiro estado na pilha.
		estados.push(0);
		
		String erroencontrado;
		
		while(true) {
			
			//Encontre o estado no topo da pilha.
			state = estados.peek();
			
			if(state == 132){
				estados.pop();
				state = estados.peek();
				erroencontrado = getErro(state);				
				System.out.println("ERRO ENCONTRADO NO ESTADO "+state+" - " + erroencontrado);
				Lexico.getLinhaeColuna();
				return;
			}
			
			//Se havera empilhamento:
				//Pela regra definida na tabela ACTION, se o numero eh maior que 0, indica empilhamento.
				//Confira tambem se nao eh o estado de aceitacao.
			if((getACTION(state, simbolo.getToken()) > 0) && (getACTION(state, simbolo.getToken()) != 151)) { 
				
				//System.out.println("Estado: "+state+"\nTerminal: "+simbolo.getToken()+"\nEmpilha: "+getACTION(state, simbolo.getToken())+"\n");
				
				estados.push(getACTION(state, simbolo.getToken()));
				
				simbolo = Lexico.getLex(Lexico.pos);	//Procura o proximo Lexema.
				
			}
			
			//Se havera reducao:
				//Pela regra definida na tabela ACTION, se o numero eh menor que 0, indica empilhamento.
			else if(getACTION(state, simbolo.getToken()) <= 0) {
				
				//Para voltar ao numero verdadeiro da producao a ser reduzida.
				int reduce = getACTION(state, simbolo.getToken()) * (-1);
				
				//Encontra a sentenca que esta sendo reduzida.
				String sentenca = getSentenca(reduce);
		
				//Para encontrar a quantidade de simbolos de Beta.
				int qtdSimbolosBeta = 0;
				boolean isBeta = false;
				
				//Para guarda qual o nao terminal.
				StringBuilder nonterminal = new StringBuilder();
				
				//Recupera a quantidade de simbolos Beta.
				for(int i = 0; i < sentenca.length(); i++) {
					//Soh passa a considerar palavras apos ->.
					if(sentenca.charAt(i) == '-' && sentenca.charAt(i + 1) == '>')
						isBeta = true;
				
					if(sentenca.charAt(i) == ' ' && isBeta == true)
						qtdSimbolosBeta++;
				
				}
				
				int j = 0;
				//Para recuperar o nao-terminal
				while(sentenca.charAt(j) != ' ') {
					nonterminal.append(sentenca.charAt(j));
					j++;
				}
				
				//Desempilha qtdSimbolosBeta de estados.
				for(int i = 0; i < qtdSimbolosBeta; i++) 
					estados.pop();
				
				//Faca o estado t ser o topo da pilha.
				state = estados.peek();
			
				//Empilhe GOTO[t, A].
				estados.push(getGOTO(state, nonterminal.toString()));

				//System.out.println("Estado: "+state+"\nNao terminal: "+nonterminal+"\nTransicao: "+getGOTO(state, nonterminal.toString())+"\n");
				
				//Imprima a producao A -> B.
				System.out.println(reduce+" Sentenca reduzida: "+sentenca+"\n");
				
				//Faca a chamada para o Semantico com o numero da reducao
				Semantico.callSemantico(reduce, Semantico.pos);
				
				s.nextLine();
			}
			
			//Se ha aceitacao
			else if(getACTION(state, simbolo.getToken()) == 151) {
				System.out.println("Aceitacao");
				return;
			}
	
		}
		
	}
	
	public static String getSentenca(int line) {//Recebe a linha a ser reduzida e devolve a produÃ§Ã£o

		EnumeracaoDaGramatica[] Enumeracao = new EnumeracaoDaGramatica[30];
		// Atencao: A linha 1 da gramatica eh o elemento 0 da enumeracao. Por isso,
		//no retorno o elemento retornado eh line - 1.
		Enumeracao[0]  = new EnumeracaoDaGramatica(1,"P' -> P");
		Enumeracao[1]  = new EnumeracaoDaGramatica(2,"P -> inicio V A");
		Enumeracao[2]  = new EnumeracaoDaGramatica(3,"V -> varinicio LV");
		Enumeracao[3]  = new EnumeracaoDaGramatica(4,"LV -> D LV");
		Enumeracao[4]  = new EnumeracaoDaGramatica(5,"LV -> varfim ;");
		Enumeracao[5]  = new EnumeracaoDaGramatica(6,"D -> id TIPO ;");
		Enumeracao[6]  = new EnumeracaoDaGramatica(7,"TIPO -> int");
		Enumeracao[7]  = new EnumeracaoDaGramatica(8,"TIPO -> real");
		Enumeracao[8]  = new EnumeracaoDaGramatica(9,"TIPO -> lit");
		Enumeracao[9] = new EnumeracaoDaGramatica(10,"A -> ES A");
		Enumeracao[10] = new EnumeracaoDaGramatica(11,"ES -> leia id ;");
		Enumeracao[11] = new EnumeracaoDaGramatica(12,"ES -> escreva ARG ;");
		Enumeracao[12] = new EnumeracaoDaGramatica(13,"ARG -> literal");
		Enumeracao[13] = new EnumeracaoDaGramatica(14,"ARG -> num");
		Enumeracao[14] = new EnumeracaoDaGramatica(15,"ARG -> id");
		Enumeracao[15] = new EnumeracaoDaGramatica(16,"A -> CMD A");
		Enumeracao[16] = new EnumeracaoDaGramatica(17,"CMD -> id rcb LD ;");
		Enumeracao[17] = new EnumeracaoDaGramatica(18,"LD -> OPRD opm OPRD");
		Enumeracao[18] = new EnumeracaoDaGramatica(19,"LD -> OPRD");
		Enumeracao[19] = new EnumeracaoDaGramatica(20,"OPRD -> id");
		Enumeracao[20] = new EnumeracaoDaGramatica(21,"OPRD -> num");
		Enumeracao[21] = new EnumeracaoDaGramatica(22,"A -> COND A");
		Enumeracao[22] = new EnumeracaoDaGramatica(23,"COND -> CABECALHO CORPO");
		Enumeracao[23] = new EnumeracaoDaGramatica(24,"CABECALHO -> se ( EXP_R ) entao");
		Enumeracao[24] = new EnumeracaoDaGramatica(25,"EXP_R -> OPRD opr OPRD");
		Enumeracao[25] = new EnumeracaoDaGramatica(26,"CORPO -> ES CORPO");
		Enumeracao[26] = new EnumeracaoDaGramatica(27,"CORPO -> CMD CORPO");
		Enumeracao[27] = new EnumeracaoDaGramatica(28,"CORPO -> COND CORP");
		Enumeracao[28] = new EnumeracaoDaGramatica(29,"CORPO -> fimse");
		Enumeracao[29] = new EnumeracaoDaGramatica(30,"A -> fim");
		
		return Enumeracao[line-1].getSentenca();

	}
	
	public static String getErro (int line) {
		
		TabeladeErros tabelaerrosintatico = new TabeladeErros();
		tabelaerrosintatico.tabeladeerros.put(0, "E0 - Codigo inicializado de forma incorreta - inicio");
		tabelaerrosintatico.tabeladeerros.put(2, "E2 - Inicio de declaracao de varaveis nao declarado - varfim");
		tabelaerrosintatico.tabeladeerros.put(3, "E3 - Tipos de dados, elementos de estrutura condicional (entao e fimse), delimitacao de declaracao de variaveis, atribuiao, operadores (relacionais e aritmeticos), inicio de programa e declaracao de variaveis, parenteses ou ponto e virgula nao permitidos");
		tabelaerrosintatico.tabeladeerros.put(4, "E4 - Espaco para declaracao de variaveis: Necessario declaracao das variaveis, nao sendo permitidos atribuicoes, operacoes, estruturas condicionais, parenteses ou ponto e virgula");
		tabelaerrosintatico.tabeladeerros.put(6, "E6 - Tipos de dados, elementos de estrutura condicional (entao e fimse), delimitacao de declaracao de variaveis, atribuiao, operadores (relacionais e aritmeticos), inicio de programa e declaracao de variaveis, parenteses ou ponto e virgula nao permitidos");
		tabelaerrosintatico.tabeladeerros.put(7, "E7 - Tipos de dados, elementos de estrutura condicional (entao e fimse), delimitacao de declaracao de variaveis, atribuiao, operadores (relacionais e aritmeticos), inicio de programa e declaracao de variaveis, parenteses ou ponto e virgula nao permitidos");
		tabelaerrosintatico.tabeladeerros.put(8, "E8 - Tipos de dados, elementos de estrutura condicional (entao e fimse), delimitacao de declaracao de variaveis, atribuiao, operadores (relacionais e aritmeticos), inicio de programa e declaracao de variaveis, parenteses ou ponto e virgula nao permitidos");
		tabelaerrosintatico.tabeladeerros.put(9, "E9 - Tipos de dados, elementos de estrutura condicional (entao e fim), delimitacao de declaracao de variaveis, atribuicao, operadores (relacionais e aritmeticos), inicio de programa e declaracao de variaveis, parenteses ou ponto e virgula nao permitidos");
		tabelaerrosintatico.tabeladeerros.put(11, "E11 - Leitura de variaveis: esperavasse um identificador");
		tabelaerrosintatico.tabeladeerros.put(12, "E12 - Escrita de variaveis: esperavasse um identificador, um inteiro ou um literal");
		tabelaerrosintatico.tabeladeerros.put(13, "E13 - Atribuicao de variaveis: esperavasse uma atribuicao");
		tabelaerrosintatico.tabeladeerros.put(14, "E14 - Inicio de expressao do condicional SE: esperavasse um abre parenteses");
		tabelaerrosintatico.tabeladeerros.put(16, "E16 - Espaco para declaracao de variaveis: esperavasse um varfim ou um identificador");
		tabelaerrosintatico.tabeladeerros.put(17, "E17 - Final de atribuicao, expressao, escrita, leitura ou espaco para declaracao de variaveis: esperavasse um ponto e virgula");
		tabelaerrosintatico.tabeladeerros.put(18, "E18 - Espaco para declaracao de variaveis: esperavasse um tipo para o identificador");
		tabelaerrosintatico.tabeladeerros.put(23, "E23 - Tipos de dados, elementos de estrutura condicional (entao e fim), delimitacao de declaracao de variaveis, atribuicao, operadores (relacionais e aritmeticos), inicio de programa e declaracao de variaveis, parenteses ou ponto e virgula nao permitidos");
		tabelaerrosintatico.tabeladeerros.put(24, "E24 - Tipos de dados, elementos de estrutura condicional (entao e fim), delimitacao de declaracao de variaveis, atribuicao, operadores (relacionais e aritmeticos), inicio de programa e declaracao de variaveis, parenteses ou ponto e virgula nao permitidos");
		tabelaerrosintatico.tabeladeerros.put(25, "E25 - Tipos de dados, elementos de estrutura condicional (entao e fim), delimitacao de declaracao de variaveis, atribuicao, operadores (relacionais e aritmeticos), inicio de programa e declaracao de variaveis, parenteses ou ponto e virgula nao permitidos");
		tabelaerrosintatico.tabeladeerros.put(27, "E27 - Final de atribuicao, expressao, escrita, leitura ou espaco para declaracao de variaveis: esperavasse um ponto e virgula");
		tabelaerrosintatico.tabeladeerros.put(28, "E28 - Final de atribuicao, expressao, escrita, leitura ou espaco para declaracao de variaveis: esperavasse um ponto e virgula");
		tabelaerrosintatico.tabeladeerros.put(32, "E32 - Atribuicao de variaveis ou expressao aritmetica: esperavasse um identificador ou um numeral");
		tabelaerrosintatico.tabeladeerros.put(33, "E33 - Atribuicao de variaveis ou expressao relacional: esperavasse um identificador ou um numeral");
		tabelaerrosintatico.tabeladeerros.put(36, "E36 - Final de atribuicao, expressao, escrita, leitura ou espaco para declaracao de variaveis: esperavasse um ponto e virgula");
		tabelaerrosintatico.tabeladeerros.put(44, "E44 - Final de atribuicao, expressao, escrita, leitura ou espaco para declaracao de variaveis: esperavasse um ponto e virgula");
		tabelaerrosintatico.tabeladeerros.put(45, "E45 - Expressao aritmetica: esperavasse um operador aritmetica");
		tabelaerrosintatico.tabeladeerros.put(48, "E48 - Fim de expressao do condicional SE: esperavasse um fecha parenteses");
		tabelaerrosintatico.tabeladeerros.put(49, "E49 - Expressao relacional: esperavasse um operador relacional");
		tabelaerrosintatico.tabeladeerros.put(53, "E53 - Expressao aritmetica: esperavasse um identificador ou um numeral");
		tabelaerrosintatico.tabeladeerros.put(54, "E54 - Estrutura condicional SE: esperavasse um entao");
		tabelaerrosintatico.tabeladeerros.put(57, "E57 - Expressao relacional: esperavasse um identificador ou um numeral");
		
		return tabelaerrosintatico.tabeladeerros.get(line);
	}
	
	//Tabela GOTO
	
	public static int getGOTO(int line, String nonTerminal) {
		//Onde Line eh o estado atual e nonTerminal eh o nao-terminal
		int G[] = {
				128, 80, 86, 65,133, 68,134,135,136,137,138,139,140,141,142,143,
				0  ,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				1  ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				2  ,  0,  3,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				3  ,  0,  0,  5,  0,  0,  0,  6,  0,  7,  0,  0,  8,  9,  0,  0,
				4  ,  0,  0,  0, 15, 16,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				5  ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				6  ,  0,  0, 19,  0,  0,  0,  6,  0,  7,  0,  0,  8,  9,  0,  0,
				7  ,  0,  0, 20,  0,  0,  0,  6,  0,  7,  0,  0,  8,  9,  0,  0,
				8  ,  0,  0, 21,  0,  0,  0,  6,  0,  7,  0,  0,  8,  9,  0,  0,
				9  ,  0,  0,  0,  0,  0,  0, 23,  0, 24,  0,  0, 25,  9, 22,  0,
				10 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				11 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				12 ,  0,  0,  0,  0,  0,  0,  0, 28,  0,  0,  0,  0,  0,  0,  0,
				13 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				14 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				15 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				16 ,  0,  0,  0, 34, 16,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				17 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				18 ,  0,  0,  0,  0,  0, 36,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				19 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				20 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				21 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				22 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				23 ,  0,  0,  0,  0,  0,  0, 23,  0, 24,  0,  0, 25,  9, 40,  0,
				24 ,  0,  0,  0,  0,  0,  0, 23,  0, 24,  0,  0, 25,  9, 41,  0,
				25 ,  0,  0,  0,  0,  0,  0, 23,  0, 24,  0,  0, 25,  9, 50,  0,
				26 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				27 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				28 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				29 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				30 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				31 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				32 ,  0,  0,  0,  0,  0,  0,  0,  0,  0, 44, 45,  0,  0,  0,  0,
				33 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 49,  0,  0,  0, 48,
				34 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				35 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				36 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				37 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				38 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				39 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				40 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				41 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				42 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				43 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				44 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				45 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				46 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				47 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				48 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				49 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				50 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				51 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				52 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				53 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 55,  0,  0,  0,  0,
				54 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				55 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				56 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
				57 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 58,  0,  0,  0,  0,
				58 ,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
		};
		
		/*
		 * Legenda da TABELA GOTO: Primeira linha utilizei a Tabela ASCII para fazer
		 * referencia aos caracteres(Os nao-terminais formados por mais de uma letra
		 * tem digitos especiais). 
		 * Os 59 estados(0 a 58 sao a primeira coluna da tabela)
		 * A Tabela ASCII vai ate 127, entao usei numeros
		 * acima desse valor como auxiliares. Numeros: 128 e o vertice da tabela, tinha
		 * que colocar um numero peguei o 128;
		 * Os erros sao marcados por 132
		 * 		LV    = 133.	TIPO  = 134.	ES        = 135.
	 	 * 		ARG   = 136.   	CMD   = 137.	LD        = 138.
	 	 * 		OPRD  = 139.	COND  = 140.	CABECALHO = 141.
	 	 * 		CORPO = 142.	EXP_R = 143.	
	 	 */
	
	
		TabelaSintatica tabelaGOTO[][] = new TabelaSintatica[60][16];
	
		int t = 0;
	
		//Aqui se adicionam os valores na tabela
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 16; j++) {
				tabelaGOTO[i][j] = new TabelaSintatica(G[t]);
				t++;
			}
		}
		
		//A variavel word serve como auxiliar que guarda o numero de cada terminal na tabela.
		int word = 0;
		int column = 0;
		
		//Verifique a qual decimal o nao-terminal estah relacionado.
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
			
			case "P":
				word = 80;
				break;
			
			case "V":
				word = 86;
				break;
			
			case "A":
				word = 65;
				break;
			
			case "D":
				word = 68;
				break;
		}
		
		//Aqui se procura qual a coluna do nao-terminal a partir de seu respectivo numero
		for(int i = 0; i < 16; i++) {
			if(tabelaGOTO[0][i].getElemento() == word) {
				column = i;
			}
		}
		
		return tabelaGOTO[line+1][column].getElemento();
		//Line +1: A primeira linha da tabela eh apenas de terminais.
		//Dessa forma, o estado 0 estah na linha 1, o estado 1 na linha 2
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
		 * Os Estados que sao empilhados sao positivos.
		 * Os Estados que serao reduzidos sao negativos.  
		 * Os 59 estados(0 a 58 sÃ£o a primeira coluna da tabela) sao inseridos como 
		 * objetos Acao com string nula.
		 * A Tabela ASCII vai ate 127, entao usei numeros
		 * acima desse valor como auxiliares. Numeros: 128 e o vertice da tabela, tinha
		 * que colocar um numero peguei o 128;
		 * Os erros sao marcados por 132
		 * A aceitacao eh marcada por 151 
		 * Os terminais de soh uma letra (incluindo $), por exemplo '*', sao referenciados 
		 * por seu proprio inteiro na tabela ASCII; no caso de *, 34.
		 * Legenda dos terminais de mais de uma letra:
		 * 		inicio       = 133.		varinicio  = 134.	varfim  = 135.
	 	 * 		int(inteiro) = 136.   	real       = 137.	lit     = 138.
	 	 * 		leia         = 139.	    id         = 140.	escreva = 141.
	 	 * 		literal      = 142.	    num        = 143.	rcb (<-)= 144.
	 	 * 		opm		     = 145.	    se		   = 146.   entao   = 147.
	 	 * 		opr		     = 148.	    fimse	   = 149.   fim     = 150.
		 *	
		 *	ERROS:
		 *
		 *		Os erros nas linhas que soh possuem empilhamento sao sinalizados por 132 e cada
		 *	uma dessas linhas tem seu retorno de erro (Frase que indica o que houve).
		 *		Os erros nas linhas em que ha uma reducao sao marcados como 0. Para identificar
		 *	qual foi a reducao propagada, eh feita uma busca na linha ateh encontrar a reducao. 
	 	 */
		
		TabelaSintatica tabelaACTION[][] = new TabelaSintatica[60][23];
		
		int t = 0;
		
		//Aqui se faz a passagem de valores para a tabela.
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 23; j++) {
				tabelaACTION[i][j] = new TabelaSintatica(A[t]); 
				t++;
			}
		}
		
		//A variavel word serve como auxiliar que guarda o numero de cada terminal na tabela.
		int word   = 0;
		int column = 0;

		//Verifique a qual decimal word estah relacionado.
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
		
		case "inteiro":
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
		
		case "pt_v":
			word = 59;
			break;
		
		case "ab_p":
			word = 40;
			break;
		
		case "fc_p":
			word = 41;
			break;
		
		case "EOF":	
			word = 36;
			break;
		
		case "id":	
			word = 140;
			break;
		}
	
		//Aqui se procura qual a coluna do terminal a partir de seu respectivo numero
		for(int i = 0; i < 23; i++) {
			if(tabelaACTION[0][i].getElemento() == word) {		
				column = i;
			}
		}
		
		return tabelaACTION[line+1][column].getElemento();
			//Line +1: A primeira linha da tabela eh apenas de terminais.
			//Dessa forma, o estado 0 estah na linha 1, o estado 1 na linha 2
			//e assim por diante.
	}
			
}
