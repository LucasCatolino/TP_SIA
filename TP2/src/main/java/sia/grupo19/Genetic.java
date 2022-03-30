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
				double element = Math.random();
				possibleX[j] = (Math.random() > 0.5) ? element : -element; // random between -1 and 1
				System.out.println(possibleX[j]);
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
			List<Individuo> tiredParents = new ArrayList<>();

			// LOG LAST POPULATION
			// Duplicate population: P/2 because in each row two sons are created
			for (int i = 0; i < P / 2; i++) {
				// Sort population by fitness
				Collections.sort(population, Comparator.comparing(Individuo::getFitness));

				// Take first two elements
				Individuo father = population.remove(0);
				Individuo mother = population.remove(0);

				// Cross father and mother to have two kids
				Individuo[] sons = cross(father, mother);

				// Parents are tired so they won't love again for a while
				tiredParents.add(father);
				tiredParents.add(mother);

				// Offspring might become mutants
				sons[0].mutate(mutationProb, deviation);
				sons[1].mutate(mutationProb, deviation);

				// Now offspring is ready to become possible parents
				population.add(sons[0]);
				population.add(sons[1]);
			}

			// P kids where born, now they are all tired
			tiredParents.addAll(population);

			// A new population is selected from previous population and fresh ones
			population = select(tiredParents);

			// while (population.size() < 2 * P) {
			// PICK 2 from population
			// get 2 new individuos using Cruza(i1, i2)
			// MUTATE the 2 new ones
			// add them to the population? to next?

			// can parents be repeated? can their children be picked? PREGUNTAR
			// }

			// SELECT P individuos from population to pass on to next

			// population = next; // pisar population con next
		}

		// LOG FINAL POPULATION

		// cut off said we are done, return best one
		return Utils.getBestIndividuo(population);
	}

	private List<Individuo> select(List<Individuo> possiblePopulation) {
		// TODO: con un case ver que metodo se esta usando
		Collections.sort(possiblePopulation, Comparator.comparing(Individuo::getFitness, Comparator.reverseOrder()));
		return possiblePopulation.subList(0, P);
	}

	private Individuo[] cross(Individuo father, Individuo mother) {
		// TODO: con un case ver que metodo se esta usando
		// Nota: esto es cruza simple, despues modularizo
		int index = (int) Math.floor(Math.random() * X_LENGTH); // Random value between 0 and 11
		double[] son1 = new double[11];
		double[] son2 = new double[11];
		for (int i = 0; i < index; i++) {
			son1[i] = father.getX()[i];
			son2[i] = mother.getX()[i];
		}
		for (int i = index; i < X_LENGTH; i++) {
			son1[i] = mother.getX()[i];
			son2[i] = father.getX()[i];
		}
		Individuo[] toRet = new Individuo[2];
		toRet[0] = new Individuo(son1);
		toRet[1] = new Individuo(son2);
		return toRet;
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
