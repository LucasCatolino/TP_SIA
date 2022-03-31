package sia.grupo19;

import static org.junit.Assert.*;

import org.junit.Test;

import sia.grupo19.Genetic;
import sia.grupo19.Params.CrossType;
import sia.grupo19.Params.SelectorType;

public class TestFuncionGenetica {

	// Genetic genetic = new Genetic(10, 10, "S", 0.1, 0.1, "E");
	Params params = new Params(2000, 5000, CrossType.UNIFORM, 0.1, 0.1, SelectorType.ELITE, 0.001, 10, 8);
	Genetic genetic = new Genetic(params);

	@Test
	public void test01() {
		Individuo bestIndividuo = genetic.runEvolution();
		System.out.println(bestIndividuo.getSerialized());
		// fail();
	}

}
