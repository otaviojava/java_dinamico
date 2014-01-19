package br.com.soujava.dinamico.calculo;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.soujava.dinamico.CodigoJava;
import br.com.soujava.dinamico.CodigoJavaUtils;
import br.com.soujava.dinamico.JavaDinamicoCompilador;


public class CalculoTest {
	
	private static final Number VALORA = 20;
	
	private static final Number VALORB = 10;
	
	private static JavaDinamicoCompilador<Calculo> compiler;
	
	private CodigoJavaUtils codigoJavaUtils = CodigoJavaUtils.INSTANCE;
	
	@Test
	public void somarTest() {
		realizarTest(CalculoUtil.SOMA);
	}
	
	@Test
	public void subtracaoTest() {
		realizarTest(CalculoUtil.SUBTRACAO);
	}
	
	@Test
	public void multiplicacaoTest() {
		realizarTest(CalculoUtil.MULTIPLICACAO);
	}
	
	@Test
	public void divisaoTest() {
		realizarTest(CalculoUtil.DIVISAO);
	}

	@Test
	public void classLoaderTest() {
		
	}
	private void realizarTest(CalculoUtil operacao) {
		CodigoJava codigoJava = codigoJavaUtils.getCodigo(operacao.getNomeClasse());
		Class<Calculo> calculoClazz = compiler.compile(null, codigoJava.getNomeClasse(), codigoJava.getFonte());
		Calculo calculo = getInstance(calculoClazz);
		Double resultado = calculo.calcular(VALORA, VALORB);
		Assert.assertEquals(operacao.getResultado(), resultado);
	}

	private Calculo getInstance(Class<Calculo> calculoClazz) {
		try {
			return calculoClazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return new NadaOperacao();
	}
	
	@BeforeClass
	public static void berforeClass() {
		compiler = new JavaDinamicoCompilador<>();
	}
	
}
