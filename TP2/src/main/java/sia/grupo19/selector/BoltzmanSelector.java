package sia.grupo19.selector;

import java.util.ArrayList;
import java.util.List;

import sia.grupo19.Individuo;
import sia.grupo19.Params;

public class BoltzmanSelector implements Selector {

    private int P, t;
    private double Tc, T0, k;

    public BoltzmanSelector(int P, double Tc, double T0, double k) {
        this.P = P;
        this.T0 = T0;
        this.Tc = Tc;
        this.k = k;
    }

    public BoltzmanSelector(Params params) {
        this.P = params.getPopulationSize();
        this.T0 = params.getBoltzmanT0();
        this.Tc = params.getBoltzmanTc();
        this.k = params.getBoltzmanK();
    }

    @Override
    public List<Individuo> selectFrom(List<Individuo> inputPopulation, int generationCount) {
        if (inputPopulation.size() != 2 * P) {
            throw new Error("Population's size was not equal to 2P");
        }

        t = generationCount;

        double totalPartialVE = getTotalPartialVE(inputPopulation, T(t));

        List<Individuo> out = new ArrayList<>();

        // using roulette selection based on VE as the fitness function
        int j = 0;
        while (out.size() < this.P) {
            double r = Math.random();

            double qjPrev = j == 0 ? 0 : sumOfVE(j - 1, inputPopulation, totalPartialVE);

            double qjNext = j == inputPopulation.size() - 1 ? 1
                    : qjPrev + VE(inputPopulation.get(j), T(t), totalPartialVE)
                            + VE(inputPopulation.get(j + 1), T(t), totalPartialVE);

            if (qjPrev < r && r < qjNext) {
                out.add(inputPopulation.get(j));
            }

            j = (j + 1) % inputPopulation.size();
        }

        return out;
    }

    private double sumOfVE(int j, List<Individuo> population, double totalPartialVE) {
        double out = 0;
        for (int i = 0; i <= j; i++) {
            out += VE(population.get(i), T(t), totalPartialVE);
        }

        return out;
    }

    private double VE(Individuo individuo, double T, double totalPartialVE) {
        return partialVE(individuo, T) / totalPartialVE;
    }

    private double getTotalPartialVE(List<Individuo> population, double T) {
        double out = 0;
        for (Individuo i : population) {
            out += partialVE(i, T);
        }

        return out;
    }

    private double partialVE(Individuo individuo, double T) {
        return Math.exp(individuo.getFitness() / T);
    }

    private double T(double t) {
        return Tc + (T0 - Tc) * Math.exp(-k * t);
    }

}
