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

    public SimpleCrossValidator(SimpleParams params) {
        this.params = params;

        divideData(params.getTrainingDataInputs(), params.getTrainingDataOutputs());

        System.out.println(new Gson().toJson(this.inputSubsets));
        System.out.println(new Gson().toJson(this.outputSubsets));

    }

    private void divideData(double[][] inputs, double[] outputs) {
        int divisionSize = params.getTrainingDataInputSize() / params.getKCuts();

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

    public void run() {
        List<SimpleSolution> iters = new ArrayList<>();
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

            try {
                SimpleParams newParams = (SimpleParams) params.clone();
                newParams.setTrainingDataInputSize(newP);
                newParams.setTrainingDataInputs(trainingSetInputs);
                newParams.setTrainingDataOutputs(trainingSetOutputs);

                SimplePerceptron sp = new SimplePerceptron(newParams);
                iters.add(sp.run());

            } catch (CloneNotSupportedException e) {
                // TODO Auto-generated catch block
                System.out.println("parameters cloning failed");
                e.printStackTrace();
            }

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
             * System.out.println("+++++++++++++++++++++++++++++++");
             *
             */
            excludedSubsetIndex++;
        }

        for (SimpleSolution s : iters) {
            System.out.println(new Gson().toJson(s.getBestIteration()) + "\n++++++++++++++\n");
        }

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

}
