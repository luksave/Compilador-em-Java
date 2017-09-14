import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tabelas.*;
import dados.*;

public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	
    	int S[]= {128,  1,  0, 69, 43, 45, 42, 47, 62, 60, 61, 40, 41, 59, 34,129,123,126,130, 32, 32, 32, 46, 95,
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
    		       25, 25,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 25,
    		       26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
    		      132,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0}; 
    	/*Legenda da TABELA:
    		Primeira linha utilizei a Tabela ASCII para fazer referência aos caracteres.
    		A Tabela ASCII vai até 127, então usei números acima desse valor como auxiliares.
    		Números: 128 é o vértice da tabela, tinha que colocar um número peguei o 128
    				 129 representa qualquer digito diferente dos referênciados nas colunas
    				 130 representa EOF
    				 131 representa a linha do estado inicial
    				 132 representa a linha dos erros*/
    	
    	//Aqui eu crio a tabela de transicao e passo os valores para ela
    	TabeladeTransicao tabeladetransicao[][] = new TabeladeTransicao [28][23];
    	int t = 0;
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 23; j++) {
                tabeladetransicao[i][j] = new TabeladeTransicao(S[t]); // Ta com erro pq eu n coloquei valores para S
                t++;
            }
        }
    	
    	//Aqui eu crio a tabela hash de simbolos 
        TabeladeSimbolos tabelahash = new TabeladeSimbolos();
        
        //Aqui eu crio os Simbolos das Palavras-Chaves e coloco na Hash - P.S. -> Não testei essa Hash, temos que testar... 
    	Simbolo sim1 = new Simbolo ("inicio", "inicio", " ");
    	tabelahash.tabeladesimbolos.put("inicio", sim1);
    	Simbolo sim2 = new Simbolo ("varinicio", "varinicio", " ");
    	tabelahash.tabeladesimbolos.put("varinicio", sim2);
    	Simbolo sim3 = new Simbolo ("varfim", "varfim", " ");
    	tabelahash.tabeladesimbolos.put("varfim", sim3);
    	Simbolo sim4 = new Simbolo ("escreva", "escreva", " ");
    	tabelahash.tabeladesimbolos.put("escreva", sim4);
    	Simbolo sim5 = new Simbolo ("leia", "leia", " ");
    	tabelahash.tabeladesimbolos.put("leia", sim5);
    	Simbolo sim6 = new Simbolo ("se", "se", " ");
    	tabelahash.tabeladesimbolos.put("se", sim6);
    	Simbolo sim7 = new Simbolo ("entao", "entao", " ");
    	tabelahash.tabeladesimbolos.put("entao", sim7);
    	Simbolo sim8 = new Simbolo ("fimse", "fimse", " ");
    	tabelahash.tabeladesimbolos.put("fimse", sim8);
    	Simbolo sim9 = new Simbolo ("fim", "fim", " ");
    	tabelahash.tabeladesimbolos.put("fim", sim9);
    	Simbolo sim10 = new Simbolo ("inteiro", "inteiro", " ");
    	tabelahash.tabeladesimbolos.put("inteiro", sim10);
    	Simbolo sim11 = new Simbolo ("literal", "literal", " ");
    	tabelahash.tabeladesimbolos.put("literal", sim11);
    	Simbolo sim12 = new Simbolo ("real", "real", " ");
    	tabelahash.tabeladesimbolos.put("real", sim12);
    	
    	//Aqui eu crio a tabela hash de erros
    	TabeladeErros tabelahasherros = new TabeladeErros();
    	
        try {
            FileReader arquivo = new FileReader("C:/Users/Lucas Felipe/Documents/GitHub/Comp-em-Java/texto.txt");
            BufferedReader lerArquivo = new BufferedReader(arquivo);

            int caracter = 0;

            while ((caracter = lerArquivo.read()) != -1) {//Enquanto não é o último caracter
        
            	System.out.println((char) caracter);
            
            }
            
            arquivo.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

    }

}
