package sia.grupo19;

import java.util.Arrays;

import com.google.gson.Gson;

public class Individuo {
    /*
     ** W = (W0, W1, W2) [1x3]
     ** w ==> S [2x3]
     ** w0 ==> R [1x2]
     ** X = (W, S, R) = (W0, W1, W2, w11, w12, w13, w21, w22, w23, w01, w02) [1x11]
     */

    private double[] X;

    public Individuo(double[] newX) throws Error {
        if (newX.length != 11) {
            throw new Error("Invalid Individuo initialization");
        }
        X = newX;
    }

    public Individuo(double[] W, double[] S, double[] R) {
        if (W.length != 3 || S.length != 6 || R.length != 2) {
            throw new Error("Invalid Individuo initialization");
        }
        X = joinThree(W, S, R);
    }

    // horrible, i miss javascript
    private double[] joinThree(double[] W, double[] S, double[] R) {
        double[] outcome = new double[11];
        int i;
        for (i = 0; i < W.length; i++) {
            outcome[i] = W[i];
        }
        for (; i < W.length + S.length; i++) {
            outcome[i] = S[i - W.length];
        }
        for (; i < W.length + S.length + R.length; i++) {
            outcome[i] = R[i - (W.length + S.length)];
        }

        return outcome;
    }

    public double[] getW() {
        return Arrays.copyOfRange(X, 0, 3);
    }

    public double[] getS() {
        return Arrays.copyOfRange(X, 3, 9);
    }

    public double[] getR() {
        return Arrays.copyOfRange(X, 9, 11);
    }

    public String getSerialized() {
        return new Gson().toJson(this);
    }
}
