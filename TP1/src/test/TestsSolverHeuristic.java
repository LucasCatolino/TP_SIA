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
	Config myConfigAStarEuclidean = new Config("123456708", StrategyTypes.INFORMED, SearchMethods.ASTAR, Heuristics.EUCLIDEAN);
	Config myConfigAStarManhattan = new Config("123456708", StrategyTypes.INFORMED, SearchMethods.ASTAR, Heuristics.MANHATTAN);
	Config myConfigHGManhattanRand = new Config("013425786", StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.MANHATTAN);
	Config myConfigHGEuclideanRand = new Config("013425786", StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.EUCLIDEAN);
	Config myConfigAStarManhattanRand = new Config("013425786", StrategyTypes.INFORMED, SearchMethods.ASTAR, Heuristics.MANHATTAN);
	Config myConfigAStarEuclideanRand = new Config("013425786", StrategyTypes.INFORMED, SearchMethods.ASTAR, Heuristics.EUCLIDEAN);

    Solver mySolverHGSolverEuclidean = new Solver(myConfigHGEuclidean);
    Solver mySolverHGSolverManhattan = new Solver(myConfigHGManhattan);
    Solver mySolverAStarEuclidean = new Solver(myConfigAStarEuclidean);
    Solver mySolverAStarManhattan = new Solver(myConfigAStarManhattan);
    Solver mySolverHGSolverManhattanRand = new Solver(myConfigHGManhattanRand);
    Solver mySolverHGSolverEuclideanRand = new Solver(myConfigHGEuclideanRand);
    Solver mySolverAStarSolverManhattanRand = new Solver(myConfigAStarManhattanRand);
    Solver mySolverAStarSolverEuclideanRand = new Solver(myConfigAStarEuclideanRand);

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test01HGEuclidean() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverHGSolverEuclidean.informedResolver(myConfigHGEuclidean);

        long stopTime = System.currentTimeMillis();
        System.out.println("test HG Euclidean: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(1, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test02HGManhattan() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverHGSolverManhattan.informedResolver(myConfigHGManhattan);

        long stopTime = System.currentTimeMillis();
        System.out.println("test HG Manhattan: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(1, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test03AStarEuclidean() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverAStarEuclidean.informedResolver(myConfigAStarEuclidean);

        long stopTime = System.currentTimeMillis();
        System.out.println("test A* Euclidean: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(1, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test04AStarManhattan() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverAStarManhattan.informedResolver(myConfigAStarManhattan);

        long stopTime = System.currentTimeMillis();
        System.out.println("test A* Manhattan: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(1, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test05ManhattanRand() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverHGSolverManhattanRand.informedResolver(myConfigHGManhattanRand);

        long stopTime = System.currentTimeMillis();
        System.out.println("test HG Manhattan Rand: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(4, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test06EuclideanRand() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverHGSolverEuclideanRand.informedResolver(myConfigHGEuclideanRand);

        long stopTime = System.currentTimeMillis();
        System.out.println("test HG Euclidean Rand: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(4, outcome.getSolutionNode().getDepth());
    }

	@Test
	public void test07AStarManhattanRand() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverAStarSolverManhattanRand.informedResolver(myConfigAStarManhattanRand);

        long stopTime = System.currentTimeMillis();
        System.out.println("test A* Manhattan Rand: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(4, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test08AStarEuclideanRand() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverAStarSolverEuclideanRand.informedResolver(myConfigAStarEuclideanRand);

        long stopTime = System.currentTimeMillis();
        System.out.println("test A* Euclidean Rand: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(4, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test09HeuristicaLocalEuclidean() {
		Config myConfigHeuristicaLocalEuclidean = new Config("528417036", StrategyTypes.INFORMED, SearchMethods.HEURLOCAL, Heuristics.EUCLIDEAN);
	    Solver mySolverHeuristicaLocalEuclidean = new Solver(myConfigHeuristicaLocalEuclidean);

        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverHeuristicaLocalEuclidean.localHeuristicResolver(myConfigHeuristicaLocalEuclidean);

        long stopTime = System.currentTimeMillis();
        System.out.println("test Heuristica Local Euclidean: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(46, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test10HeuristicaLocalManhattan() {
		Config myConfigHeuristicaLocalManhattan = new Config("528417036", StrategyTypes.INFORMED, SearchMethods.HEURLOCAL, Heuristics.MANHATTAN);
	    Solver mySolverHeuristicaLocalManhattan = new Solver(myConfigHeuristicaLocalManhattan);

        long startTime = System.currentTimeMillis();

        Solution outcome = mySolverHeuristicaLocalManhattan.localHeuristicResolver(myConfigHeuristicaLocalManhattan);

        long stopTime = System.currentTimeMillis();
        System.out.println("test Heuristica Local Manhattan: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(184, outcome.getSolutionNode().getDepth());
    }

	
	@Test
	public void testXX() {
		
		Config XX = new Config("528417036", StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.MANHATTAN);
	    Solver YY = new Solver(XX);

        long startTime = System.currentTimeMillis();

        Solution outcome = YY.informedResolver(XX);

        long stopTime = System.currentTimeMillis();
        System.out.println("test XX: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(44, outcome.getSolutionNode().getDepth());
    }
}
