package sia.grupo19.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import sia.grupo19.Params;

public class Parser {

    private Scanner inputScanner;
    private Params params;

    public enum State {
        READING,
        DUMP,
        POPULATION,
        // selector substates
        SELECTOR_TYPE,
        SELECTOR_TRUNCATED_K,
        SELECTOR_BOLTZMAN_TC,
        SELECTOR_BOLTZMAN_T0,
        SELECTOR_BOLTZMAN_K,
        // mutation substates
        MUTATION_PROB,
        MUTATION_DEVIATION,
        // cross substates
        CROSS_TYPE,
        CROSS_MULTIPLE_K_CUTS,
        // cutoff substates
        CUTOFF_MAX_GEN,
        CUTOFF_MIN_ACCEPTABLE,
        CUTOFF_MAX_REP_STRUCT,
        CUTOFF_MAX_REP_CONTENT,
    };

    public Parser(String path) {
        InputStream is = Parser.class.getResourceAsStream(path);
        if (is == null) {
            throw new Error("Config file not found!");
        }
        inputScanner = new Scanner(is);

        configParser(inputScanner);

        inputScanner.close();
    }

    public Parser(String path, boolean prod) throws IOException {
        FileInputStream file = new FileInputStream(path);
        inputScanner = new Scanner(file);
        configParser(inputScanner);
        inputScanner.close();
        file.close();
    }

    public Params getParams() {
        return this.params;
    }

    private void configParser(Scanner iScanner) throws Error {
        params = new Params();

        String token;
        State state = State.READING;

        while (iScanner.hasNext()) {
            token = iScanner.next().toLowerCase();

            if (state == State.READING) {
                String cleanToken = token.split(":")[0];
                state = selectMode(cleanToken);
            } else {
                parseParam(state, token, params);
                state = State.READING;
            }
        }
    }

    private void parseParam(State state, String token, Params params) {
        switch (state) {
            case POPULATION:
                params.setPopulationSize(Integer.parseInt(token));
                break;
            case SELECTOR_TYPE:
                params.setSelectorType(params.parseSelector(token));
                break;
            case SELECTOR_TRUNCATED_K:
                params.setTruncatedK(Integer.parseInt(token));
                break;
            case SELECTOR_BOLTZMAN_T0:
                params.setBoltzmanT0(Double.parseDouble(token));
                break;
            case SELECTOR_BOLTZMAN_TC:
                params.setBoltzmanTc(Double.parseDouble(token));
                break;
            case SELECTOR_BOLTZMAN_K:
                params.setBoltzmanK(Double.parseDouble(token));
                break;
            case MUTATION_PROB:
                params.setMutationProb(Double.parseDouble(token));
                break;
            case MUTATION_DEVIATION:
                params.setMutationDeviation(Double.parseDouble(token));
                break;
            case CROSS_TYPE:
                params.setCrossType(params.parseCross(token));
                break;
            case CROSS_MULTIPLE_K_CUTS:
                params.setKCuts(Integer.parseInt(token));
                break;
            case CUTOFF_MAX_GEN:
                params.setCutOffMaxGen(Integer.parseInt(token));
                break;
            case CUTOFF_MIN_ACCEPTABLE:
                params.setCutOffMinAcceptable(Double.parseDouble(token));
                break;
            case CUTOFF_MAX_REP_CONTENT:
                params.setMaxRepeatedContent(Integer.parseInt(token));
                break;
            case CUTOFF_MAX_REP_STRUCT:
                params.setMaxRepeatedStruct(Integer.parseInt(token));
                break;
            case DUMP:
                params.setPrintHistory(Boolean.parseBoolean(token));
                break;
            default:
                throw new Error("Internal Parsing Error");
        }
    }

    private State selectMode(String token) {
        String[] tokenParts = token.split("\\.");
        String[] others = Arrays.copyOfRange(tokenParts, 1, tokenParts.length);
        switch (tokenParts[0]) {
            case "population":
                return State.POPULATION;
            case "mutation":
                return mutationState(others);
            case "selector":
                return selectorState(others);
            case "cross":
                return crossState(others);
            case "cutoff":
                return cutoffState(others);
            case "dump":
                return State.DUMP;
            default:
                return State.READING;
        }
    }

    private State cutoffState(String[] others) {
        switch (others[0]) {
            case "maxgen":
                return State.CUTOFF_MAX_GEN;
            case "minacceptable":
                return State.CUTOFF_MIN_ACCEPTABLE;
            case "maxrepstruct":
                return State.CUTOFF_MAX_REP_STRUCT;
            case "maxrepcontent":
                return State.CUTOFF_MAX_REP_CONTENT;
            default:
                throw new Error("Invalid Cutoff param");
        }
    }

    private State crossState(String[] others) {
        switch (others[0]) {
            case "type":
                return State.CROSS_TYPE;
            case "multiple":
                if (others[1].compareTo("k") == 0) {
                    return State.CROSS_MULTIPLE_K_CUTS;
                }
            default:
                throw new Error("Invalid Cross param " + others);
        }
    }

    private State selectorState(String[] others) {
        if (others[0].compareTo("type") == 0) {
            return State.SELECTOR_TYPE;
        } else if (others[0].compareTo("truncated") == 0 && others[1].compareTo("k") == 0) {
            return State.SELECTOR_TRUNCATED_K;
        } else if (others[0].compareTo("boltzman") == 0) {
            switch (others[1]) {
                case "t0":
                    return State.SELECTOR_BOLTZMAN_T0;
                case "tc":
                    return State.SELECTOR_BOLTZMAN_TC;
                case "k":
                    return State.SELECTOR_BOLTZMAN_K;
                default:
                    throw new Error("Invalid param with boltzman");
            }
        }

        throw new Error("Invalid Selector param");
    }

    private State mutationState(String[] others) {
        switch (others[0]) {
            case "probability":
                return State.MUTATION_PROB;
            case "deviation":
                return State.MUTATION_DEVIATION;
            default:
                throw new Error("Invalid Mutation param");
        }
    }
}
