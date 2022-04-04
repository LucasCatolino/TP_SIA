package sia.grupo19.io;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import sia.grupo19.Individuo;
import sia.grupo19.Params;
import sia.grupo19.CutOff.CutOffReason;

public class Solution {

    public Solution() {
        generationsInfo = new ArrayList<>();
    }

    private int generationCount = 0;
    private Params params;
    private long elapsedTimeMillis;
    private transient List<GenerationInfo> generationsInfo;
    private Individuo optimalIndividuo;
    private CutOffReason stopReason;

    public Individuo getOptimalIndividuo() {
        return generationsInfo.get(generationCount - 1).bestIndividuo;
    }

    @Override
    public String toString() {
        optimalIndividuo = getOptimalIndividuo();
        return new Gson().toJson(this);
    }

    public long getElapsedTimeMillis() {
        return this.elapsedTimeMillis;
    }

    public void setElapsedTimeMillis(long elapsedTimeMillis) {
        this.elapsedTimeMillis = elapsedTimeMillis;
    }

    public void setGenerationsInfo(List<GenerationInfo> generationsInfo) {
        this.generationsInfo = generationsInfo;
    }

    public int getGenerationCount() {
        return this.generationCount;
    }

    public void setGenerationCount(int generationCount) {
        this.generationCount = generationCount;
    }

    public Params getParams() {
        return this.params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public List<GenerationInfo> getGenerationsInfo() {
        return this.generationsInfo;
    }

    public void addGenerationInfo(GenerationInfo generationInfo) {
        this.generationsInfo.add(generationInfo);
    }

    public CutOffReason getStopReason() {
        return this.stopReason;
    }

    public void setStopReason(CutOffReason stopReason) {
        this.stopReason = stopReason;
    }

}
