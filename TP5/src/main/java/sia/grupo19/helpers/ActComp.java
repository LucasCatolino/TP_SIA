package sia.grupo19.helpers;

public class ActComp {
    private double activation;
    private double expected;

    public ActComp(double act, double exp) {
        activation = act;
        expected = exp;
    }

    public void setActivation(double activation) {
        this.activation = activation;
    }

    public double getActivation() {
        return this.activation;
    }

    public void setExpected(double expected) {
        this.expected = expected;
    }

    public double getExpected() {
        return this.expected;
    }
}