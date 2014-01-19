package br.com.soujava.dinamico.calculo;

import org.apache.commons.lang.math.NumberUtils;

public class NadaOperacao implements Calculo{

	@Override
	public Double calcular(Number valorA, Number valorB) {
		return NumberUtils.DOUBLE_ZERO;
	}

}
