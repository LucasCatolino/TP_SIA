package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Config.SearchMethods;
import main.Tree.Node;

public class Solver {
    public static class Solution {
        int sizeF;
        int sizeEx;
        int finalLimit;
        Config config;
        Tree.Node solutionNode;
        long elapsedTimeMillis;

        public void printSolutionPath() {
            if (this.solutionNode == null) {
                System.out.println("No solution was found");
            } else {
                this.solutionNode.printPath();
            }

        }

        public String writeSolutionPath() {
            if (this.solutionNode == null) {
                return "No solution was found";
            } else {
                return this.solutionNode.writePath();
            }

        }

        @Override
        public String toString() {
            return String.format(
                    "Solution: [size of F: %d, size of Ex: %d, finalLimit: %d, time: %s, time in millis: %d, %n%s, %n%s%n]",
                    sizeF,
                    sizeEx,
                    finalLimit, getReadableTime(elapsedTimeMillis), elapsedTimeMillis, config, solutionNode);
        }

        public int getFinalLimit() {
            return finalLimit;
        }

        public void setFinalLimit(int newLimit) {
            finalLimit = newLimit;
        }

        public int getSizeFrontera() {
            return sizeF;
        }

        public int getSizeExpandidos() {
            return sizeEx;
        }

        public Config getConfig() {
            return config;
        }

        public Tree.Node getSolutionNode() {
            return solutionNode;
        }

        public long getTimeMillis() {
            return elapsedTimeMillis;
        }

        public String getReadableTime(long time) {
            SimpleDateFormat format = new SimpleDateFormat("mm:ss.SSS");
            return format.format(new Date(time));
        }
    }

    private Config config;
    private Map<String, Tree.Node> localHeurEx = new HashMap<>();

    public Solver() {
        // demo mode: run with all search methods ?
    }

    public Solver(Config config) {
        this.config = config;
    }

    public Solution run() {
        long startTime = System.currentTimeMillis();
        Tablero solvabilityCheck = new Tablero(config.getPuzzle());
        Solution outcome;
        if (solvabilityCheck.isSolvable()) {
            outcome = runResolver();
        } else {
            outcome = new Solution();
            outcome.config = config;
        }
        long stopTime = System.currentTimeMillis();
        outcome.elapsedTimeMillis = stopTime - startTime;

        return outcome;
    }

    public Solution runResolver() {
        switch (config.strategy) {
            case UNINFORMED:
                if (config.method != SearchMethods.BPPV) {
                    return unInformedResolver(config);
                } else {
                    return bppvResolver(config);
                }
            case INFORMED:
                if (config.method != SearchMethods.HEURLOCAL) {
                    return informedResolver(config);
                } else {
                    return localHeuristicResolver(config);
                }
            default:
                return null;
        }
    }

    public Solution bppvResolver(Config config) {
        Solution baseSolution = unInformedResolver(new Config(config));

        Solution bestSolution = baseSolution;
        Solution currentSolution = null;
        int foundLimit = -1;

        if (baseSolution.getSolutionNode() != null) {
            for (int i = config.getLimit() - 1; i >= 0; i--) {
                Config currentConfig = new Config(config);
                currentConfig.setLimit(i);

                currentSolution = unInformedResolver(currentConfig);
                if (currentSolution.getSolutionNode() != null) {
                    bestSolution = currentSolution;
                    foundLimit = i;
                }
            }
        } else {

            for (int i = config.limit + 1; i < Integer.MAX_VALUE; i++) {

                Config currentConfig = new Config(config);
                currentConfig.setLimit(i);

                currentSolution = unInformedResolver(currentConfig);
                if (currentSolution.getSolutionNode() != null) {
                    bestSolution = currentSolution;
                    foundLimit = i;
                    break;
                }
            }
        }
        if (bestSolution.getSolutionNode() != null) {
            bestSolution.config.limit = config.limit;
            bestSolution.finalLimit = foundLimit;
        }
        return bestSolution;
    }

    public Solution unInformedResolver(Config config) {

        Tree A = new Tree(config.getPuzzle());
        Frontera F = new Frontera(config.getMethod());
        Map<String, Tree.Node> Ex = new HashMap<>();
        F.add(A.getRoot());

        Tree.Node solutionNode = null;

        while (!F.isEmpty()) {
            Tree.Node n = F.getFirst();
            /*
             * System.out.printf("running board with: %s at depth: %d\n",
             * n.getTablero().getEstado(), n.getDepth());
             */

            if (!Ex.containsKey(n.getTablero().getEstado())) {
                Ex.put(n.getTablero().getEstado(), n);
            }
            // n.printTablero();
            if (n.goalReached()) {
                // System.out.println("goal reached!");
                solutionNode = n;
                break;
            }

            // expand n & add children to A and F if they are not in Ex
            List<String> posibleSuccessors = n.getTablero().getRotaciones();
            for (String successor : posibleSuccessors) {
                if (!Ex.containsKey(successor)
                        && (config.method == SearchMethods.BPPV ? n.getDepth() + 1 <= config.limit : true)) {
                    Tree.Node child = new Tree.Node(successor, n.getDepth() + 1);
                    child.setParent(n);
                    n.addChild(child);
                    F.add(child);
                }
            }
            F.sort();
        }

        Solution outcome = new Solution();
        outcome.config = config;
        outcome.sizeEx = Ex.size();
        outcome.sizeF = F.getSize();
        outcome.solutionNode = solutionNode;

        return outcome;
    }

    public Solution informedResolver(Config config) {

        Tree A = new Tree(config.getPuzzle());
        Frontera F = new Frontera(config.getMethod());
        Map<String, Tree.Node> Ex = new HashMap<>();
        F.add(A.getRoot());

        Tree.Node solutionNode = null;

        while (!F.isEmpty()) {
            Tree.Node n = F.getFirst();
            // System.out.printf("running board with: %s at depth: %d\n",
            // n.getTablero().getEstado(), n.getDepth());

            if (!Ex.containsKey(n.getTablero().getEstado())) {
                Ex.put(n.getTablero().getEstado(), n);
            }
            // n.printTablero();
            if (n.goalReached()) {
                // System.out.println("goal reached!");
                solutionNode = n;
                break;
            }

            // expand n & add children to A and F if they are not in Ex
            List<String> posibleSuccessors = n.getTablero().getRotaciones();
            for (String successor : posibleSuccessors) {
                if (!Ex.containsKey(successor)) {
                    Tree.Node child = new Tree.Node(successor, n.getDepth() + 1);
                    child.setHeuristic(config.getHSelected());
                    child.setHeuristicCost();
                    child.setParent(n);
                    n.addChild(child);
                    F.add(child);
                }
            }
            F.sort();
        }

        Solution outcome = new Solution();
        outcome.config = config;
        outcome.sizeEx = Ex.size();
        outcome.sizeF = F.getSize();
        outcome.solutionNode = solutionNode;

        return outcome;
    }

    public Solution localHeuristicResolver(Config config) {
        Tree A = new Tree(config.getPuzzle());
        Node node= A.getRoot();
        node.setHeuristic(config.hSelected);
        node.setHeuristicCost();
        Frontera F = new Frontera(config.getMethod());
        F.add(node);

        Tree.Node solutionNode = searchLocalHeuristic(F);

        Solution outcome = new Solution();
        outcome.config = config;
        outcome.sizeEx = localHeurEx.size();
        outcome.sizeF = F.getSize();
        outcome.solutionNode = solutionNode;

        return outcome;
    }

    private Tree.Node searchLocalHeuristic(Frontera F) {

        if (!F.isEmpty()) {
            // Ordena la frontera segun el criterio de menor heuristica y se queda con el
            // primero
            F.sort();
            Tree.Node n = F.getFirst();

            // Si el nodo ya fue explorado, sigue explorando los otros de la frontera
            if (localHeurEx.containsKey(n.getTablero().getEstado())) {
                return searchLocalHeuristic(F);
            }

            // Si no fue explorado, lo explora
            localHeurEx.put(n.getTablero().getEstado(), n);

            // Si el nodo es la solucion la devuelve
            if (n.getTablero().goalReached()) {
                return n;
            }

            // Si el nodo no es la solucion, explora su frontera
            Frontera newF = new Frontera(config.getMethod());
            List<String> posibleSuccessors = n.getTablero().getRotaciones();
            for (String successor : posibleSuccessors) {
                if (!localHeurEx.containsKey(successor)) {
                    Tree.Node child = new Tree.Node(successor, n.getDepth() + 1);
                    child.setHeuristic(config.getHSelected());
                    child.setHeuristicCost();
                    child.setParent(n);
                    n.addChild(child);
                    newF.add(child);
                }
            }
            return searchLocalHeuristic(newF);
        }

        // En caso de toda la frontera explorada devuelve null
        return null;
    }
}
