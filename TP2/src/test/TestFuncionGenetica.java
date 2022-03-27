package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Genetic;

public class TestFuncionGenetica {

	double[] xi= {4,4793, -4,0765, -4,0765};
	Genetic genetic= new Genetic(xi ,10, 10, "S", 0.1, 0.1, "E");
	
	@Test
	public void test01() {
		//fail();
	}

}
