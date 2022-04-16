package sia.grupo19;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
    public static class TestClass {
        private int numero = 1;
        private String str = " pepe";
    }

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new TestClass()));
    }
}
