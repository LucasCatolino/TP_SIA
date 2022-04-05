package sia.grupo19.io;

import java.util.List;

import com.google.gson.Gson;

import sia.grupo19.Individuo;
import sia.grupo19.Utils;

public class GenerationInfo {
    Individuo bestIndividuo;
    double averageFitness, worstFitness;
    double[] averageF3 = new double[3];
    double[] worstF3 = new double[3];

    public GenerationInfo(List<Individuo> generation) {
        bestIndividuo = Utils.getBestIndividuo(generation);
        averageFitness = Utils.getAverageFitness(generation);
        worstFitness = Utils.getWorstIndividuo(generation).getFitness();
        averageF3 = Utils.getAverageF3(generation);
        worstF3 = Utils.getWorstIndividuo(generation).getF3();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}