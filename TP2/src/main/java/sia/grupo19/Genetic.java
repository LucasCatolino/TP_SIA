package sia.grupo19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sia.grupo19.cross.Cross;
import sia.grupo19.cross.CrossFactory;
import sia.grupo19.io.GenerationInfo;
import sia.grupo19.io.Solution;
import sia.grupo19.selector.Selector;
import sia.grupo19.selector.SelectorFactory;

public class Genetic {

	private static final int X_LENGTH = 11;

	// parameters
	private Params params;

	private int generationCount = 0;
	private List<Individuo> population;
	private List<Individuo> lastPopulation = null;

	public Genetic(int P, int generations, String cross, double prob, double sigma, String selection) {
		population = new ArrayList<Individuo>();

		initializePopulation(P);
	}

	public Genetic(Params params) {
		this.params = params;
		population = new ArrayList<Individuo>();

		initializePopulation(this.params.getPopulationSize());
	}

	private void initializePopulation(int p) {
		for (int i = 0; i < p; i++) {
			double[] possibleX = new double[X_LENGTH];
			for (int j = 0; j < X_LENGTH; j++) {
				double element = Math.random();
				possibleX[j] = (Math.random() > 0.5) ? element : -element; // random between -1 and 1
				// System.out.println(possibleX[j]);
			}
			population.add(new Individuo(possibleX));
		}
	}

	public Solution runEvolution() {
		long startTime = System.currentTimeMillis();
		Individuo best = null; // TODO: return Solution object, that contains best and more info
		Solution solution = new Solution();
		solution.setParams(params);

		CutOff cutOff = new CutOff(this.params);
		Selector selector = new SelectorFactory().getSelector(this.params);
		Cross crosser = new CrossFactory().getCross(this.params);

		while (!cutOff.isDone(generationCount, getBestFitness(population),
				getSharedCount(population, lastPopulation))) {

			lastPopulation = new ArrayList<>(population); // COPY THEM BEFORE MODIFYING THE POOL
			List<Individuo> tiredParents = new ArrayList<>();

			// TODO: LOG LAST POPULATION
			solution.addGenerationInfo(new GenerationInfo(population));

			// Duplicate population: P/2 because in each row two sons are created
			for (int i = 0; i < params.getPopulationSize() / 2; i++) {
				// Sort population by fitness
				Collections.sort(population, Comparator.comparing(Individuo::getFitness, Comparator.reverseOrder()));

				// Take first two elements
				Individuo father = population.remove(0);
				Individuo mother = population.remove(0);

				// Cross father and mother to have two kids
				Individuo[] sons = crosser.cross(father, mother);
				// Individuo[] sons = cross(father, mother);

				// Parents are tired so they won't love again for a while
				tiredParents.add(father);
				tiredParents.add(mother);

				// Offspring might become mutants
				sons[0].mutate(params.getMutationProb(), params.getMutationDeviation());
				sons[1].mutate(params.getMutationProb(), params.getMutationDeviation());

				// Now offspring is ready to become possible parents
				population.add(sons[0]);
				population.add(sons[1]);
			}

			// P kids where born, now they are all tired
			tiredParents.addAll(population);

			// A new population is selected from previous population and fresh ones
			population = selector.selectFrom(tiredParents, generationCount);

			generationCount++;
		}
		long stopTime = System.currentTimeMillis();
		solution.setElapsedTimeMillis(stopTime - startTime);

		solution.setStopReason(cutOff.reason(generationCount, getBestFitness(population),
				getSharedCount(population, lastPopulation)));

		// TODO: LOG FINAL POPULATION
		solution.addGenerationInfo(new GenerationInfo(population));
		solution.setGenerationCount(generationCount);

		return solution;

		// cut off said we are done, return best one
		// return Utils.getBestIndividuo(population);
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
