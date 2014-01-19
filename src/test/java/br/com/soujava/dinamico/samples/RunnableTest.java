package br.com.soujava.dinamico.samples;

import org.junit.Test;

import br.com.soujava.dinamico.CodigoJava;
import br.com.soujava.dinamico.CodigoJavaUtils;
import br.com.soujava.dinamico.JavaDinamicoCompilador;
import br.com.soujava.dinamico.JavaDinamicoException;
import junit.framework.Assert;

public class RunnableTest {

	
	@Test
	public void runTest() throws JavaDinamicoException, InstantiationException, IllegalAccessException{
		
		  JavaDinamicoCompilador<Runnable> compiler = new JavaDinamicoCompilador<Runnable>();   
		   CodigoJava codigoJava = CodigoJavaUtils.INSTANCE.getCodigo("RunnableImpl.java");  
		   Class<Runnable> clazz = compiler.compile(null, codigoJava.getNomeClasse(), codigoJava.getFonte());
		   System.out.println(clazz.getName());
		   Runnable r = clazz.newInstance();
		   r.run();
		Assert.assertNotNull(clazz);
	}
}
