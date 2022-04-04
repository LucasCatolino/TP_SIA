package sia.grupo19.informe;

import org.junit.Test;

import sia.grupo19.Genetic;
import sia.grupo19.Params;
import sia.grupo19.Params.CrossType;
import sia.grupo19.Params.SelectorType;
import sia.grupo19.io.Parser;
import sia.grupo19.io.Solution;
import sia.grupo19.io.Writer;

public class TestRunnerMultiple3 {

    String run = "RUN01";

    @Test
    public void test01() throws Exception {
        // ELITE
        Parser parser = new Parser("./test-configs-m3/test01.txt", true);
        Genetic genetic = new Genetic(parser.getParams());
        Solution solution = genetic.runEvolution();
        System.out.println(solution.getOptimalIndividuo().getSerialized());

        Writer writer = new Writer(solution, "test01-" + run + "-output-");
    }

    @Test
    public void test02() throws Exception {
        // STOCHASTIC
        Parser parser = new Parser("./test-configs-m3/test02.txt", true);
        Genetic genetic = new Genetic(parser.getParams());
        Solution solution = genetic.runEvolution();
        System.out.println(solution.getOptimalIndividuo().getSerialized());

        Writer writer = new Writer(solution, "test02-" + run + "-output-");
    }

    @Test
    public void test03() throws Exception {
        // TRUNCATED
        Parser parser = new Parser("./test-configs-m3/test03.txt", true);
        Genetic genetic = new Genetic(parser.getParams());
        Solution solution = genetic.runEvolution();
        System.out.println(solution.getOptimalIndividuo().getSerialized());

        Writer writer = new Writer(solution, "test03-" + run + "-output-");
    }

    @Test
    public void test04() throws Exception {
        // ROULETTE
        Parser parser = new Parser("./test-configs-m3/test04.txt", true);
        Genetic genetic = new Genetic(parser.getParams());
        Solution solution = genetic.runEvolution();
        System.out.println(solution.getOptimalIndividuo().getSerialized());

        Writer writer = new Writer(solution, "test04-" + run + "-output-");
    }

    @Test
    public void test05() throws Exception {
        // RANK
        Parser parser = new Parser("./test-configs-m3/test05.txt", true);
        Genetic genetic = new Genetic(parser.getParams());
        Solution solution = genetic.runEvolution();
        System.out.println(solution.getOptimalIndividuo().getSerialized());

        Writer writer = new Writer(solution, "test05-" + run + "-output-");
    }

    @Test
    public void test06() throws Exception {
        // TOURNAMENT
        Parser parser = new Parser("./test-configs-m3/test06.txt", true);
        Genetic genetic = new Genetic(parser.getParams());
        Solution solution = genetic.runEvolution();
        System.out.println(solution.getOptimalIndividuo().getSerialized());

        Writer writer = new Writer(solution, "test06-" + run + "-output-");
    }

    @Test
    public void test07() throws Exception {
        // BOLTZMAN
        Parser parser = new Parser("./test-configs-m3/test07.txt", true);
        Genetic genetic = new Genetic(parser.getParams());
        Solution solution = genetic.runEvolution();
        System.out.println(solution.getOptimalIndividuo().getSerialized());

        Writer writer = new Writer(solution, "test07-" + run + "-output-");
    }

}
