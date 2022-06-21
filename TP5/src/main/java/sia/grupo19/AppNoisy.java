package sia.grupo19;

import java.io.IOException;

import com.google.gson.Gson;

import sia.grupo19.helpers.ParamsParser;
import sia.grupo19.helpers.Utils;

public class AppNoisy {
    public static void main(String[] args) throws Exception {

        ParamsParser parser = new ParamsParser("./conf.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        mlp.printFile("NoisyAutoEncoder.out");
        System.out.println("Training done");

        double[][] noisyInputs = Utils.noiseEmUp(parser.getParams().getTrainingDataInputs(),
                parser.getParams().getNoise());

        MultiLayerPerceptron encoder = mlp.getEncoder();
        double[][] encoderOutputs = encoder.runInputs(noisyInputs);
        encoder.printFile("NoisyEncoderOutputs.out");
        System.out.println("Encoder run done");

        MultiLayerPerceptron decoder = mlp.getDecoder();

        double[][] testLatent = Utils.getTestingLatent();

        double[][] latenteOutput = decoder.runInputs(testLatent);
        decoder.printFile("NoisyDecoderOutputs.out");
        System.out.println("Decoder run done");

        System.out.println("TestLatent:");
        System.out.println(new Gson().toJson(testLatent));
        System.out.println("New char:");
        System.out.println(new Gson().toJson(latenteOutput));

        System.out.println("Goodbye.");

    }
}
