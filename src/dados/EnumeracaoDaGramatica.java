package dados;

public class EnumeracaoDaGramatica {
	
	private int enumeracao;
	private String sentenca;
	
	public EnumeracaoDaGramatica(int enumeracao, String sentenca){
		this.enumeracao = enumeracao;
		this.sentenca = sentenca;
	}

	public int getEnumeracao() {
		return enumeracao;
	}

	public void setEnumeracao(int enumeracao) {
		this.enumeracao = enumeracao;
	}

	public String getSentenca() {
		return sentenca;
	}

	public void setSentenca(String sentenca) {
		this.sentenca = sentenca;
	}
	
}
