package sia.grupo19.selector;

import java.util.List;

import sia.grupo19.Individuo;

public interface Selector {
    List<Individuo> selectFrom(List<Individuo> inputPopulation);
}
