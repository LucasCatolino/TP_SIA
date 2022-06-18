package sia.grupo19.helpers;

import com.google.gson.Gson;

public class ParamsContainer implements Cloneable {

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    private int inputSize; // dimension of a single sample (input layer)

    private int outputSize; // dimension of a single output (output layer)

    private int hiddenLayers; // number of hidden layers
    private int[] hiddenLayersSizes; // dimensions for each hidden layer

    private double[][] trainingDataInputs;
    private double[][] trainingDataOutputs;

    private int epochs;
    private double noise;
    private double momentum = 0.5;

    public ParamsContainer() {
    }

    public ParamsContainer(int inputSize, int outputSize, int hiddenLayers, int[] hiddenLayersSizes,
            double[][] trainingDataInputs, double[][] trainingDataOutputs, int epochs, double noise, double momentum) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.hiddenLayers = hiddenLayers;
        this.hiddenLayersSizes = hiddenLayersSizes;
        this.trainingDataInputs = trainingDataInputs;
        this.trainingDataOutputs = trainingDataOutputs;
        this.epochs = epochs;
        this.noise = noise;
        this.momentum = momentum;
    }

    public int getInputSize() {
        return this.inputSize;
    }

    public void setInputSize(int inputSize) {
        this.inputSize = inputSize;
    }

    public int getOutputSize() {
        return this.outputSize;
    }

    public void setOutputSize(int outputSize) {
        this.outputSize = outputSize;
    }

    public int getHiddenLayers() {
        return this.hiddenLayers;
    }

    public void setHiddenLayers(int hiddenLayers) {
        this.hiddenLayers = hiddenLayers;
    }

    public int[] getHiddenLayersSizes() {
        return this.hiddenLayersSizes;
    }

    public void setHiddenLayersSizes(int[] hiddenLayersSizes) {
        this.hiddenLayersSizes = hiddenLayersSizes;
    }

    public double[][] getTrainingDataInputs() {
        return this.trainingDataInputs;
    }

    public void setTrainingDataInputs(double[][] trainingDataInputs) {
        this.trainingDataInputs = trainingDataInputs;
    }

    public double[][] getTrainingDataOutputs() {
        return this.trainingDataOutputs;
    }

    public void setTrainingDataOutputs(double[][] trainingDataOutputs) {
        this.trainingDataOutputs = trainingDataOutputs;
    }

    public int getEpochs() {
        return this.epochs;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }

    public double getNoise() {
        return this.noise;
    }

    public void setNoise(double noise) {
        this.noise = noise;
    }

    public double getMomentum() {
        return this.momentum;
    }

    public void setMomentum(double momentum) {
        this.momentum = momentum;
    }

}
