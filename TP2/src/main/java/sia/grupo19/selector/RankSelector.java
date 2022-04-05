package sia.grupo19.selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sia.grupo19.Individuo;

public class RankSelector implements Selector {

    private int P;

    public RankSelector(int P) {
        this.P = P;
    }

    @Override
    public List<Individuo> selectFrom(List<Individuo> inputPopulation, int generationCount) {
        if (inputPopulation.size() != 2 * P) {
            throw new Error("Population's size was not equal to 2P");
        }

        List<Individuo> rankedList = new ArrayList<>(inputPopulation);
        Collections.sort(rankedList, Comparator.comparing(Individuo::getFitness, Comparator.reverseOrder()));

        List<Individuo> out = new ArrayList<>();
        int j = 0;
        while (out.size() < this.P) {
            double totalF1 = sumOfF1(rankedList);
            double r = Math.random();

            double qjPrev = j == 0 ? 0 : sumOfProbabilities(j - 1, inputPopulation, rankedList, totalF1);

            double qjNext = j == inputPopulation.size() - 1 ? 1
                    : qjPrev + calculateF1(inputPopulation.get(j), rankedList)
                            + calculateF1(inputPopulation.get(j + 1), rankedList);

            if (qjPrev < r && r < qjNext) {
                out.add(inputPopulation.remove(j));
            }

            j = (j + 1) % inputPopulation.size();
        }

        return out;

    }

    private double sumOfF1(List<Individuo> rankedPopulation) {
        double out = 0;
        for (int i = 0; i < rankedPopulation.size(); i++) {
            out += calculateF1(rankedPopulation.get(i), rankedPopulation);
        }
        return out;
    }

    private double calculateF1(Individuo individuo, List<Individuo> rankedPopulation) {

        // need to use rankedPopulation since its sorted from top fitness to worst
        return (rankedPopulation.size() - rankedPopulation.indexOf(individuo)) / rankedPopulation.size();
    }

    private double sumOfProbabilities(int j, List<Individuo> population, List<Individuo> rankedList, double totalF1) {
        double out = 0;
        for (int i = 0; i <= j; i++) {
            // get individuo from the list with its original sorting, if it was using the
            // ranked it might be skewed on its selections...
            out += calculateF1(population.get(i), rankedList) / totalF1;
        }
        return out;
    }

}
