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
    private SimpleParams params;
    private long elapsedTimeMillis;
    private transient List<IterationInfo> iterationsInfo;
    private CutOffReason stopReason;
    private IterationInfo finalIteration;

    @Override
    public String toString() {
        finalIteration = getFinalIteration();
        return new Gson().toJson(this);
    }

    // GETTERS & SETTERS

    public int getIterations() {
        return this.iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
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

    public IterationInfo getFinalIteration() {
        return this.iterationsInfo.get(iterations - 1);
    }

    public void setFinalIteration(IterationInfo finalIteration) {
        this.finalIteration = finalIteration;
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
