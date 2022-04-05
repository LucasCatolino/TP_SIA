package sia.grupo19;

import static org.junit.Assert.*;

import org.junit.Test;

import sia.grupo19.Genetic;
import sia.grupo19.Params.CrossType;
import sia.grupo19.Params.SelectorType;
import sia.grupo19.io.Solution;
import sia.grupo19.io.Writer;

public class TestFuncionGenetica {

	// Genetic genetic = new Genetic(10, 10, "S", 0.1, 0.1, "E");
	Params params = new Params(1000, 500, CrossType.MULTIPLE, 0.3, 0.2, SelectorType.ROULETTE, 0.001, 10, 8, 5, 2, 1,
			0.2, true);
	Genetic genetic = new Genetic(params);

	@Test
	public void test01() {
		Solution solution = genetic.runEvolution();
		System.out.println(solution.getOptimalIndividuo().getSerialized());

		Writer writer = new Writer(solution, "test01-output-");
		// fail();
	}

}
