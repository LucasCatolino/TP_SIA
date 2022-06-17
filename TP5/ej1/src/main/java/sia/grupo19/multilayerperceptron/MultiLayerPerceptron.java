package sia.grupo19.multilayerperceptron;

import java.util.ArrayList;
import java.util.Collections;

/**
 * TODO:
 * [ ]-> Solution object for logging
 * [ ]-> Params object for my sanity
 * [ ]-> a function to run the perceptron from the middle (tirarle muestras del
 * espacio latente al decodificador)
 * [ ]-> log "output" from the middle (cuando le pasamos algo al encoder, vamos
 * a querer ver como se representa en el espacio latente)
 * [ ]-> aplicar alguna optimizacion (momentum)
 * [ ]-> git gud
 */
public class MultiLayerPerceptron {

    private static double[][] X;
    private static double[] Y;
    private static final double ERROR = 0.00001;
    private static int EPOCHS;

    private Layer[] network;
    private Layer[] bestNetwork;

    private int lastLayer;

    private ArrayList<Integer> indexList;
    private double minError;

    private ArrayList<String> toPrint;

    public MultiLayerPerceptron(int inputSize, int outputSize, int hiddenLayers, int[] hiddenLayersSizes, double[][] x,
            double[] y, int epochs) {
        X = x;
        Y = y;
        EPOCHS = epochs;

        toPrint = new ArrayList<>();

        this.network = new Layer[hiddenLayers + 2]; // +2 because of input and output layers

        Layer inputLayer = new Layer(inputSize);
        network[0] = inputLayer;

        for (int i = 0; i < hiddenLayers; i++) {
            network[i + 1] = new Layer(network[i], hiddenLayersSizes[i], false);
        }

        Layer outputLayer = new Layer(network[network.length - 2], outputSize, true);
        this.lastLayer = hiddenLayers + 1;
        network[lastLayer] = outputLayer;

        this.indexList = new ArrayList<Integer>();
        this.minError = Integer.MAX_VALUE;
        this.bestNetwork = network;
    }

    public void run() {
        int epoch = 0;
        initializeUnits();
        double error = 1;

        while (epoch < EPOCHS && error > ERROR) {
            double auxError = 0;
            initializeIndex(X.length);
            toPrint.add("Epoch: " + epoch + "\n");

            while (!indexList.isEmpty()) {
                // get random X_i
                int position = indexList.remove(0);

                // calculate activation for input layer
                network[0].apply(X[position]);

                // propagate activation to output layer
                propagate(network);

                // calculate delta for output layer
                network[lastLayer].calculateDelta(Y[position]);

                // backpropagate delta
                backpropagate();

                // update weights
                updateWeights();

                // update auxError
                auxError += calculateError(position, network[lastLayer]);

                // restart excitations, activations and deltas
                restartUnits();
            }

            epoch++;
            error = auxError / 2;
            toPrint.add("Error: " + error + "\n");
            updateBestNetwork(error);
        }
    }

    public void evaluate() {
        toPrint.add("\nFinal output" + "\n");
        for (int i = 0; i < X.length; i++) {
            bestNetwork[0].apply(X[i]);
            propagate(bestNetwork);
            toPrint.add("Expected: " + Y[i] + ", result: " + bestNetwork[lastLayer].getTotalActivation() + "\n");
            restartUnits();
        }
    }

    private void initializeUnits() {
        for (int i = 0; i < lastLayer + 1; i++) {
            network[i].initializeWeights();
        }

    }

    private void initializeIndex(int limit) {
        for (int i = 0; i < limit; i++) {
            indexList.add(i);
        }
        Collections.shuffle(indexList);
    }

    private void propagate(Layer[] network) {
        for (int i = 1; i < lastLayer; i++) { // i= 1 because first layer was already calculated
            network[i].apply();
        }
        // System.out.println("capa: " + lastLayer);
        network[lastLayer].apply(true);

    }

    private void backpropagate() {
        for (int i = lastLayer - 1; i > 0; i--) {
            network[i].calculateDelta();
        }
    }

    private void updateWeights() {
        for (int i = 1; i < lastLayer + 1; i++) {
            network[i].updateWeights();
        }
    }

    private double calculateError(int position, Layer layer) {
        double errorToRet = Y[position] - layer.getTotalActivation();
        toPrint.add("Expected: " + Y[position] + " result: " + layer.getTotalActivation() + "\n");
        return Math.pow(errorToRet, 2);
    }

    private void restartUnits() {
        for (int i = 1; i < lastLayer + 1; i++) {
            network[i].restartUnits();
        }
    }

    private void updateBestNetwork(double error) {
        if (error < minError) {
            bestNetwork = network;
            minError = error;
        }
    }

}
