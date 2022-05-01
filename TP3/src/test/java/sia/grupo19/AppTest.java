package sia.grupo19;

import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;

import org.junit.Test;

import sia.grupo19.params.SimpleParser;

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
        sp.run();
        System.out.println(new Gson().toJson(parser));
    }

    @Test
    public void test01XOR() throws Exception {
        SimpleParser parser = new SimpleParser("./conf1XOR.json", true);
        SimplePerceptron sp = new SimplePerceptron(parser.getParams());
        sp.run();
        System.out.println(new Gson().toJson(parser));
    }
}
