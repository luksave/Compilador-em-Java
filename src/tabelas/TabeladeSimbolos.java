package tabelas;

import java.util.Hashtable;
import dados.*;

public class TabeladeSimbolos {
	
	public static Hashtable<String,Simbolo> tabeladesimbolos;
	
	public static void iniciarTabeladeSimbolos(){
		TabeladeSimbolos.tabeladesimbolos = new Hashtable <String, Simbolo> ();
		Simbolo sim1 = new Simbolo("inicio", "inicio", " ");
		TabeladeSimbolos.tabeladesimbolos.put("inicio", sim1);
		Simbolo sim2 = new Simbolo("varinicio", "varinicio", " ");
		TabeladeSimbolos.tabeladesimbolos.put("varinicio", sim2);
		Simbolo sim3 = new Simbolo("varfim", "varfim", " ");
		TabeladeSimbolos.tabeladesimbolos.put("varfim", sim3);
		Simbolo sim4 = new Simbolo("escreva", "escreva", " ");
		TabeladeSimbolos.tabeladesimbolos.put("escreva", sim4);
		Simbolo sim5 = new Simbolo("leia", "leia", " ");
		TabeladeSimbolos.tabeladesimbolos.put("leia", sim5);
		Simbolo sim6 = new Simbolo("se", "se", " ");
		TabeladeSimbolos.tabeladesimbolos.put("se", sim6);
		Simbolo sim7 = new Simbolo("entao", "entao", " ");
		TabeladeSimbolos.tabeladesimbolos.put("entao", sim7);
		Simbolo sim8 = new Simbolo("fimse", "fimse", " ");
		TabeladeSimbolos.tabeladesimbolos.put("fimse", sim8);
		Simbolo sim9 = new Simbolo("fim", "fim", " ");
		TabeladeSimbolos.tabeladesimbolos.put("fim", sim9);
		Simbolo sim10 = new Simbolo("inteiro", "inteiro", " ");
		TabeladeSimbolos.tabeladesimbolos.put("inteiro", sim10);
		Simbolo sim11 = new Simbolo("literal", "literal", " ");
		TabeladeSimbolos.tabeladesimbolos.put("literal", sim11);
		Simbolo sim12 = new Simbolo("real", "real", " ");
		TabeladeSimbolos.tabeladesimbolos.put("real", sim12);
	}
	
}
