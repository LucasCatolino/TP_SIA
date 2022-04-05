package sia.grupo19.cross;

import sia.grupo19.Params;

public class CrossFactory {
	
    public Cross getCross(Params params) {
        switch (params.getCrossType()) {
            case SIMPLE:
                return new SimpleCross(params.getIndividuoSize());
            case MULTIPLE:
               return new MultipleCross(params.getIndividuoSize(), params.getKCross());
            case UNIFORM:
                return new UniformCross(params.getIndividuoSize());
            default:
                throw new Error("I DON'T KNOW DAT LOVING MOVES");
        }

    }
}
