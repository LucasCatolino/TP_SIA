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

    // TESTEAR LINEAR CON 2*X Y NON-LINEAR CON X**2

    @Test
    public void test02Linear2X() throws Exception {
        SimpleParser parser = new SimpleParser("./conf2Linear2X.json", true);
        SimplePerceptron sp = new SimplePerceptron(parser.getParams());
        SimpleSolution s = sp.run();
        SimpleWriter w = new SimpleWriter(s, "test02Linear2X-output-");
        // System.out.println(new Gson().toJson(parser));
    }

    @Test
    public void test02Linear2XPlus90() throws Exception {
        SimpleParser parser = new SimpleParser("./conf2Linear2X+90.json", true);
        SimplePerceptron sp = new SimplePerceptron(parser.getParams());
        SimpleSolution s = sp.run();
        SimpleWriter w = new SimpleWriter(s, "test02Linear2X+90-output-");
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

    // its not meant to do a perfect aproximation, but get kinda close (single
    // digits ideally)
    @Test
    public void test02NonLinearXtoThe2() throws Exception {
        SimpleParser parser = new SimpleParser("./conf2NonLinearXtothe2.json", true);
        SimplePerceptron sp = new SimplePerceptron(parser.getParams());
        SimpleSolution s = sp.run();
        SimpleWriter w = new SimpleWriter(s, "test02NonLinearXtothe2-output-");
        // System.out.println(new Gson().toJson(parser));
    }

    @Test
    public void test02CrossValidatorSubdivisions() throws Exception {
        SimpleParser parser = new SimpleParser("./conf2NonLinearXtothe2.json", true);
        SimpleCrossValidator scv = new SimpleCrossValidator(parser.getParams());

        // System.out.println(new Gson().toJson(parser));
    }
}
