package main;

public class Config {

    public Config() {

    }

    public Config(Config config) {
        this.puzzle = config.puzzle;
        this.strategy = config.strategy;
        this.method = config.method;
        this.hSelected = config.hSelected;
        this.limit = config.limit;
    }

    public Config(String puzzle, StrategyTypes strat, SearchMethods method, Heuristics h, int limit) {
        this.puzzle = puzzle;
        this.strategy = strat;
        this.method = method;
        this.hSelected = h;
        this.limit = limit;
    }

    public enum StrategyTypes {
        UNINFORMED,
        INFORMED,
    }

    public enum SearchMethods {
        BPA,
        BPP,
        BPPV,
        HEURLOCAL,
        HEURGLOBAL,
        ASTAR,
    }

    public enum Heuristics {
        EUCLIDEAN,
        MANHATTAN,
        INVALID,
    }

    String puzzle;
    StrategyTypes strategy;
    SearchMethods method;
    int limit;
    Heuristics hSelected;

    public StrategyTypes parseStrategy(String token) throws Error {
        if (token.toLowerCase().compareTo("uninformed") == 0) {
            return StrategyTypes.UNINFORMED;
        }
        if (token.toLowerCase().compareTo("informed") == 0) {
            return StrategyTypes.INFORMED;
        }

        throw new Error("Invalid strategy in configuration file");
    }

    public SearchMethods parseMethod(String token) throws Error {
        if (token.toLowerCase().compareTo("bpa") == 0) {
            return SearchMethods.BPA;
        }
        if (token.toLowerCase().compareTo("bpp") == 0) {
            return SearchMethods.BPP;
        }
        if (token.toLowerCase().compareTo("bppv") == 0) {
            return SearchMethods.BPPV;
        }
        if (token.toLowerCase().compareTo("hl") == 0) {
            return SearchMethods.HEURLOCAL;
        }
        if (token.toLowerCase().compareTo("hg") == 0) {
            return SearchMethods.HEURGLOBAL;
        }
        if (token.toLowerCase().compareTo("astar") == 0) {
            return SearchMethods.ASTAR;
        }

        throw new Error("Invalid search method in configuration file");
    }

    public Heuristics parseHeuristic(String token) throws Error {
        if (token.toLowerCase().compareTo("euclidean") == 0) {
            return Heuristics.EUCLIDEAN;
        }
        if (token.toLowerCase().compareTo("manhattan") == 0) {
            return Heuristics.MANHATTAN;
        }
        if (token.toLowerCase().compareTo("invalid") == 0) {
            return Heuristics.INVALID;
        }

        throw new Error("Invalid strategy in configuration file");
    }

    public String getPuzzle() {
        return this.puzzle;
    }

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
    }

    public StrategyTypes getStrategy() {
        return this.strategy;
    }

    public void setStrategy(StrategyTypes strategy) {
        this.strategy = strategy;
    }

    public SearchMethods getMethod() {
        return this.method;
    }

    public void setMethod(SearchMethods method) {
        this.method = method;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Heuristics getHSelected() {
        return this.hSelected;
    }

    public void setHSelected(Heuristics hSelected) {
        this.hSelected = hSelected;
    }

}
