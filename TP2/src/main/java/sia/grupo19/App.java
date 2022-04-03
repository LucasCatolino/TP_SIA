package sia.grupo19;

import sia.grupo19.io.Parser;
import sia.grupo19.io.Solution;
import sia.grupo19.io.Writer;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        Parser parser = new Parser("./cfg.txt", true);
        Genetic geneticSolver = new Genetic(parser.getParams());

        Solution outcome = geneticSolver.runEvolution();
        System.out.println(outcome);
        Writer writer = new Writer(outcome, "output-");
        // System.out.println(parser.getParams());
        // System.out.println("Hello World!");
    }
}
