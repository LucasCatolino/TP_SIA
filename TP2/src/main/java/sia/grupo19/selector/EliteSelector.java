package sia.grupo19.selector;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sia.grupo19.Individuo;

public class EliteSelector implements Selector {

    private int P;

    public EliteSelector(int P) {
        this.P = P;
    }

    @Override
    public List<Individuo> selectFrom(List<Individuo> inputPopulation, int generationCount) {
        if (inputPopulation.size() != 2 * P) {
            throw new Error("Population's size was not equal to 2P");
        }
        // sort population from highest to lowest fitness
        Collections.sort(inputPopulation, Comparator.comparing(Individuo::getFitness, Comparator.reverseOrder()));
        // instead of returning a new list of size P everytime (if P is big, this can
        // get expensive), return slice
        return inputPopulation.subList(0, P);
    }

}
