package sia.grupo19;

import com.google.gson.Gson;

public class Params {

    private static final int X_LENGTH = 11;

    public Params() {

    }

    public Params(int P, int maxGen, CrossType crossType, double mutationProb, double mutationDeviation,
            SelectorType selectorType, double minAcceptable, int maxRepeatedStruct, int maxRepeatedContent) {
        this.populationSize = P;
        this.cutOffMaxGen = maxGen;
        this.crossType = crossType;
        this.mutationProb = mutationProb;
        this.mutationDeviation = mutationDeviation;
        this.selectorType = selectorType;
        this.cutOffMinAcceptable = minAcceptable;
        this.maxRepeatedStruct = maxRepeatedStruct;
        this.maxRepeatedContent = maxRepeatedContent;
    }

    public Params(int P, int maxGen, CrossType crossType, double mutationProb, double mutationDeviation,
            SelectorType selectorType, double minAcceptable, int maxRepeatedStruct, int maxRepeatedContent, int kCuts) {
        this.populationSize = P;
        this.cutOffMaxGen = maxGen;
        this.crossType = crossType;
        this.mutationProb = mutationProb;
        this.mutationDeviation = mutationDeviation;
        this.selectorType = selectorType;
        this.cutOffMinAcceptable = minAcceptable;
        this.maxRepeatedStruct = maxRepeatedStruct;
        this.maxRepeatedContent = maxRepeatedContent;
        this.kCuts = kCuts;
    }

    public Params(int P, int maxGen, CrossType crossType, double mutationProb, double mutationDeviation,
            SelectorType selectorType, double minAcceptable, int maxRepeatedStruct, int maxRepeatedContent, int kCuts,
            double boltzmanT0, double boltzmanTc, double boltzmanK) {
        this.populationSize = P;
        this.cutOffMaxGen = maxGen;
        this.crossType = crossType;
        this.mutationProb = mutationProb;
        this.mutationDeviation = mutationDeviation;
        this.selectorType = selectorType;
        this.cutOffMinAcceptable = minAcceptable;
        this.maxRepeatedStruct = maxRepeatedStruct;
        this.maxRepeatedContent = maxRepeatedContent;
        this.kCuts = kCuts;
        this.boltzmanT0 = boltzmanT0;
        this.boltzmanTc = boltzmanTc;
        this.boltzmanK = boltzmanK;
    }

    public enum SelectorType {
        ELITE,
        STOCHASTIC,
        TRUNCATED,
        BOLTZMAN,
        ROULETTE,
        RANK,
        TOURNAMENT,
    };

    public SelectorType parseSelector(String token) {
        String lowerString = token.toLowerCase();
        switch (lowerString) {
            case "elite":
                return SelectorType.ELITE;
            case "stochastic":
                return SelectorType.STOCHASTIC;
            case "truncated":
                return SelectorType.TRUNCATED;
            case "boltzman":
                return SelectorType.BOLTZMAN;
            case "roulette":
                return SelectorType.ROULETTE;
            case "rank":
                return SelectorType.RANK;
            case "tournament":
                return SelectorType.TOURNAMENT;
            default:
                throw new Error("Invalid Selector type");
        }
    }

    public enum CrossType {
        SIMPLE,
        MULTIPLE,
        UNIFORM,
    };

    public CrossType parseCross(String token) {
        String lowerString = token.toLowerCase();
        switch (lowerString) {
            case "simple":
                return CrossType.SIMPLE;
            case "multiple":
                return CrossType.MULTIPLE;
            case "uniform":
                return CrossType.UNIFORM;
            default:
                throw new Error("Invalid Cross type");
        }
    }

    // crossing params
    private CrossType crossType;
    private int kCuts = 1;

    // population params
    private int populationSize = 1000;

    // mutation params
    private double mutationProb = 0.2;
    private double mutationDeviation = 0.05;

    // selection params
    private SelectorType selectorType;
    private int truncatedK = 400;
    private double boltzmanT0 = 5;
    private double boltzmanTc = 2;
    private double boltzmanK = 0.2;

    // cutoff params
    private int cutOffMaxGen = 5000;
    private double cutOffMinAcceptable = 0.01;
    private int maxRepeatedStruct = 10;
    private int maxRepeatedContent = 8;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * GETTERS/SETTERS
     */

    public CrossType getCrossType() {
        return this.crossType;
    }

    public void setCrossType(CrossType crossType) {
        this.crossType = crossType;
    }

    public SelectorType getSelectorType() {
        return this.selectorType;
    }

    public void setSelectorType(SelectorType selectorType) {
        this.selectorType = selectorType;
    }

    public int getPopulationSize() {
        return this.populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getTruncatedK() {
        return this.truncatedK;
    }

    public void setTruncatedK(int truncatedK) {
        this.truncatedK = truncatedK;
    }

    public double getMutationProb() {
        return this.mutationProb;
    }

    public void setMutationProb(double mutationProb) {
        this.mutationProb = mutationProb;
    }

    public double getMutationDeviation() {
        return this.mutationDeviation;
    }

    public void setMutationDeviation(double mutationDeviation) {
        this.mutationDeviation = mutationDeviation;
    }

    public int getCutOffMaxGen() {
        return this.cutOffMaxGen;
    }

    public void setCutOffMaxGen(int cutOffMaxGen) {
        this.cutOffMaxGen = cutOffMaxGen;
    }

    public double getCutOffMinAcceptable() {
        return this.cutOffMinAcceptable;
    }

    public void setCutOffMinAcceptable(double cutOffMinAcceptable) {
        this.cutOffMinAcceptable = cutOffMinAcceptable;
    }

    public int getMaxRepeatedStruct() {
        return this.maxRepeatedStruct;
    }

    public void setMaxRepeatedStruct(int maxRepeatedStruct) {
        this.maxRepeatedStruct = maxRepeatedStruct;
    }

    public int getMaxRepeatedContent() {
        return this.maxRepeatedContent;
    }

    public void setMaxRepeatedContent(int maxRepeatedContent) {
        this.maxRepeatedContent = maxRepeatedContent;
    }

    public int getIndividuoSize() {
        return X_LENGTH;
    }

    public int getKCross() {
        return kCuts;
    }

    public void setKCuts(int kCuts) {
        this.kCuts = kCuts;
    }

    public double getBoltzmanT0() {
        return this.boltzmanT0;
    }

    public void setBoltzmanT0(double boltzmanT0) {
        this.boltzmanT0 = boltzmanT0;
    }

    public double getBoltzmanTc() {
        return this.boltzmanTc;
    }

    public void setBoltzmanTc(double boltzmanTc) {
        this.boltzmanTc = boltzmanTc;
    }

    public double getBoltzmanK() {
        return this.boltzmanK;
    }

    public void setBoltzmanK(double boltzmanK) {
        this.boltzmanK = boltzmanK;
    }
}
