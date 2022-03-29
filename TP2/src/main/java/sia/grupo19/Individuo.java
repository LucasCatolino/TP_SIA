package sia.grupo19;

import java.util.Arrays;
import java.util.Random;

import com.google.gson.Gson;

public class Individuo implements Comparable<Individuo> {
    /*
     ** W = (W0, W1, W2) [1x3]
     ** w ==> S [2x3]
     ** w0 ==> R [1x2]
     ** X = (W, S, R) = (W0, W1, W2, w11, w12, w13, w21, w22, w23, w01, w02) [1x11]
     */
	private static final int SIZE= 11;
    private double[] X;
    private double fitness;
    private double[] F3 = new double[3];

    public Individuo(double[] newX) throws Error {
        if (newX.length != SIZE) {
            throw new Error("Invalid Individuo initialization");
        }
        X = newX;
        this.setFitness();
    }

    public Individuo(double[] W, double[] S, double[] R) {
        if (W.length != 3 || S.length != 6 || R.length != 2) {
            throw new Error("Invalid Individuo initialization");
        }
        X = joinThree(W, S, R);
        this.setFitness();
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
    
    public double[] getX() {
    	return X;
    }

    public String getSerialized() {
        return new Gson().toJson(this);
    }

    public void setFitness() {
        this.fitness = Utils.E(this);
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Individuo)) {
            return false;
        }

        Individuo other = (Individuo) o;
        return this.X.equals(other.X);
    }

    @Override
    public int compareTo(Individuo other) {
        return Double.compare(this.fitness, other.getFitness());
    }

    public void setF3() {
        this.F3[0] = Utils.F(getW(), getS(), getR(), Utils.samples[0].getXi());
        this.F3[1] = Utils.F(getW(), getS(), getR(), Utils.samples[1].getXi());
        this.F3[2] = Utils.F(getW(), getS(), getR(), Utils.samples[2].getXi());
    }

    public double[] getF3() {
        return this.F3;
    }

	public void mutate(double mutationProb, double deviation) {
		for (int i = 0; i < SIZE; i++) {
			if (Math.random() < mutationProb) {
				Random random= new Random();
				//x'i= xi + r, r~N(0, deviation)
				X[i]+= random.nextGaussian() * deviation;
			}
		}	
	}
}
