package sia.grupo19.cross;

import sia.grupo19.Individuo;

public interface Cross {

	Individuo[] cross(Individuo father, Individuo mother);
	
}
