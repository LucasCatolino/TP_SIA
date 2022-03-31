package sia.grupo19;

public class Params {
	
	private static final int X_LENGTH= 11;

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

    public enum SelectorType {
        ELITE,
        STOCHASTIC,
        TRUNCATED,
        BOLTZMANN,
        ROULETE,
        RANK,
        TOURNAMENT,
    };

    public enum CrossType {
        SIMPLE,
        MULTIPLE,
        UNIFORM,
    };

    // crossing params
    private CrossType crossType;

    // population params
    private int populationSize = 1000;

    // mutation params
    private double mutationProb = 0.2;
    private double mutationDeviation = 0.05;

    // selection params
    private SelectorType selectorType;
    private int truncatedK = 400;

    // cutoff params
    private int cutOffMaxGen = 5000;
    private double cutOffMinAcceptable = 0.01;
    private int maxRepeatedStruct = 10;
    private int maxRepeatedContent = 8;

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
}
