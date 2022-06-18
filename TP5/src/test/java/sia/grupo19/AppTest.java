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
