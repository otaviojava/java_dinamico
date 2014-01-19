import br.com.soujava.dinamico.calculo.Calculo;

public class Subtracao implements Calculo{

	@Override
	public Double calcular(Number valorA, Number valorB) {
		return valorA.doubleValue() - valorB.doubleValue();
	}

}
