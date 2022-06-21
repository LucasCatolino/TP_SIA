package sia.grupo19;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import org.junit.Test;

import sia.grupo19.helpers.EpochInfo;
import sia.grupo19.helpers.ParamsParser;
import sia.grupo19.helpers.Utils;
import sia.grupo19.helpers.Writer;
import sia.grupo19.params.SimpleParser;
import sia.grupo19.params.SimpleSolution;
import sia.grupo19.params.SimpleWriter;

/**
 * Unit test for simple App.
 */
public class LatentesTest {

    @Test
    public void autoEncoderLatenteOfTraining() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1aconfs/conf9.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();

        double[][] trainingData = parser.getParams().getTrainingDataInputs();

        double[][] expectedOutputs = mlp.runInputs(trainingData);

        MultiLayerPerceptron encoder = mlp.getEncoder();
        double[][] encoderOutputs = encoder.runInputs(trainingData);

        MultiLayerPerceptron decoder = mlp.getDecoder();
        double[][] decoderOutputs = decoder.runInputs(encoderOutputs);

        /*
         *
         * double[][] testLatent = Utils.getTestingLatent();
         * // double[][] testtt = { { 0.8999999999999998, 0.9999999999999998 } };
         * double[][] latenteOutput = decoder.runInputs(testLatent);
         */

        System.out.println("Expected:");
        System.out.println(new Gson().toJson(expectedOutputs));
        System.out.println("Result:");
        System.out.println(new Gson().toJson(decoderOutputs));
        System.out.println("Latente:");
        System.out.println(new Gson().toJson(encoderOutputs));
        System.out.println("Inputs:");
        System.out.println(new Gson().toJson(trainingData));
        /*
         *
         * System.out.println("TestLatent:");
         * System.out.println(new Gson().toJson(testLatent));
         * System.out.println("New char:");
         * System.out.println(new Gson().toJson(latenteOutput));
         */
    }

}
