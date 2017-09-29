package analisadores;

import tabelas.*;
import dados.*;

public class Sintatico {
	
	public String getSentenca(String token) {
		EnumeracaoDaGramatica enum1 = new EnumeracaoDaGramatica(1,"P' -> P");
		EnumeracaoDaGramatica enum2 = new EnumeracaoDaGramatica(2,"P -> inicio V A");
		EnumeracaoDaGramatica enum3 = new EnumeracaoDaGramatica(3,"V -> varinicio LV");
		EnumeracaoDaGramatica enum4 = new EnumeracaoDaGramatica(4,"LV -> D LV");
		EnumeracaoDaGramatica enum5 = new EnumeracaoDaGramatica(5,"LV -> varfim;");
		EnumeracaoDaGramatica enum6 = new EnumeracaoDaGramatica(6,"D -> id TIPO;");
		EnumeracaoDaGramatica enum7 = new EnumeracaoDaGramatica(7,"TIPO -> int");
		EnumeracaoDaGramatica enum8 = new EnumeracaoDaGramatica(8,"TIPO -> real");
		EnumeracaoDaGramatica enum9 = new EnumeracaoDaGramatica(9,"TIPO -> lit");
		EnumeracaoDaGramatica enum10 = new EnumeracaoDaGramatica(10,"A -> ES A");
		EnumeracaoDaGramatica enum11 = new EnumeracaoDaGramatica(11,"ES -> leia id;");
		EnumeracaoDaGramatica enum12 = new EnumeracaoDaGramatica(12,"ES -> escreva ARG;");
		EnumeracaoDaGramatica enum13 = new EnumeracaoDaGramatica(13,"ARG -> literal");
		EnumeracaoDaGramatica enum14 = new EnumeracaoDaGramatica(14,"ARG -> num");
		EnumeracaoDaGramatica enum15 = new EnumeracaoDaGramatica(15,"ARG -> id");
		EnumeracaoDaGramatica enum16 = new EnumeracaoDaGramatica(16,"A -> CMD A");
		EnumeracaoDaGramatica enum17 = new EnumeracaoDaGramatica(17,"CMD -> id rcb LD;");
		EnumeracaoDaGramatica enum18 = new EnumeracaoDaGramatica(18,"LD -> OPRD opm OPRD");
		EnumeracaoDaGramatica enum19 = new EnumeracaoDaGramatica(19,"LD -> OPRD");
		EnumeracaoDaGramatica enum20 = new EnumeracaoDaGramatica(20,"OPRD -> id");
		EnumeracaoDaGramatica enum21 = new EnumeracaoDaGramatica(21,"OPRD -> num");
		EnumeracaoDaGramatica enum22 = new EnumeracaoDaGramatica(22,"A -> COND A");
		EnumeracaoDaGramatica enum23 = new EnumeracaoDaGramatica(23,"COND -> CABEÇALHO CORPO");
		EnumeracaoDaGramatica enum24 = new EnumeracaoDaGramatica(24,"CABEÇALHO -> se (EXP_R) então");
		EnumeracaoDaGramatica enum25 = new EnumeracaoDaGramatica(25,"EXP_R -> OPRD opr OPRD");
		EnumeracaoDaGramatica enum26 = new EnumeracaoDaGramatica(26,"CORPO -> ES CORPO");
		EnumeracaoDaGramatica enum27 = new EnumeracaoDaGramatica(27,"CORPO -> CMD CORPO");
		EnumeracaoDaGramatica enum28 = new EnumeracaoDaGramatica(28,"CORPO -> COND CORP");
		EnumeracaoDaGramatica enum39 = new EnumeracaoDaGramatica(29,"CORPO -> fimse");
		EnumeracaoDaGramatica enum30 = new EnumeracaoDaGramatica(29,"A -> fim");
		String a = "Oi";
		return a;
	}

}
