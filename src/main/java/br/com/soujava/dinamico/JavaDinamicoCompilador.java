package br.com.soujava.dinamico;

import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
/**
 * Classe que realiza a compilação do objeto
 * @param <T>
 */
public class JavaDinamicoCompilador<T> {
/**
 * Responsável por compilar o código
 * Essa classe é da própria api do JSR 199
 */
  private JavaCompiler compiler;
  private JavaDinamicoManager javaDinamicoManager;
  private JavaDinamicoClassLoader classLoader;
  /**
   * Caso exista algum erro essa classe trará essas informações
   */
  private DiagnosticCollector<JavaFileObject> diagnostics;

  /**
   * método construtor, realiza a instancia do JavaCompiler, 
   * ClassLoader e diagnostics
   * @throws JavaDinamicoException
   */
  public JavaDinamicoCompilador() throws JavaDinamicoException {
    compiler = ToolProvider.getSystemJavaCompiler();
    if (compiler == null) { 
    	throw new JavaDinamicoException("Compilador não encontrado");
    }

    classLoader = new JavaDinamicoClassLoader(getClass().getClassLoader());
    diagnostics = new DiagnosticCollector<JavaFileObject>();

    StandardJavaFileManager standardFileManager = compiler
        .getStandardFileManager(diagnostics, null, null);
    javaDinamicoManager = new JavaDinamicoManager(standardFileManager, classLoader);
  }

  
  /**
   * método que compila o código e que retorna o bytecode
   * @param packageName - o pacote aonde o código se encontra
   * @param className - o nome da classe
   * @param javaSource - o código a ser compilado
   * @return
   * @throws JavaDinamicoException
   */
  @SuppressWarnings("unchecked")
  public synchronized Class<T> compile(String packageName, String className,
      String javaSource) throws JavaDinamicoException
  {
    try {
      String qualifiedClassName = JavaDinamicoUtils.INSTANCE.getQualifiedClassName(
          packageName, className);
      JavaDinamicoBean sourceObj = new JavaDinamicoBean(className, javaSource);
      JavaDinamicoBean compiledObj = new JavaDinamicoBean(qualifiedClassName);
      javaDinamicoManager.setSources(sourceObj, compiledObj);

      CompilationTask task = compiler.getTask(null, javaDinamicoManager, diagnostics,
          null, null, Arrays.asList(sourceObj));
      boolean result = task.call();

      if (!result) { 
    	  throw new JavaDinamicoException("A compilação falhou", diagnostics); 
    }

      Class<T> newClass = (Class<T>) classLoader.loadClass(qualifiedClassName);
      return newClass;

    }
    catch (Exception exception) {
      throw new JavaDinamicoException(exception, diagnostics);
    }
  }
}
