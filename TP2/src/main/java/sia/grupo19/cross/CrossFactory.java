package sia.grupo19.cross;

import sia.grupo19.Params;

public class CrossFactory {
	
    public Cross getCross(Params params) {
        switch (params.getCrossType()) {
            case SIMPLE:
                return new SimpleCross(params.getIndividuoSize());
            case MULTIPLE:
               return new MultipleCross(params.getIndividuoSize());
            /*case UNIFORM:
                return new UniformCross(params.getPopulationSize(), params.getTruncatedK());*/
            default:
                throw new Error("WHAT ARE YOU");
        }

    }
}
