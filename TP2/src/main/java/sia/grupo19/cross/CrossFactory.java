package sia.grupo19.cross;

import sia.grupo19.Params;
import sia.grupo19.selector.EliteSelector;
import sia.grupo19.selector.Selector;
import sia.grupo19.selector.StochasticSelector;
import sia.grupo19.selector.TruncatedSelector;

public class CrossFactory {
	
    public Cross getCross(Params params) {
        switch (params.getCrossType()) {
            case SIMPLE:
                return new SimpleCross(params.getIndividuoSize());
            case MULTIPLE:
               /* return new MultipleCross(params.getPopulationSize());
            case UNIFORM:
                return new UniformCross(params.getPopulationSize(), params.getTruncatedK());*/
            default:
                throw new Error("WHAT ARE YOU");
        }

    }
}
