package br.com.soujava.dinamico;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.tools.SimpleJavaFileObject;
/**
 * Estrutura de dados que contem os códigos-fonte e as classes compiladas
 */
public class JavaDinamicoBean extends SimpleJavaFileObject {
/**
 * Código Fonte
 */
  private String source;
  /**
   * Representação da classe compilada em stream
   */
  private ByteArrayOutputStream byteCode = new ByteArrayOutputStream();

  /**
   * Contrutor
   * Representação da Classe Compilada e o byteCode
   * @param baseName - nome da Classe
   * @param source -código fonte
   */
  public JavaDinamicoBean(String baseName, String source) {
    super(JavaDinamicoUtils.INSTANCE.createURI(JavaDinamicoUtils.INSTANCE.getClassNameWithExt(baseName)),
        Kind.SOURCE);
    this.source = source;
  }

  /**
   * Contrutor
   * @param namecompleto para o bytecode
   */
		 
  public JavaDinamicoBean(String name) {
    super(JavaDinamicoUtils.INSTANCE.createURI(name), Kind.CLASS);
  }

  @Override
  public String getCharContent(boolean ignoreEncodingErrors) {
    return source;
  }

  @Override
  public OutputStream openOutputStream() {
    return byteCode;
  }

  public byte[] getBytes() {
    return byteCode.toByteArray();
  }
}