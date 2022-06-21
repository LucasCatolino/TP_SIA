package sia.grupo19;

import java.util.List;

import sia.grupo19.params.SimpleParser;
import sia.grupo19.params.SimpleSolution;
import sia.grupo19.params.SimpleWriter;

public class SimpleCrossValidatorApp {

    public static void main(String[] args) throws Exception {
        SimpleParser parser = new SimpleParser("./conf.json", true);
        SimpleCrossValidator scv = new SimpleCrossValidator(parser.getParams());
        List<SimpleSolution> sols = scv.run();
        SimpleWriter w = new SimpleWriter(sols, "output");
    }
}
