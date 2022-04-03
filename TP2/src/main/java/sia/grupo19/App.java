package sia.grupo19;

import sia.grupo19.io.Parser;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        Parser parser = new Parser("./cfg.txt", true);
        System.out.println(parser.getParams());
        System.out.println("Hello World!");
    }
}
