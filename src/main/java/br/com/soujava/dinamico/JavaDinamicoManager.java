package br.com.soujava.dinamico;

import java.io.IOException;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
/**
 * Classe que tem a função de gerenciar as classes compiladas e o código fonte
 * representação da classe e do código @see AppJavaFileObject
 */
public class JavaDinamicoManager extends ForwardingJavaFileManager<JavaFileManager> {
  private JavaDinamicoClassLoader classLoader;
  
  /**
   * codigo não compilado
   */
  private JavaDinamicoBean codigoFonte;
  /**
   * código fonte compilado
   */
  private JavaDinamicoBean arquivoCompilado;
  
  public JavaDinamicoManager(JavaFileManager fileManager, JavaDinamicoClassLoader classLoader)
  {
    super(fileManager);
    this.classLoader = classLoader;
  }

  public void setSources(JavaDinamicoBean sourceObject, JavaDinamicoBean compiledObject) {
    this.codigoFonte = sourceObject;
    this.arquivoCompilado = compiledObject;
    this.classLoader.addClass(compiledObject);
  }

  @Override
  public FileObject getFileForInput(Location location, String packageName,
      String relativeName) throws IOException
  {
    return codigoFonte;
  }

  @Override
  public JavaFileObject getJavaFileForOutput(Location location,
      String qualifiedName, Kind kind, FileObject outputFile)
      throws IOException
  {
    return arquivoCompilado;
  }

  @Override
  public ClassLoader getClassLoader(Location location) {
    return classLoader;
  }
}