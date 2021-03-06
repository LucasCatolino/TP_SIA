package sia.grupo19.params;

import com.google.gson.annotations.SerializedName;

public class SimpleParams implements Cloneable {

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private int iterationLimit = 1000;
    private double learningRate = 0.1;
    private double minAcceptable = 0;

    private double[][] trainingDataInputs;
    private double[] trainingDataOutputs;

    private int trainingDataInputDimension = 2;
    private int trainingDataInputSize = 4;

    private SimplePerceptronMode perceptronMode = SimplePerceptronMode.STEP;
    private int kCuts = 3;

    private double beta = 1.5;
    private NonLinearSigmoidType sigmoidType = NonLinearSigmoidType.TANH;

    private PrintHistoryMode printHistory = PrintHistoryMode.OFF;

    public enum SimplePerceptronMode {
        @SerializedName("step")
        STEP,
        @SerializedName("linear")
        LINEAR,
        @SerializedName("nonlinear")
        NONLINEAR,
    };

    public enum NonLinearSigmoidType {
        @SerializedName("tanh")
        TANH,
        @SerializedName("logistic")
        LOGISTIC,
    };

    public enum PrintHistoryMode {
        @SerializedName("off")
        OFF,
        @SerializedName("iters")
        ITERS,
        @SerializedName("epochs")
        EPOCHS,
        @SerializedName("both")
        BOTH,
    }

    // GETTERS & SETTERS

    public double getMinAcceptable() {
        return this.minAcceptable;
    }

    public void setMinAcceptable(double minAcceptable) {
        this.minAcceptable = minAcceptable;
    }

    public PrintHistoryMode getPrintHistory() {
        return this.printHistory;
    }

    public void setPrintHistory(PrintHistoryMode printHistory) {
        this.printHistory = printHistory;
    }

    public NonLinearSigmoidType getSigmoidType() {
        return this.sigmoidType;
    }

    public void setSigmoidType(NonLinearSigmoidType sigmoidType) {
        this.sigmoidType = sigmoidType;
    }

    public double getBeta() {
        return this.beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

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

    public int getKCuts() {
        return this.kCuts;
    }

    public void setKCuts(int kCuts) {
        this.kCuts = kCuts;
    }
}
