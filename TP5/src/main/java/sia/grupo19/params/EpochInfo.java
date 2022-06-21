package sia.grupo19.params;

import com.google.gson.Gson;

public class EpochInfo {
    private double[] w;
    private double trainAccuracy;
    private double testAccuracy;

    private double error;

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

    public EpochInfo(double[] w, double trainAcc, double testAcc, double err) {
        this.w = w;
        this.trainAccuracy = trainAcc;
        this.testAccuracy = testAcc;
        this.error = err;
    }

    public double getTrainAccuracy() {
        return this.trainAccuracy;
    }

    public void setTrainAccuracy(double trainAccuracy) {
        this.trainAccuracy = trainAccuracy;
    }

    public double getTestAccuracy() {
        return this.testAccuracy;
    }

    public void setTestAccuracy(double testAccuracy) {
        this.testAccuracy = testAccuracy;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
