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

public class TestsInforme {

	//BPA, BPP, BPPV
	//HLM, HLE, HGM, HGE, A*M, A*E, HLI, HGI, A*I
	
	//UNINFORMED
	
	String tablero= "265013478";
/*
	@Test
	public void test01BPA() {
	    Config myConfigBPA = new Config(tablero, StrategyTypes.UNINFORMED, SearchMethods.BPA, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigBPA);
	    
        long startTime = System.currentTimeMillis();
	    Solution outcome = mySolver.unInformedResolver(myConfigBPA);
        long stopTime = System.currentTimeMillis();

        System.out.println("BPA: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                        + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                        + ", depth: " + outcome.getSolutionNode().getDepth());
        
		assertEquals(11, outcome.getSolutionNode().getDepth());
	}
	
	@Test
	public void test02BPP() {
		Config myConfigBPP = new Config(tablero, StrategyTypes.UNINFORMED, SearchMethods.BPP, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigBPP);
	    
        long startTime = System.currentTimeMillis();
	    Solution outcome = mySolver.unInformedResolver(myConfigBPP);
	    long stopTime = System.currentTimeMillis();
	    
        System.out.println("BPP: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(92103, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test03BPPV() {
		Config myConfigBPPV = new Config(tablero, StrategyTypes.UNINFORMED, SearchMethods.BPPV, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigBPPV);
	    
        long startTime = System.currentTimeMillis();
	    Solution outcome = mySolver.unInformedResolver(myConfigBPPV);
	    long stopTime = System.currentTimeMillis();
	    
        System.out.println("BPPV: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());
    }
	
	//INFORMED
	@Test
	public void test04HLM() {
		Config myConfigHLM = new Config(tablero, StrategyTypes.INFORMED, SearchMethods.HEURLOCAL, Heuristics.MANHATTAN, 15);
	    Solver mySolver = new Solver(myConfigHLM);
	    
	    long startTime = System.currentTimeMillis();
	    Solution outcome = mySolver.localHeuristicResolver(myConfigHLM);
	    long stopTime = System.currentTimeMillis();
	    
        System.out.println("HLM: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test05HLE() {
		Config myConfigHLE = new Config(tablero, StrategyTypes.INFORMED, SearchMethods.HEURLOCAL, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigHLE);
	    
	    long startTime = System.currentTimeMillis();
	    Solution outcome = mySolver.localHeuristicResolver(myConfigHLE);
	    long stopTime = System.currentTimeMillis();

	    
        System.out.println("HLE: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(199, outcome.getSolutionNode().getDepth());
	}

	@Test
	public void test06HGM() {
		Config myConfigHGM = new Config(tablero, StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.MANHATTAN, 15);
	    Solver mySolver = new Solver(myConfigHGM);
	    
	    long startTime = System.currentTimeMillis();
        Solution outcome = mySolver.informedResolver(myConfigHGM);
        long stopTime = System.currentTimeMillis();

        
        System.out.println("HGM: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(33, outcome.getSolutionNode().getDepth());
	}
	
	@Test
	public void test07HGE() {
		Config myConfigHGE = new Config(tablero, StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigHGE);
	    
	    long startTime = System.currentTimeMillis();
        Solution outcome = mySolver.informedResolver(myConfigHGE);
        long stopTime = System.currentTimeMillis();

		
        System.out.println("HGE: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(47, outcome.getSolutionNode().getDepth());
	}
	
	@Test
	public void test08ASM() {		
		Config myConfigASM = new Config(tablero, StrategyTypes.INFORMED, SearchMethods.ASTAR, Heuristics.MANHATTAN, 15);
	    Solver mySolver = new Solver(myConfigASM);
	    
	    long startTime = System.currentTimeMillis();
        Solution outcome = mySolver.informedResolver(myConfigASM);
        long stopTime = System.currentTimeMillis();


        System.out.println("ASM: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());
	}
	
	@Test
	public void test09ASE() {
		Config myConfigASE = new Config(tablero, StrategyTypes.INFORMED, SearchMethods.ASTAR, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigASE);
	    
	    long startTime = System.currentTimeMillis();
        Solution outcome = mySolver.informedResolver(myConfigASE);
        long stopTime = System.currentTimeMillis();


        System.out.println("ASE: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());
	}
	
	@Test
	public void test10HLI() {
		Config myConfigHLI = new Config(tablero, StrategyTypes.INFORMED, SearchMethods.HEURLOCAL, Heuristics.INVALID, 15);
	    Solver mySolver = new Solver(myConfigHLI);
	    
	    long startTime = System.currentTimeMillis();
        Solution outcome = mySolver.informedResolver(myConfigHLI);
        long stopTime = System.currentTimeMillis();


        System.out.println("HLI: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(45, outcome.getSolutionNode().getDepth());
	}
	
	@Test
	public void test11HGI() {
		Config myConfigHGI = new Config(tablero, StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.INVALID, 15);
	    Solver mySolver = new Solver(myConfigHGI);
	    
	    long startTime = System.currentTimeMillis();
        Solution outcome = mySolver.informedResolver(myConfigHGI);
        long stopTime = System.currentTimeMillis();


        System.out.println("HGI: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(45, outcome.getSolutionNode().getDepth());
	}
	
	@Test
	public void test12ASI() {
		Config myConfigASI = new Config(tablero, StrategyTypes.INFORMED, SearchMethods.ASTAR, Heuristics.INVALID, 15);
	    Solver mySolver = new Solver(myConfigASI);
	    
	    long startTime = System.currentTimeMillis();
        Solution outcome = mySolver.informedResolver(myConfigASI);
        long stopTime = System.currentTimeMillis();


        System.out.println("ASI: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());
	}
*/
	@Test
	public void test13BPPVLimit15() {
		Config myConfigBPPV = new Config(tablero, StrategyTypes.UNINFORMED, SearchMethods.BPPV, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigBPPV);
	    
        long startTime = System.currentTimeMillis();
	    Solution outcome = mySolver.unInformedResolver(myConfigBPPV);
	    long stopTime = System.currentTimeMillis();
	    
        System.out.println("BPPV limit 15: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test14BPPVLimit20() {
		Config myConfigBPPV = new Config(tablero, StrategyTypes.UNINFORMED, SearchMethods.BPPV, Heuristics.EUCLIDEAN, 20);
	    Solver mySolver = new Solver(myConfigBPPV);
	    
        long startTime = System.currentTimeMillis();
	    Solution outcome = mySolver.unInformedResolver(myConfigBPPV);
	    long stopTime = System.currentTimeMillis();
	    
        System.out.println("BPPV limit 20: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(19, outcome.getSolutionNode().getDepth());
    }
	
	@Test
	public void test15BPPVLimit50() {
		Config myConfigBPPV = new Config(tablero, StrategyTypes.UNINFORMED, SearchMethods.BPPV, Heuristics.EUCLIDEAN, 50);
	    Solver mySolver = new Solver(myConfigBPPV);
	    
        long startTime = System.currentTimeMillis();
	    Solution outcome = mySolver.unInformedResolver(myConfigBPPV);
	    long stopTime = System.currentTimeMillis();
	    
        System.out.println("BPPV limit 50: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(49, outcome.getSolutionNode().getDepth());
    }
	
	
}
