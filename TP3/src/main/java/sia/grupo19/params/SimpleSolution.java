package sia.grupo19.params;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class SimpleSolution {

    public SimpleSolution() {
        iterationsInfo = new ArrayList<>();
    }

    public enum CutOffReason {
        MAXITER,
        MINACCEPTABLE,
    };

    private int iterations;
    private int epochs;

    private SimpleParams params;
    private long elapsedTimeMillis;

    private transient List<IterationInfo> iterationsInfo;
    private CutOffReason stopReason;

    private IterationInfo bestIteration;

    @Override
    public String toString() {
        ;
        return new Gson().toJson(this);
    }

    // GETTERS & SETTERS

    public int getIterations() {
        return this.iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getEpochs() {
        return this.epochs;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }

    public SimpleParams getParams() {
        return this.params;
    }

    public void setParams(SimpleParams params) {
        this.params = params;
    }

    public long getElapsedTimeMillis() {
        return this.elapsedTimeMillis;
    }

    public void setElapsedTimeMillis(long elapsedTimeMillis) {
        this.elapsedTimeMillis = elapsedTimeMillis;
    }

    public CutOffReason getStopReason() {
        return this.stopReason;
    }

    public void setStopReason(CutOffReason stopReason) {
        this.stopReason = stopReason;
    }

    public IterationInfo getBestIteration() {
        return this.bestIteration;
    }

    public void setBestIteration(IterationInfo bestIteration) {
        this.bestIteration = bestIteration;
    }

    public void setIterationsInfo(List<IterationInfo> iterationsInfo) {
        this.iterationsInfo = iterationsInfo;
    }

    public List<IterationInfo> getIterationsInfo() {
        return this.iterationsInfo;
    }

    public void addIterationInfo(IterationInfo iterationInfo) {
        this.iterationsInfo.add(iterationInfo);
    }

}
