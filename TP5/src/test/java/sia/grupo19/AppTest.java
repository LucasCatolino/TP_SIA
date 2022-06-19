package sia.grupo19;

import static org.junit.Assert.assertTrue;

import java.util.List;

import com.google.gson.Gson;

import org.junit.Test;

import sia.grupo19.helpers.ParamsParser;
import sia.grupo19.params.SimpleParser;
import sia.grupo19.params.SimpleSolution;
import sia.grupo19.params.SimpleWriter;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testParseParams() throws Exception {
        ParamsParser parser = new ParamsParser("./confAUTO.json", true);
        System.out.println(new Gson().toJson(parser.getParams()));
    }

    @Test
    public void testAutoEncoderWithParams() throws Exception {
        ParamsParser parser = new ParamsParser("./confAUTO.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        mlp.testNoise();
        mlp.printFile("TEST_Ex3_3.out");
    }

    @Test
    public void testGetEncoderAndLatente() throws Exception {
        ParamsParser parser = new ParamsParser("./confAUTO.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        mlp.testNoise();
        MultiLayerPerceptron encoder = mlp.getEncoder();
        encoder.testInputs(parser.getParams().getTrainingDataInputs());
        encoder.printFile("TEST_Latente.out");
    }

    @Test
    public void testGetDecoderAndLatente() throws Exception {
        ParamsParser parser = new ParamsParser("./confAUTO.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        mlp.testNoise();
        MultiLayerPerceptron decoder = mlp.getDecoder();
        double[][] latentes = {
                { -0.8628522390911326, 0.024698937470470993 },
                { 0.9940573192285671, -0.1629764327645953 },
                { 0.9973456166001179, 0.8939276876604434 },
                { 0.5325497313632074, 0.9229697981342071 },
                { -0.9002039507523814, -0.9723702617699167 },
                { 0.33558208934378786, -0.35822945106042914 },
                { -0.3564519233978818, 0.8626884010298133 },
                { 0.9989121845744537, -0.988265655448676 },
                { 0.13222454078252177, 0.840139650443761 },
                { -0.9850814103109841, 0.9676466625663507 },

        };
        decoder.testInputs(latentes);
        decoder.printFile("TEST_Latente_deco.out");
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void test01AND() throws Exception {
        SimpleParser parser = new SimpleParser("./conf1AND.json", true);
        SimplePerceptron sp = new SimplePerceptron(parser.getParams());
        SimpleSolution s = sp.run();
        SimpleWriter w = new SimpleWriter(s, "test01AND-output-");
        // System.out.println(new Gson().toJson(parser));
    }

    @Test
    public void test01XOR() throws Exception {
        SimpleParser parser = new SimpleParser("./conf1XOR.json", true);
        SimplePerceptron sp = new SimplePerceptron(parser.getParams());
        SimpleSolution s = sp.run();
        SimpleWriter w = new SimpleWriter(s, "test01XOR-output-");
        // System.out.println(new Gson().toJson(parser));
    }
}
