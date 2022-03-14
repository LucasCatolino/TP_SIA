package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import main.Config;
import main.Solver;
import main.Config.Heuristics;
import main.Config.SearchMethods;
import main.Config.StrategyTypes;
import main.Solver.Solution;

public class TestsSolver {

    Config myConfigBPA = new Config("123456708", StrategyTypes.UNINFORMED, SearchMethods.BPA, Heuristics.EUCLIDEAN, 15);
    Config myConfigBPP = new Config("123456708", StrategyTypes.UNINFORMED, SearchMethods.BPP, Heuristics.EUCLIDEAN, 15);
    Config myConfigBPPV = new Config("123456708", StrategyTypes.UNINFORMED, SearchMethods.BPPV, Heuristics.EUCLIDEAN,
            1);

    Config myConfigOptimal4BPA = new Config("013425786", StrategyTypes.UNINFORMED, SearchMethods.BPA,
            Heuristics.EUCLIDEAN,
            15);
    Config myConfigOptimal4BPP = new Config("013425786", StrategyTypes.UNINFORMED, SearchMethods.BPP,
            Heuristics.EUCLIDEAN,
            15);
    Config myConfigOptimal4BPPV = new Config("013425786", StrategyTypes.UNINFORMED, SearchMethods.BPPV,
            Heuristics.EUCLIDEAN,
            1);

    Config hard31BPA = new Config("867254301", StrategyTypes.UNINFORMED, SearchMethods.BPA, Heuristics.EUCLIDEAN, 1);
    Config hard31BPP = new Config("867254301", StrategyTypes.UNINFORMED, SearchMethods.BPP, Heuristics.EUCLIDEAN, 1);

    Config hard31BPPVlimit1 = new Config("867254301", StrategyTypes.UNINFORMED, SearchMethods.BPPV,
            Heuristics.EUCLIDEAN, 1);
    Config hard31BPPVlimit5 = new Config("867254301", StrategyTypes.UNINFORMED, SearchMethods.BPPV,
            Heuristics.EUCLIDEAN, 5);
    Config hard31BPPVlimit10 = new Config("867254301", StrategyTypes.UNINFORMED, SearchMethods.BPPV,
            Heuristics.EUCLIDEAN, 10);
    Config hard31BPPVlimit15 = new Config("867254301", StrategyTypes.UNINFORMED, SearchMethods.BPPV,
            Heuristics.EUCLIDEAN, 15);
    Config hard31BPPVlimit20 = new Config("867254301", StrategyTypes.UNINFORMED, SearchMethods.BPPV,
            Heuristics.EUCLIDEAN, 20);
    Config hard31BPPVlimit25 = new Config("867254301", StrategyTypes.UNINFORMED, SearchMethods.BPPV,
            Heuristics.EUCLIDEAN, 25);
    Config hard31BPPVlimit31 = new Config("867254301", StrategyTypes.UNINFORMED, SearchMethods.BPPV,
            Heuristics.EUCLIDEAN, 31);

    Solver mySolver = new Solver(myConfigBPA);

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test01RunsSimpleBPA() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.unInformedResolver(myConfigBPA);

        long stopTime = System.currentTimeMillis();
        System.out.println("test BPA: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(1, outcome.getSolutionNode().getDepth());
    }

    @Test
    public void test02RunsSimpleBPP() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.unInformedResolver(myConfigBPP);

        long stopTime = System.currentTimeMillis();
        System.out.println("test BPP: " + outcome.getReadableTime(stopTime - startTime));

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

    @Test
    public void test05RunsSimpleBPPV() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.bppvResolver(myConfigBPPV);

        long stopTime = System.currentTimeMillis();
        System.out.println("test05: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(true, outcome.getSolutionNode().goalReached());
    }

    @Test
    public void test06RunsOptimal4BPPV() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.bppvResolver(myConfigOptimal4BPPV);

        long stopTime = System.currentTimeMillis();
        System.out.println("test06: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(4, outcome.getSolutionNode().getDepth());
    }

    @Test
    public void test07RunsHard31BPA() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.unInformedResolver(hard31BPA);

        long stopTime = System.currentTimeMillis();
        System.out.println("test07: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(31, outcome.getSolutionNode().getDepth());
    }

    @Test
    public void test08RunsHard31BPP() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.unInformedResolver(hard31BPP);

        long stopTime = System.currentTimeMillis();
        System.out.println("test08: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(31, outcome.getSolutionNode().getDepth());
    }

    @Test
    public void test09RunsHard31BPPVlimit1() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.bppvResolver(hard31BPPVlimit1);

        long stopTime = System.currentTimeMillis();
        System.out.println("test09: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(null, outcome.getSolutionNode());
    }

    @Test
    public void test10RunsHard31BPPVlimit5() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.bppvResolver(hard31BPPVlimit5);

        long stopTime = System.currentTimeMillis();
        System.out.println("test10: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(null, outcome.getSolutionNode());
    }

    @Test
    public void test11RunsHard31BPPVlimit10() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.bppvResolver(hard31BPPVlimit10);

        long stopTime = System.currentTimeMillis();
        System.out.println("test11: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(null, outcome.getSolutionNode());
    }

    @Test
    public void test12RunsHard31BPPVlimit15() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.bppvResolver(hard31BPPVlimit15);

        long stopTime = System.currentTimeMillis();
        System.out.println("test12: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(null, outcome.getSolutionNode());
    }

    @Test
    public void test13RunsHard31BPPVlimit20() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.bppvResolver(hard31BPPVlimit20);

        long stopTime = System.currentTimeMillis();
        System.out.println("test13: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(null, outcome.getSolutionNode());
    }

    @Test
    public void test14RunsHard31BPPVlimit25() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.bppvResolver(hard31BPPVlimit25);

        long stopTime = System.currentTimeMillis();
        System.out.println("test14: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(null, outcome.getSolutionNode());
    }

    @Test
    public void test15RunsHard31BPPVlimit31() {
        long startTime = System.currentTimeMillis();

        Solution outcome = mySolver.bppvResolver(hard31BPPVlimit31);

        long stopTime = System.currentTimeMillis();
        System.out.println("test15: " + outcome.getReadableTime(stopTime - startTime));

        assertEquals(null, outcome.getSolutionNode());
    }

}
