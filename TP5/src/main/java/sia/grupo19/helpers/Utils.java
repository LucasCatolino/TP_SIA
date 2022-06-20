package sia.grupo19.helpers;

public class Utils {
	
	private final static int LATENT_SIZE= 20;
	private final static int DOWN_LIMIT= -1;
	private final static int UP_LIMIT= 1;

	public static double[][] getTestingLatent() {
		int latentLimit= LATENT_SIZE * LATENT_SIZE;
		double[][] toRet= new double[latentLimit + 2 * LATENT_SIZE][2];

		double step= (double) (UP_LIMIT - DOWN_LIMIT)/LATENT_SIZE;
		double x= DOWN_LIMIT;
		double y= DOWN_LIMIT;
		int pos= 0;

		while (pos <= latentLimit) {
			x= DOWN_LIMIT;
			for (int j = 0; j <= LATENT_SIZE; j++) {
				toRet[pos][0]= x;
				toRet[pos][1]= y;
				x+= step;
				pos++;
			}
			y+= step;
		}
		
		x= DOWN_LIMIT;
		for (int j = 0; j < LATENT_SIZE; j++) {
			toRet[pos][0]= x;
			toRet[pos][1]= y;
			x+= step;
			pos++;
		}
		
		return toRet;
	}

}
