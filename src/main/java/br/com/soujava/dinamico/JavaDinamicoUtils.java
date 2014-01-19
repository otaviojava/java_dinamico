package br.com.soujava.dinamico;

import java.net.URI;
import java.net.URISyntaxException;

import javax.tools.JavaFileObject.Kind;

import org.apache.commons.lang.StringUtils;
/**
 * Classe com método utilitarios na compilação de um código
 */
public enum  JavaDinamicoUtils {

	INSTANCE;
/**
 * cria um objeto URI a partir de uma String	
 * @param str - caminho da classe
 * @return
 */
  public  URI createURI(String str) {
    try {
      return new URI(str);
    }
    catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * retorna o nome completo da classe
   * @param packageName - pacote java
   * @param className - nome da Classe
   * @return nome completo da Classe
   */
  public  String getQualifiedClassName(String packageName,
      String className)
  {
    if (StringUtils.isEmpty(packageName)) {
      return className;
    }
    else {
      return packageName + "." + className;
    }
  }

  public  String getClassNameWithExt(String className) {
    return className + Kind.SOURCE.extension;
  }
  
}