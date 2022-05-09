package sia.grupo19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;

import sia.grupo19.params.IterationInfo;
import sia.grupo19.params.SimpleParams;
import sia.grupo19.params.SimpleSolution;

public class SimpleCrossValidator {

    private SimpleParams params;

    private List<double[][]> inputSubsets;
    private List<double[]> outputSubsets;

    private double minExpectedOutput, maxExpectedOutput;

    public SimpleCrossValidator(SimpleParams params) {
        this.params = params;

        minExpectedOutput = Arrays.stream(params.getTrainingDataOutputs()).min().getAsDouble();
        maxExpectedOutput = Arrays.stream(params.getTrainingDataOutputs()).max().getAsDouble();

        divideData(params.getTrainingDataInputs(), params.getTrainingDataOutputs());

        System.out.println(new Gson().toJson(this.inputSubsets));
        System.out.println(new Gson().toJson(this.outputSubsets));

    }

    public List<SimpleSolution> run() {
        List<SimpleSolution> perms = new ArrayList<>();
        int excludedSubsetIndex = 0;

        while (excludedSubsetIndex < this.inputSubsets.size()) {
            int newP = getTrainingSize(excludedSubsetIndex);
            double[][] trainingSetInputs = new double[newP][];
            double[] trainingSetOutputs = new double[newP];

            int lastSize = 0;
            for (int i = 0; i < this.inputSubsets.size(); i++) {
                if (i != excludedSubsetIndex) {
                    System.arraycopy(this.inputSubsets.get(i), 0, trainingSetInputs, lastSize,
                            this.inputSubsets.get(i).length);
                    System.arraycopy(this.outputSubsets.get(i), 0, trainingSetOutputs, lastSize,
                            this.outputSubsets.get(i).length);
                    lastSize += this.inputSubsets.get(i).length;
                }
            }

            double[][] testingSetInputs = this.inputSubsets.get(excludedSubsetIndex);
            double[] testingSetOutputs = this.outputSubsets.get(excludedSubsetIndex);

            try {
                SimpleParams newParams = (SimpleParams) params.clone();
                newParams.setTrainingDataInputSize(newP);
                newParams.setTrainingDataInputs(trainingSetInputs);
                newParams.setTrainingDataOutputs(trainingSetOutputs);

                SimplePerceptron sp = new SimplePerceptron(newParams, minExpectedOutput, maxExpectedOutput);
                SimpleSolution sol = sp.runExtended(testingSetInputs, testingSetOutputs);
                perms.add(sol);

                double finalAccuracy = sp.calculateAccuracy(sol.getBestIteration().getW(), testingSetInputs,
                        testingSetOutputs, 0.18);

                /*
                 * System.out.println("training set inputs " + new
                 * Gson().toJson(trainingSetInputs));
                 * System.out.println("training set outputs " + new
                 * Gson().toJson(trainingSetOutputs));
                 *
                 * System.out.println("test set inputs" + new
                 * Gson().toJson(this.inputSubsets.get(excludedSubsetIndex)));
                 * System.out.println("test set outputs" + new
                 * Gson().toJson(this.outputSubsets.get(excludedSubsetIndex)));
                 *
                 * System.out.println("final accuracy: " + finalAccuracy);
                 * System.out.println("best error: " + sol.getBestIteration().getError());
                 *
                 * System.out.println("+++++++++++++++++++++++++++++++");
                 */

            } catch (CloneNotSupportedException e) {
                System.out.println("parameters cloning failed!");
                e.printStackTrace();
            }

            excludedSubsetIndex++;
        }

        return perms;

        /*
         * for (SimpleSolution s : iters) {
         * System.out.println(new Gson().toJson(s.getBestIteration()) +
         * "\n++++++++++++++\n");
         * }
         */

    }

    private int getTrainingSize(int excludedSubsetIndex) {
        int count = 0;
        for (int i = 0; i < this.inputSubsets.size(); i++) {
            if (i != excludedSubsetIndex) {
                count += this.inputSubsets.get(i).length;
            }
        }

        return count;
    }

    private void divideData(double[][] inputs, double[] outputs) {
        int divisionSize = params.getTrainingDataInputSize() / params.getKCuts();
        if (divisionSize < 1) {
            throw new Error("Invalid k-parts value");
        }

        List<double[][]> inputDivisions = new ArrayList<>(); // outcome
        List<double[]> outputDivisions = new ArrayList<>(); // outcome

        List<Integer> indices = getNewIndices(params.getTrainingDataInputSize());

        while (inputDivisions.size() < params.getKCuts()) {
            List<double[]> inputPart = new ArrayList<>();
            List<Double> outputPart = new ArrayList<>();

            while (indices.size() > 0
                    && (inputPart.size() < divisionSize ? true : inputDivisions.size() == (params.getKCuts() - 1))) {
                int i = indices.get(0);

                inputPart.add(inputs[i]);
                outputPart.add(outputs[i]);

                indices.remove(0);
            }

            double[][] auxInputPart = inputPart.toArray(new double[inputPart.size()][]);
            inputDivisions.add(auxInputPart);

            Double[] aux = outputPart.toArray(new Double[outputPart.size()]);
            double[] auxOutputPart = Stream.of(aux).mapToDouble(Double::doubleValue).toArray();

            outputDivisions.add(auxOutputPart);

        }

        this.inputSubsets = inputDivisions;
        this.outputSubsets = outputDivisions;

    }

    private List<Integer> getNewIndices(int size) {
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            out.add(i);
        }

        return out;
    }

}
