package sia.grupo19;

import java.io.IOException;

import com.google.gson.Gson;

import sia.grupo19.helpers.ParamsParser;
import sia.grupo19.helpers.Utils;

public class App {
    public static void main(String[] args) throws Exception {

        ParamsParser parser = new ParamsParser("./conf.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        mlp.printFile("BaseAutoEncoder.out");
        System.out.println("Training done");

        double[][] trainingData = parser.getParams().getTrainingDataInputs();

        double[][] expectedOutputs = mlp.runInputs(trainingData);

        MultiLayerPerceptron encoder = mlp.getEncoder();
        double[][] encoderOutputs = encoder.runInputs(trainingData);
        encoder.printFile("EncoderOutputs.out");
        System.out.println("Encoder run done");

        MultiLayerPerceptron decoder = mlp.getDecoder();
        double[][] decoderOutputs = decoder.runInputs(encoderOutputs);

        double[][] testLatent = Utils.getTestingLatent();

        double[][] latenteOutput = decoder.runInputs(testLatent);
        decoder.printFile("DecoderOutputs.out");
        System.out.println("Decoder run done");

        System.out.println("Expected:");
        System.out.println(new Gson().toJson(expectedOutputs));
        System.out.println("Result:");
        System.out.println(new Gson().toJson(decoderOutputs));
        System.out.println("Latente:");
        System.out.println(new Gson().toJson(encoderOutputs));
        System.out.println("Inputs:");
        System.out.println(new Gson().toJson(trainingData));
        System.out.println("TestLatent:");
        System.out.println(new Gson().toJson(testLatent));
        System.out.println("New char:");
        System.out.println(new Gson().toJson(latenteOutput));

        System.out.println("Goodbye.");

    }
}
