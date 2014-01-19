package br.com.soujava.dinamico.calculo;

/**
 * Classe para auxilicar nos testes do calculo 
 */
public enum CalculoUtil{
	
	SOMA(30d,"Soma.java"), SUBTRACAO(10d, "Subtracao.java"),
	MULTIPLICACAO(200d, "Multiplicacao.java"),
	DIVISAO(2d, "Divisao.java");
	
	private Double resultado;
	
	private String nomeClasse;
	
	CalculoUtil(Double resultado,String nomeClasse){
		this.resultado = resultado;
		this.nomeClasse = nomeClasse;
	}
	public Double getResultado() {
		return resultado;
	}
	public String getNomeClasse() {
		return nomeClasse;
	}
	
}
