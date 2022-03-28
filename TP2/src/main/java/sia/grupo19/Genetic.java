package sia.grupo19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Genetic {

	private static final int X_LENGTH = 11;

	// parameters
	private int generations;
	private String crossingMethod;
	private double mutationProb;
	private double deviation;
	private String selectionMethod;
	private int P;

	private int generationCount = 0;
	private List<Individuo> population;
	private List<Individuo> lastPopulation = null;

	public Genetic(int P, int generations, String cross, double prob, double sigma, String selection) {
		this.P = P;
		this.generations = generations;
		this.crossingMethod = cross;
		this.mutationProb = prob;
		this.deviation = sigma;
		this.selectionMethod = selection;
		population = new ArrayList<Individuo>();

		initializePopulation(P);
	}

	private void calculateFitness() {
		/*
		 * for (Individuo ind : population) {
		 * ind.getFitness();
		 * }
		 */
		// ordenar la poblacion por fitness
	}

	private void initializePopulation(int p) {
		for (int i = 0; i < p; i++) {
			double[] possibleX = new double[X_LENGTH];
			for (int j = 0; j < X_LENGTH; j++) {
				double element = Math.random() * X_LENGTH;
				possibleX[j] = element;
				// System.out.println(element);
			}
			population.add(new Individuo(possibleX));
		}
	}

	public Individuo runEvolution() {
		Individuo best = null;
		CutOff cutOff = new CutOff(500, 0.01, 10, 8);
		while (!cutOff.isDone(generationCount, getBestFitness(population),
				getSharedCount(population, lastPopulation))) {
			List<Individuo> next = new ArrayList<>();

			lastPopulation = new ArrayList<>(population); // COPY THEM BEFORE MODIFYING THE POOL

			// LOG LAST POPULATION

			while (population.size() < 2 * P) {
				// PICK 2 from population
				// get 2 new individuos using Cruza(i1, i2)
				// MUTATE the 2 new ones
				// add them to the population? to next?

				// can parents be repeated? can their children be picked? PREGUNTAR
			}

			// SELECT P individuos from population to pass on to next

			population = next; // pisar population con next
		}

		// LOG FINAL POPULATION

		// cut off said we are done, return best one
		return Utils.getBestIndividuo(population);
	}

	private int getSharedCount(List<Individuo> currPop, List<Individuo> lastPop) {
		if (lastPop != null) {
			return Utils.getIntersection(currPop, lastPop).size();
		}
		return 0;
	}

	private double getBestFitness(List<Individuo> pop) {
		return Utils.getBestIndividuo(pop).getFitness();
	}

}
