package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Parser {
    private Scanner inputScanner;
    private Config config;

    public Parser() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("Static100.txt");
        inputScanner = new Scanner(is);

        String token;

        while (inputScanner.hasNext()) {
            token = inputScanner.next();
            System.out.println(token);
        }

        inputScanner.close();

        System.out.println("this is a test!");
    }

    public Config getConfig() {
        return config;
    }
}
