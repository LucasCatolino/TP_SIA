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
	//HLM, HLE, HGM, HGE, A*M, A*E
	
	//UNINFORMED
	@Test
	public void test01BPA() {
	    Config myConfigBPA = new Config("265013478", StrategyTypes.UNINFORMED, SearchMethods.BPA, Heuristics.EUCLIDEAN, 15);
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
		Config myConfigBPP = new Config("265013478", StrategyTypes.UNINFORMED, SearchMethods.BPP, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigBPP);
	    
        long startTime = System.currentTimeMillis();
	    Solution outcome = mySolver.unInformedResolver(myConfigBPP);
	    long stopTime = System.currentTimeMillis();
	    
        System.out.println("BPP: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());	}
	
	@Test
	public void test03BPPV() {
		Config myConfigBPPV = new Config("265013478", StrategyTypes.UNINFORMED, SearchMethods.BPPV, Heuristics.EUCLIDEAN, 15);
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
		Config myConfigHLM = new Config("265013478", StrategyTypes.INFORMED, SearchMethods.HEURLOCAL, Heuristics.MANHATTAN, 15);
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
		Config myConfigHLE = new Config("265013478", StrategyTypes.INFORMED, SearchMethods.HEURLOCAL, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigHLE);
	    
	    long startTime = System.currentTimeMillis();
	    Solution outcome = mySolver.localHeuristicResolver(myConfigHLE);
	    long stopTime = System.currentTimeMillis();

	    
        System.out.println("HLE: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());
	}

	@Test
	public void test06HGM() {
		Config myConfigHGM = new Config("265013478", StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.MANHATTAN, 15);
	    Solver mySolver = new Solver(myConfigHGM);
	    
	    long startTime = System.currentTimeMillis();
        Solution outcome = mySolver.informedResolver(myConfigHGM);
        long stopTime = System.currentTimeMillis();

        
        System.out.println("HGM: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());
	}
	
	@Test
	public void test07HGE() {
		Config myConfigHGE = new Config("265013478", StrategyTypes.INFORMED, SearchMethods.HEURGLOBAL, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigHGE);
	    
	    long startTime = System.currentTimeMillis();
        Solution outcome = mySolver.informedResolver(myConfigHGE);
        long stopTime = System.currentTimeMillis();

		
        System.out.println("HGE: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());
	}
	
	@Test
	public void test08ASM() {		
		Config myConfigASM = new Config("265013478", StrategyTypes.INFORMED, SearchMethods.ASTAR, Heuristics.MANHATTAN, 15);
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
		Config myConfigASE = new Config("265013478", StrategyTypes.INFORMED, SearchMethods.ASTAR, Heuristics.EUCLIDEAN, 15);
	    Solver mySolver = new Solver(myConfigASE);
	    
	    long startTime = System.currentTimeMillis();
        Solution outcome = mySolver.informedResolver(myConfigASE);
        long stopTime = System.currentTimeMillis();


        System.out.println("ASE: " + outcome.getReadableTime(stopTime - startTime) + ", millis: " + (stopTime - startTime)
                + ", size_F: " + outcome.getSizeFrontera() + ", size_Ex: " + outcome.getSizeExpandidos()
                + ", depth: " + outcome.getSolutionNode().getDepth());

        assertEquals(11, outcome.getSolutionNode().getDepth());
	}
}
