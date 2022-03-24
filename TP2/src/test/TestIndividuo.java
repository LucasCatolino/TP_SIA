package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import main.Individuo;

public class TestIndividuo {
    double[] X = { 4.4793, -4.0765, -4.0765, 1, 0, 1, 0.5, -0.1, 0.5, 0.1, 0.7 };

    double[] W = { 4.4793, -4.0765, -4.0765 };
    double[] S = { 1, 0, 1, 0.5, -0.1, 0.5 };
    double[] R = { 0.1, 0.7 };

    @Test
    public void test01() {
        Individuo i = new Individuo(W, S, R);
        assertTrue(Arrays.equals(i.getW(), W));
        assertTrue(Arrays.equals(i.getS(), S));
        assertTrue(Arrays.equals(i.getR(), R));
    }

    @Test
    public void test02() {
        Individuo i = new Individuo(X);
        assertTrue(Arrays.equals(i.getW(), W));
        assertTrue(Arrays.equals(i.getS(), S));
        assertTrue(Arrays.equals(i.getR(), R));
    }

}
