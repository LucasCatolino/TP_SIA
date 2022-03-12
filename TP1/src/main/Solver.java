package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Config.StrategyTypes;

public class Solver {
    private Config config;

    public Solver() {
        // demo mode: run with all search methods ?
    }

    public Solver(Config config) {
        this.config = config;
    }

    public Tree.Node Run() {
        switch (config.strategy) {
            case UNINFORMED:
                return unInformedResolver(config);
            // TODO: add back when informed is done...
            /*
             * case INFORMED:
             * return informedResolver();
             */
            default:
                return null;
        }
    }

    public Tree.Node unInformedResolver(Config config) {
        Tree A = new Tree(config.getPuzzle());
        Frontera F = new Frontera(config.getMethod());
        Map<String, Tree.Node> Ex = new HashMap<>();
        F.add(A.getRoot());
        while (!F.isEmpty()) {
            Tree.Node n = F.getFirst();
            System.out.printf("running board with: %s at depth: %d\n", n.getTablero().getEstado(), n.getDepth());

            if (!Ex.containsKey(n.getTablero().getEstado())) {
                Ex.put(n.getTablero().getEstado(), n);
            }

            if (n.goalReached()) {
                System.out.println("goal reached!");
                return n;
            }

            // expand n & add children to A and F if they are not in Ex
            List<String> posibleSuccessors = n.getTablero().getRotaciones();
            for (String successor : posibleSuccessors) {
                if (!Ex.containsKey(successor)) {
                    Tree.Node child = new Tree.Node(successor, n.getDepth() + 1);
                    n.addChild(child);
                    F.add(child);
                }
            }
            F.sort();
        }

        return null;
    }

    public void informedResolver() {

    }
}
