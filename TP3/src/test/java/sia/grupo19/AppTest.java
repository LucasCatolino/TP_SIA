package sia.grupo19;

import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;

import org.junit.Test;

import sia.grupo19.params.SimpleParser;
import sia.grupo19.params.SimpleSolution;
import sia.grupo19.params.SimpleWriter;

/**
 * Unit test for simple App.
 */
public class AppTest {
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

    @Test
    public void test02Linear() throws Exception {
        SimpleParser parser = new SimpleParser("./conf2Linear.json", true);
        SimplePerceptron sp = new SimplePerceptron(parser.getParams());
        SimpleSolution s = sp.run();
        SimpleWriter w = new SimpleWriter(s, "test02Linear-output-");
        // System.out.println(new Gson().toJson(parser));
    }

    @Test
    public void test02NonLinear() throws Exception {
        SimpleParser parser = new SimpleParser("./conf2NonLinear.json", true);
        SimplePerceptron sp = new SimplePerceptron(parser.getParams());
        SimpleSolution s = sp.run();
        SimpleWriter w = new SimpleWriter(s, "test02NonLinear-output-");
        // System.out.println(new Gson().toJson(parser));
    }
}
