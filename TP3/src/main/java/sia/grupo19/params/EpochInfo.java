package sia.grupo19.params;

import com.google.gson.Gson;

public class EpochInfo {
    private double[] w;
    private double accuracy;
    private double error;

    public double[] getW() {
        return this.w;
    }

    public void setW(double[] w) {
        this.w = w;
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getError() {
        return this.error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public EpochInfo(double[] w, double acc, double err) {
        this.w = w;
        this.accuracy = acc;
        this.error = err;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
