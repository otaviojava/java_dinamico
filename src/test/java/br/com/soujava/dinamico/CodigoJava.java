package br.com.soujava.dinamico;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Essa estrutura de dados representa o codigo nao compilado
 * oriundo de uma base de informacao.
 */
public class CodigoJava implements Serializable{

	private static final long serialVersionUID = 3305045467314963410L;
	
	/**
	 * codigo fonte vazio
	 */
	public static final CodigoJava VAZIO = new CodigoJava(StringUtils.EMPTY, StringUtils.EMPTY);
	/**
	 * contem  informacao do nome da classe.
	 * Ex: RunnableImpl
	 */
	private String nomeClasse;
	
	/**
	 * Codigo fontem em questao.
	 */
	private String fonte;

	public String getNomeClasse() {
		return nomeClasse;
	}

	public String getFonte() {
		return fonte;
	}

	public CodigoJava(String nomeClasse, String codigo) {
		this.nomeClasse = nomeClasse;
		this.fonte = codigo;
	}

	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nomeClasse).append(fonte).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof CodigoJava) {
			CodigoJava other = CodigoJava.class.cast(obj);
			return new EqualsBuilder().append(fonte, other.fonte).append(nomeClasse, other.nomeClasse).isEquals();
		}
		return false;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	
}
