package sia.grupo19;

public class CutOff {

    private int maxGenCount;
    private double minAcceptable;

    private int SHARED_EPSILON = 50; // make this number into a parameter?
    private int lastSharedGenStructure = 0; // initially no shared individuals
    private int repeatedGenStructureCount = 0;
    private int maxRepeatedGenStructure;

    private double EPSILON = 0.0001; // make this number into a parameter?
    private double lastBestFitness = -1000; // initially, any best fitness should be good
    private int repeatedGenContentCount = 0;
    private int maxRepeatedGenContent;

    public CutOff(int maxGenCount, double minAcceptable, int maxRepeatedGenStructure, int maxRepeatedGenContent) {
        this.maxGenCount = maxGenCount;
        // needs to be negative since all fitness values will be negative too
        this.minAcceptable = Math.abs(minAcceptable) * -1;
        this.maxRepeatedGenStructure = maxRepeatedGenStructure;
        this.maxRepeatedGenContent = maxRepeatedGenContent;
    }

    public CutOff(Params params) {
        this.maxGenCount = params.getCutOffMaxGen();
        // needs to be negative since all fitness values will be negative too
        this.minAcceptable = Math.abs(params.getCutOffMinAcceptable()) * -1;
        this.maxRepeatedGenStructure = params.getMaxRepeatedStruct();
        this.maxRepeatedGenContent = params.getMaxRepeatedContent();
    }

    public boolean isDone(int currGen, double currBestFitness, int sharedGenStructure) {
        return isMaxGen(currGen) || isAcceptable(currBestFitness) || isMaxRepeatedContent(currBestFitness)
                || isMaxRepeatedStructure(sharedGenStructure);
    }

    private boolean isMaxRepeatedStructure(int sharedGenStructure) {
        int diff = Math.abs(sharedGenStructure - lastSharedGenStructure);
        if (diff >= SHARED_EPSILON) {
            repeatedGenStructureCount++;
        } else {
            repeatedGenStructureCount = 0; // reset
        }

        return repeatedGenStructureCount >= maxRepeatedGenStructure;
    }

    private boolean isMaxRepeatedContent(double currBestFitness) {
        double diff = Math.abs(currBestFitness - lastBestFitness);
        if (diff < EPSILON) {
            repeatedGenContentCount++;
        } else {
            repeatedGenContentCount = 0; // reset
        }
        return repeatedGenContentCount >= maxRepeatedGenContent;
    }

    private boolean isAcceptable(double currBestFitness) {
        return currBestFitness >= minAcceptable;
    }

    private boolean isMaxGen(int currGen) {
        return currGen >= maxGenCount;
    }

}
