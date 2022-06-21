package sia.grupo19.params;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.google.gson.Gson;

public class SimpleParser {

    private Scanner inputScanner;
    private SimpleParams params;

    public SimpleParser(String path) {
        InputStream is = SimpleParser.class.getResourceAsStream(path);
        if (is == null) {
            throw new Error("Config file not found!");
        }
        inputScanner = new Scanner(is);

        configParser(inputScanner);

        inputScanner.close();
    }

    public SimpleParser(String path, boolean prod) throws IOException {
        params = new Gson().fromJson(new FileReader(path), SimpleParams.class);
        /*
         * FileInputStream file = new FileInputStream(path);
         * inputScanner = new Scanner(file);
         * configParser(inputScanner);
         * inputScanner.close();
         * file.close();
         */
    }

    public SimpleParams getParams() {
        return this.params;
    }

    private void configParser(Scanner iScanner) throws Error {
        params = new SimpleParams();
    }

}
