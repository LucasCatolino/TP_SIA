package main;

import java.io.InputStream;
import java.util.Scanner;

public class Parser {
    private Scanner inputScanner;
    private Config config;

    public enum State {
        READING,
        PUZZLE,
        STRATEGY,
        METHOD,
        LIMIT,
        HEUR,
    };

    public Parser(String path) {
        // path used on build should be "/parameters/cfg.txt"
        // for tests: "/test/resources/config.txt"
        InputStream is = Parser.class.getResourceAsStream(path);
        if (is == null) {
            throw new Error("Configuration file not found!");
        }
        inputScanner = new Scanner(is);

        config = new Config();

        String token;
        State state = State.READING;

        while (inputScanner.hasNext()) {
            token = inputScanner.next();
            System.out.println(token);

            if (state == State.READING) {
                state = selectMode(token);
            } else {
                switch (state) {
                    case PUZZLE:
                        config.puzzle = token;
                        break;
                    case STRATEGY:
                        config.strategy = config.parseStrategy(token);
                        break;
                    case METHOD:
                        config.method = config.parseMethod(token);
                        break;
                    case LIMIT:
                        config.limit = Integer.parseInt(token);
                        break;
                    case HEUR:
                        config.hSelected = config.parseHeuristic(token);
                        break;
                    default:
                        throw new Error("Out of bounds state");
                }

                state = State.READING;

            }

        }

        inputScanner.close();

        System.out.println("this is a test!");
    }

    private State selectMode(String token) {
        switch (token) {
            case "puzzle:":
                return State.PUZZLE;
            case "strategy:":
                return State.STRATEGY;
            case "method:":
                return State.METHOD;
            case "limit:":
                return State.LIMIT;
            case "heur:":
                return State.HEUR;
            default:
                return State.READING;
        }
    }

    public Config getConfig() {
        return config;
    }
}
