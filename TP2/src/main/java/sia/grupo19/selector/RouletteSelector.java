package sia.grupo19.selector;

import java.util.ArrayList;
import java.util.List;

import sia.grupo19.Individuo;

public class RouletteSelector implements Selector {

    private int P;

    public RouletteSelector(int P) {
        this.P = P;
    }

    @Override
    public List<Individuo> selectFrom(List<Individuo> inputPopulation, int generationCount) {
        if (inputPopulation.size() != 2 * P) {
            throw new Error("Population's size was not equal to 2P");
        }

        double totalFitness = inputPopulation.stream().mapToDouble(Individuo::getFitness).sum();

        List<Individuo> out = new ArrayList<>();
        int j = 0;
        while (out.size() < this.P) {
            double r = Math.random();

            double qjPrev = j == 0 ? 0 : sumOfProbabilities(j - 1, inputPopulation, totalFitness);
            double qjNext = j == inputPopulation.size() - 1 ? 1
                    : qjPrev + probability(inputPopulation.get(j).getFitness(), totalFitness)
                            + probability(inputPopulation.get(j + 1).getFitness(), totalFitness);

            if (qjPrev < r && r < qjNext) {
                out.add(inputPopulation.get(j));
            }

            j = (j + 1) % inputPopulation.size();
        }

        return out;
    }

    private double sumOfProbabilities(int j, List<Individuo> population, double totalFitness) {
        double out = 0;
        for (int i = 0; i <= j; i++) {
            out += probability(population.get(i).getFitness(), totalFitness);
        }
        return out;
    }

    private double probability(double iFitness, double totalFitness) {
        return iFitness / totalFitness;
    }

}
