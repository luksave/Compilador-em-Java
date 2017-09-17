package dados;

public class Simbolo {
	
	private String lexema, token, tipo;
	
	public Simbolo( String lexema, String token, String tipo) {
		this.lexema = lexema;
		this.token = token;
		this.tipo = tipo; 
	}
	
	public String getLexema(){
		return this.lexema;
	}
	
	public String getToken(){
		return this.token;		
	}
	
	public String getTipo(){
		return this.tipo;	
	}
	
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
