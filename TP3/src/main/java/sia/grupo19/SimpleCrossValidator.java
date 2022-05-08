package sia.grupo19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;

import sia.grupo19.params.SimpleParams;

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

}
