package main;

public class Utils {
    private static Sample[] samples = { new Sample(4.4793, -4.0765, -4.0765, 0),
            new Sample(-4.1793, -4.9218, 1.7664, 1),
            new Sample(-3.9429, -0.7689, 4.8830, 1) };

    public static double E(Individuo X) {
        double out = 0;
        for (int i = 0; i < 3; i++) {
            out += Math.pow((samples[i].getZeta() - F(X.getW(), X.getS(), X.getR(), samples[i].getXi())), 2);
        }

        return out;
    }

    public static double F(double[] W, double[] S, double[] R, double[] xi) {
        double wd = 0;
        double sd;

        for (int j = 1; j < 3; j++) {
            sd = 0;
            for (int k = 0; k < 3; k++) {
                sd += S[(j - 1) * 3 + k] * xi[k] - R[j - 1];
            }
            wd += (W[j] * g(sd)) - W[0];
        }

        return g(wd);
    }

    public static double g(double x) {
        return Math.exp(x) / (1 + Math.exp(x));
    }

    public static class Sample {
        double[] inputs;
        int output;

        public Sample(double[] in, int out) {
            inputs = in;
            output = out;
        }

        public Sample(double Xi0, double Xi1, double Xi2, int zeta) {
            inputs = new double[3];
            inputs[0] = Xi0;
            inputs[1] = Xi1;
            inputs[2] = Xi2;

            output = zeta;
        }

        public double[] getXi() {
            return inputs;
        }

        public int getZeta() {
            return output;
        }
    }
}
