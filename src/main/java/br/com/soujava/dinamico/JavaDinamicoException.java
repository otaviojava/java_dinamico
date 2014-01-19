package br.com.soujava.dinamico;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;


/**
 * Classe Exceção para a compilacao dinamica.
 * Seu objetivo é apenas reportar os erros de compilação em tempo real
 */
public class JavaDinamicoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * O coletor de informações da compilação
	 */
	private DiagnosticCollector<JavaFileObject> collector;

	public JavaDinamicoException(String message) {
		super(message);
	}

	public JavaDinamicoException(String message, DiagnosticCollector<JavaFileObject> collector) {
		super(message);
		this.collector = collector;
	}
	
	public JavaDinamicoException(Throwable e, DiagnosticCollector<JavaFileObject> collector) {
		super(e);
		this.collector = collector;
	}

	public String getCompilationError() {
		StringBuilder sb = new StringBuilder();
		for (Diagnostic<? extends JavaFileObject> diagnostic : collector.getDiagnostics()) {
			sb.append(diagnostic.getMessage(null));
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
	  return getCompilationError();
	}
}
