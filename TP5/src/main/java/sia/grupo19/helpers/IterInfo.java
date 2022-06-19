package sia.grupo19.helpers;

import java.util.ArrayList;
import java.util.List;

public class IterInfo {

    private List<ActComp> actComps;
    private double error;

    public IterInfo() {
        actComps = new ArrayList<>();
    }

    public void addActComp(ActComp actComp) {
        actComps.add(actComp);
    }

    public List<ActComp> getActComps() {
        return this.actComps;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getError() {
        return this.error;
    }

}
