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

public class TestsSolver {

    Config myConfigBPA = new Config("123456708", StrategyTypes.UNINFORMED, SearchMethods.BPA, Heuristics.EUCLIDEAN, 15);
    Config myConfigBPP = new Config("123456708", StrategyTypes.UNINFORMED, SearchMethods.BPP, Heuristics.EUCLIDEAN, 15);
    Config myConfigOptimal4 = new Config("013425786", StrategyTypes.UNINFORMED, SearchMethods.BPA, Heuristics.EUCLIDEAN,
            15);

    Solver mySolver = new Solver(myConfigBPA);

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test01RunsSimpleBPA() {
        Tree.Node outcome = mySolver.unInformedResolver(myConfigBPA);
        assertEquals(1, outcome.getDepth());
    }

    @Test
    public void test02RunsSimpleBPP() {
        Tree.Node outcome = mySolver.unInformedResolver(myConfigBPA);
        assertEquals(1, outcome.getDepth());
    }

    @Test
    public void test03RunsOptimal4() {
        Tree.Node outcome = mySolver.unInformedResolver(myConfigOptimal4);
        assertEquals(4, outcome.getDepth());
    }

}
