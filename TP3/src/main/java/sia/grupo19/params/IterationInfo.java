package sia.grupo19.params;

import com.google.gson.Gson;

public class IterationInfo {
    double[] w;
    double error;

    public IterationInfo(double[] w, double error) {
        this.w = w;
        this.error = error;
    }

    public double[] getW() {
        return this.w;
    }

    public void setW(double[] w) {
        this.w = w;
    }

    public double getError() {
        return this.error;
    }

    public void setError(double error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
