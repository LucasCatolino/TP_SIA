package sia.grupo19;

import static org.junit.Assert.*;

import org.junit.Test;

import sia.grupo19.Genetic;

public class TestFuncionGenetica {

	Genetic genetic = new Genetic(10, 10, "S", 0.1, 0.1, "E");

	@Test
	public void test01() {
		Individuo bestIndividuo= genetic.runEvolution();
		System.out.println(bestIndividuo.getSerialized());
		// fail();
	}

}
