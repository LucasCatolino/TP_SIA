package sia.grupo19.helpers;

public class Utils {

	private final static int LATENT_SIZE = 20;
	private final static int DOWN_LIMIT = -1;
	private final static int UP_LIMIT = 1;

	public static double[][] getTestingLatent() {
		int latentLimit = LATENT_SIZE * LATENT_SIZE;
		double[][] toRet = new double[latentLimit + 2 * LATENT_SIZE + 1][2];

		double step = (double) (UP_LIMIT - DOWN_LIMIT) / LATENT_SIZE;
		double x = DOWN_LIMIT;
		double y = DOWN_LIMIT;
		int pos = 0;

		while (pos <= latentLimit) {
			x = DOWN_LIMIT;
			for (int j = 0; j <= LATENT_SIZE; j++) {
				toRet[pos][0] = x;
				toRet[pos][1] = y;
				x += step;
				pos++;
			}
			y += step;
		}

		x = DOWN_LIMIT;
		for (int j = 0; j < LATENT_SIZE; j++) {
			toRet[pos][0] = x;
			toRet[pos][1] = y;
			x += step;
			pos++;
		}
		toRet[pos][0] = 1;
		toRet[pos][1] = 1;

		return toRet;
	}

	public static double[][] noiseEmUp(double[][] in, double noise_prob) {
		double[][] out = new double[in.length][in[0].length];
		for (int i = 0; i < in.length; i++) {
			out[i] = noysiInput(in[i], noise_prob);
		}
		return out;
	}

	public static double[] noysiInput(double[] notNoisyInput, double PROBABILITY_NOISE) {
		double[] noisyInput = new double[notNoisyInput.length];
		double prob = 0;
		for (int i = 0; i < notNoisyInput.length; i++) {
			prob = Math.random();
			if (prob < PROBABILITY_NOISE) { // if there is noise in the bit
				noisyInput[i] = (notNoisyInput[i] == 0) ? 1 : 0; // swap value (if it was 0 now is 1 and if it was 1 now
																	// is 0)
			} else { // if there is no noise in the bit
				noisyInput[i] = notNoisyInput[i]; // value remains equal
			}
		}
		return noisyInput;
	}

}
