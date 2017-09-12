import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tabelas.*;
import dados.*;
//É só um teste.
public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	String S[]; // Aqui vou colocar os valores da Tabela de Transição
    	
    	//Aqui eu crio a tabela de transicao e passo os valores para ela
    	TabeladeTransicao tabeladetransicao[][] = new TabeladeTransicao [28][24];
    	int t = 0;
        for (int i = 0; i < 62; i++) {
            for (int j = 0; j < 56; j++) {
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
    	
    	
        try {
            FileReader arquivo = new FileReader("C:/Users/Lucas Felipe/eclipse-workspace/Analisador Lexico/src/texto.txt");
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
