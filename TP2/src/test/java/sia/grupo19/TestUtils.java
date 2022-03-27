package sia.grupo19;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import sia.grupo19.Individuo;
import sia.grupo19.Utils;

public class TestUtils {
    double[] W = { 4.4793, -4.0765, -4.0765 };
    double[] S = { 1, 0, 1, 0.5, -0.1, 0.5 };
    double[] R = { 0.1, 0.7 };

    @Test
    public void test01() {
        Individuo i = new Individuo(W, S, R);
        System.out.println(Utils.E(i));
    }

}
