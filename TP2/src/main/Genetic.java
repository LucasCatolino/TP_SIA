package main;

import java.util.ArrayList;
import java.util.List;

public class Genetic {
	
	private static final int X_LENGTH= 11;
	private int generations;
	private String crossingMethod;
	private double mutationProb;
	private double deviation;
	private String selectionMethod;
	private List<Individuo> population;
	
	public Genetic(int P, int generations, String cross, double prob, double sigma, String selection) {
		this.generations= generations;
		this.crossingMethod= cross;
		this.mutationProb= prob;
		this.deviation= sigma;this.selectionMethod= selection;
		population= new  ArrayList<Individuo>();
		
		initializePopulation(P);
	}

	private void initializePopulation(int p) {
		for (int i = 0; i < p; i++) {
			double[] possibleX = new double[X_LENGTH]; 
			for (int j = 0; j < X_LENGTH; j++) {
				double element= Math.random() * X_LENGTH;
				possibleX[j]= element;
				//System.out.println(element);
			}
			population.add(new Individuo(possibleX));
		}
	}

}
