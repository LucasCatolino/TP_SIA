package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Config.SearchMethods;

public class Solver {
    public static class Solution {
        int sizeF;
        int sizeEx;
        int finalLimit;
        Config config;
        Tree.Node solutionNode;
        long elapsedTimeMillis;

        @Override
        public String toString() {
            return String.format("Solution[sizeF: %d, sizeEx: %d, finalLimit: %d, time: %s, %s, %s]", sizeF, sizeEx,
                    finalLimit, getReadableTime(elapsedTimeMillis), config, solutionNode);
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
        Solution outcome = runResolver();
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
                // TODO: add back when informed is done...
                /*
                 * case INFORMED:
                 * return informedResolver();
                 */
            default:
                return null;
        }
    }

    public Solution bppvResolver(Config config) {
        int lastLimit = config.limit;
        Config auxConfig = new Config(config);
        Solution bestOutcome = new Solution();
        int bestLimit = 0;
        Solution currOutcome = null;
        // maximum expanded nodes would be about 9!
        while (!(lastLimit > auxConfig.limit
                && (auxConfig.limit > 0
                        || bestOutcome.getSolutionNode() != null))) {
            currOutcome = unInformedResolver(new Config(auxConfig));
            lastLimit = auxConfig.limit;
            if (currOutcome.getSolutionNode() == null) {
                auxConfig.limit++; // this might be a nightmare given the case where it took over 50k nodes to find
                // a solution...
                // System.out.println("new limit is: " + auxConfig.limit);
            } else {
                bestOutcome = currOutcome;
                bestLimit = auxConfig.limit;
                auxConfig.limit--;
            }
        }
        bestOutcome.finalLimit = bestLimit;
        return bestOutcome;
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
        Frontera F = new Frontera(config.getMethod());
        F.add(A.getRoot());

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
            // Ordena la frontera seg�n el criterio de menor heuristica y se queda con el
            // primero
            F.sort();
            Tree.Node n = F.getFirst();

            // Si el nodo ya fue explorado, sigue explorando los otros de la frontera
            if (localHeurEx.containsKey(n.getTablero().getEstado())) {
                return searchLocalHeuristic(F);
            }

            // Si no fue explorado, lo explora
            localHeurEx.put(n.getTablero().getEstado(), n);

            // Si el nodo es la soluci�n la devuelve
            if (n.getTablero().goalReached()) {
                return n;
            }

            // Si el nodo no es la soluci�n, explora su frontera
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

        // En caso de error devuelve null
        return null;
    }
}
