package tabelas;

import java.util.Hashtable;
import dados.*;

public class TabeladeSimbolos {
	
	public Hashtable<String,Simbolo> tabeladesimbolos;
	
	public TabeladeSimbolos(){
		
	}
	
	public void put(String s, Simbolo simbolo) {
		tabeladesimbolos.put(s, simbolo);		
	}
}
