package main;

import main.Solver.Solution;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("this is a test!");
        Parser parser = new Parser("./parameters/cfg.txt", true);
        Solver solver = new Solver(parser.getConfig());

        Solution outcome = solver.run();
        System.out.println(outcome);
        System.out.println(outcome.writeSolutionPath());
        Writer writer = new Writer(outcome, "output-");
        // outcome.printSolutionPath();
    }
}
