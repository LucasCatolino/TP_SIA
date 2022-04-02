package sia.grupo19.selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sia.grupo19.Individuo;

public class TruncatedSelector implements Selector {

    private int P, k;

    public TruncatedSelector(int P, int k) {
        this.P = P;
        this.k = k;

        if (k > P) {
            throw new Error("Invalid value: truncated selector's k is greater than P");
        }
    }

    @Override
    public List<Individuo> selectFrom(List<Individuo> inputPopulation, int generationCount) {
        if (inputPopulation.size() != 2 * this.P) {
            throw new Error("Population's size was not equal to 2P");
        }
        List<Individuo> truncatedPopulation = new ArrayList<>(inputPopulation.subList(0, this.k));
        Collections.shuffle(truncatedPopulation);
        return truncatedPopulation.subList(0, P);
    }

}
