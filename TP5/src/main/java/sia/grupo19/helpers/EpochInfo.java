package sia.grupo19.helpers;

import java.util.ArrayList;
import java.util.List;

public class EpochInfo {
    private transient List<IterInfo> itersInfo;
    private double error;

    public void setItersInfo(List<IterInfo> itersInfo) {
        this.itersInfo = itersInfo;
    }

    public double getError() {
        return this.error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public EpochInfo(List<IterInfo> iterInfos, double err) {
        this.itersInfo = iterInfos;
        this.error = err;
    }

    public EpochInfo() {
        itersInfo = new ArrayList<>();
    }

    public void addItersInfo(IterInfo iterInfo) {
        itersInfo.add(iterInfo);
    }

    public List<IterInfo> getItersInfo() {
        return this.itersInfo;
    }
}
