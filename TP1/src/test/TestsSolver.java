package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import main.Config;
import main.Solver;
import main.Tree;
import main.Config.Heuristics;
import main.Config.SearchMethods;
import main.Config.StrategyTypes;
import main.Solver.Solution;

public class TestsSolver {

    Config myConfigBPA = new Config("123456708", StrategyTypes.UNINFORMED, SearchMethods.BPA, Heuristics.EUCLIDEAN, 15);
    Config myConfigBPP = new Config("123456708", StrategyTypes.UNINFORMED, SearchMethods.BPP, Heuristics.EUCLIDEAN, 15);
    Config myConfigOptimal4BPA = new Config("013425786", StrategyTypes.UNINFORMED, SearchMethods.BPA,
            Heuristics.EUCLIDEAN,
            15);
    Config myConfigOptimal4BPP = new Config("013425786", StrategyTypes.UNINFORMED, SearchMethods.BPP,
            Heuristics.EUCLIDEAN,
            15);

    Solver mySolver = new Solver(myConfigBPA);

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test01RunsSimpleBPA() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.unInformedResolver(myConfigBPA);

        long stopTime = System.currentTimeMillis();
        System.out.println("test01: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(1, outcome.getSolutionNode().getDepth());
    }

    @Test
    public void test02RunsSimpleBPP() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.unInformedResolver(myConfigBPP);

        long stopTime = System.currentTimeMillis();
        System.out.println("test02: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(true, outcome.getSolutionNode().goalReached());
    }

    @Test
    public void test03RunsOptimal4BPA() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.unInformedResolver(myConfigOptimal4BPA);

        long stopTime = System.currentTimeMillis();
        System.out.println("test03: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(4, outcome.getSolutionNode().getDepth());
    }

    @Test
    public void test04RunsOptimal4BPP() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.unInformedResolver(myConfigOptimal4BPP);

        long stopTime = System.currentTimeMillis();
        System.out.println("test04: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(50796, outcome.getSolutionNode().getDepth());
    }

}
