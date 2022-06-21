package sia.grupo19;

import sia.grupo19.params.SimpleParser;
import sia.grupo19.params.SimpleSolution;
import sia.grupo19.params.SimpleWriter;

public class SimplePerceptronApp {

    public static void main(String[] args) throws Exception {
        SimpleParser parser = new SimpleParser("./conf.json", true);
        SimplePerceptron sp = new SimplePerceptron(parser.getParams());
        SimpleSolution s = sp.run();
        SimpleWriter w = new SimpleWriter(s, "output-");
    }
}
