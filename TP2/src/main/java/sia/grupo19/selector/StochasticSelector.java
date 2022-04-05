package sia.grupo19.selector;

import java.util.Collections;
import java.util.List;

import sia.grupo19.Individuo;

public class StochasticSelector implements Selector {

    private int P;

    public StochasticSelector(int P) {
        this.P = P;
    }

    @Override
    public List<Individuo> selectFrom(List<Individuo> inputPopulation, int generationCount) {
        if (inputPopulation.size() != 2 * this.P) {
            throw new Error("Population's size was not equal to 2P");
        }
        // shuffle generation, grab the first P elements
        Collections.shuffle(inputPopulation);
        return inputPopulation.subList(0, P);
    }

}
