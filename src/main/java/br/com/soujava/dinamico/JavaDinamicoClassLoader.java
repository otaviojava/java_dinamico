package br.com.soujava.dinamico;

import java.util.HashMap;
import java.util.Map;


/**
 * Class Loader da lib,
 * O objetivo de extender essa classe é para simplismente usar
 * a representação de objeto.
 */
public class JavaDinamicoClassLoader extends ClassLoader {


  /**
   * Representa o arquivo e o bytecode 
   * Guiado pela chave e o valor.
   * Sendo que a chave e o qualified e o valor e o bytecode
   * @see JavaDinamicoBean
   */  
  private Map<String, JavaDinamicoBean> classes = new HashMap<>();

  public JavaDinamicoClassLoader(ClassLoader parentClassLoader) {
    super(parentClassLoader);
    
  }
  
  public void addClass(JavaDinamicoBean compiledObj) {
    classes.put(compiledObj.getName(), compiledObj);
  }

  @Override
  public Class<?> findClass(String qualifiedClassName)
      throws ClassNotFoundException
  {
	JavaDinamicoBean bean = classes.get(qualifiedClassName);
	if (bean == null) {
		return super.findClass(qualifiedClassName);
	}
    byte[] bytes = bean.getBytes();
    return defineClass(qualifiedClassName, bytes, 0, bytes.length);
  }
}