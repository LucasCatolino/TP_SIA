package sia.grupo19.selector;

import sia.grupo19.Params;

public class SelectorFactory {

    public Selector getSelector(Params params) {
        switch (params.getSelectorType()) {
            case ELITE:
                return new EliteSelector(params.getPopulationSize());
            case STOCHASTIC:
                return new StochasticSelector(params.getPopulationSize());
            case TRUNCATED:
                return new TruncatedSelector(params.getPopulationSize(), params.getTruncatedK());
            default:
                throw new Error("WHAT ARE YOU");
        }

    }

}
