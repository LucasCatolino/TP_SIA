package sia.grupo19.params;

import com.google.gson.annotations.SerializedName;

public class SimpleParams {

    private int iterationLimit = 1000;
    private double learningRate = 0.1;

    private double[][] trainingDataInputs;
    private double[] trainingDataOutputs;

    private int trainingDataInputDimension = 2;
    private int trainingDataInputSize = 4;

    private SimplePerceptronMode perceptronMode = SimplePerceptronMode.STEP;

    public enum SimplePerceptronMode {
        @SerializedName("step")
        STEP,
        @SerializedName("lineal")
        LINEAL,
        @SerializedName("nonlineal")
        NONLINEAL,
    };

    // GETTERS & SETTERS

    public SimplePerceptronMode getPerceptronMode() {
        return this.perceptronMode;
    }

    public void setPerceptronMode(SimplePerceptronMode perceptronMode) {
        this.perceptronMode = perceptronMode;
    }

    public int getTrainingDataInputSize() {
        return this.trainingDataInputSize;
    }

    public void setTrainingDataInputSize(int trainingDataInputSize) {
        this.trainingDataInputSize = trainingDataInputSize;
    }

    public int getIterationLimit() {
        return this.iterationLimit;
    }

    public void setIterationLimit(int iterationLimit) {
        this.iterationLimit = iterationLimit;
    }

    public double getLearningRate() {
        return this.learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public double[][] getTrainingDataInputs() {
        return this.trainingDataInputs;
    }

    public void setTrainingDataInputs(double[][] trainingDataInputs) {
        this.trainingDataInputs = trainingDataInputs;
    }

    public double[] getTrainingDataOutputs() {
        return this.trainingDataOutputs;
    }

    public void setTrainingDataOutputs(double[] trainingDataOutputs) {
        this.trainingDataOutputs = trainingDataOutputs;
    }

    public int getTrainingDataInputDimension() {
        return this.trainingDataInputDimension;
    }

    public void setTrainingDataInputDimension(int trainingDataInputDimension) {
        this.trainingDataInputDimension = trainingDataInputDimension;
    }

}
