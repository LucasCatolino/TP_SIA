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
public class Ex1bTest {

    @Test
    public void Ex1b0point05Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1bconfs/conf9point05.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(parser.getParams().getNoise() + " - Error = " + mlp.getError());

        double[][] noisyInputs = Utils.noiseEmUp(parser.getParams().getTrainingDataInputs(),
                parser.getParams().getNoise());

        MultiLayerPerceptron encoder = mlp.getEncoder();
        double[][] encoderOutputs = encoder.runInputs(noisyInputs);

        MultiLayerPerceptron decoder = mlp.getDecoder();
        double[][] decoderOutputs = decoder.runInputs(encoderOutputs);

        System.out.println("Results:");
        System.out.println(new Gson().toJson(decoderOutputs));

    }

    @Test
    public void Ex1b0point1Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1bconfs/conf9point1.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(parser.getParams().getNoise() + " - Error = " + mlp.getError());
    }

    @Test
    public void Ex1b0point2Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1bconfs/conf9point2.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(parser.getParams().getNoise() + " - Error = " + mlp.getError());
    }

    @Test
    public void Ex1b0point4Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1bconfs/conf9point4.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(parser.getParams().getNoise() + " - Error = " + mlp.getError());
    }

    @Test
    public void Ex1b0point5Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1bconfs/conf9point5.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(parser.getParams().getNoise() + " - Error = " + mlp.getError());

        double[][] noisyInputs = Utils.noiseEmUp(parser.getParams().getTrainingDataInputs(),
                parser.getParams().getNoise());

        MultiLayerPerceptron encoder = mlp.getEncoder();
        double[][] encoderOutputs = encoder.runInputs(noisyInputs);

        MultiLayerPerceptron decoder = mlp.getDecoder();
        double[][] decoderOutputs = decoder.runInputs(encoderOutputs);

        System.out.println("Results:");
        System.out.println(new Gson().toJson(decoderOutputs));
    }

    @Test
    public void Ex1b0point6Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1bconfs/conf9point6.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(parser.getParams().getNoise() + " - Error = " + mlp.getError());
    }

    @Test
    public void Ex1b0point8Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1bconfs/conf9point8.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(parser.getParams().getNoise() + " - Error = " + mlp.getError());
    }

    @Test
    public void Ex1bFullNoiseCompare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1bconfs/conf9full.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(parser.getParams().getNoise() + " - Error = " + mlp.getError());
    }

}
