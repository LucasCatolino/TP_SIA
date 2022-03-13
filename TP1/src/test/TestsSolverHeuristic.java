package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import main.Config;
import main.Solver;
import main.Config.Heuristics;
import main.Config.SearchMethods;
import main.Config.StrategyTypes;
import main.Solver.Solution;

public class TestsSolverHeuristic {
	
	Config myConfigHGEuclidean = new Config("123456708", StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.EUCLIDEAN);
	Config myConfigHGManhattan = new Config("123456708", StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.MANHATTAN);
	Config myConfigHGManhattanRand = new Config("013425786", StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.MANHATTAN);
	Config myConfigHGEuclideanRand = new Config("013425786", StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.EUCLIDEAN);

    Solver mySolverHGSolverEuclidean = new Solver(myConfigHGEuclidean);
    Solver mySolverHGSolverManhattan = new Solver(myConfigHGManhattan);
    Solver mySolverHGSolverManhattanRand = new Solver(myConfigHGManhattanRand);
    Solver mySolverHGSolverEuclideanRand = new Solver(myConfigHGEuclideanRand);



	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01Euclidean() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverHGSolverEuclidean.informedResolver(myConfigHGEuclidean);

        long stopTime = System.currentTimeMillis();
        System.out.println("test HG Euclidean: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(1, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test02Euclidean() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverHGSolverManhattan.informedResolver(myConfigHGManhattan);

        long stopTime = System.currentTimeMillis();
        System.out.println("test HG Manhattan: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(1, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test03ManhattanRand() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverHGSolverManhattanRand.informedResolver(myConfigHGManhattanRand);

        long stopTime = System.currentTimeMillis();
        System.out.println("test HG Manhattan Rand: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(4, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test04EuclideanRand() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverHGSolverEuclideanRand.informedResolver(myConfigHGEuclideanRand);

        long stopTime = System.currentTimeMillis();
        System.out.println("test HG Euclidean Rand: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(4, outcome.getSolutionNode().getDepth());
    }


}
