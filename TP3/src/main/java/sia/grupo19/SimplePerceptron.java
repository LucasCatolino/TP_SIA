package sia.grupo19;

public class SimplePerceptron {

	private static final int LIMIT = 1000;
	private static final double learningRate = 0.1;
	private static final int[][] X = { { -1, 1 }, { 1, -1 }, { -1, -1 }, { 1, 1 } }; // TODO: desharcodeame esta
	private static final int[] Y = { -1, -1, -1, 1 }; // TODO: desharcodeame esta
	private static int N;
	private static int p;

	public SimplePerceptron() {
		this.N = X[0].length + 1;
		this.p = X.length;

		System.out.println("N: " + N + " p: " + p);
	}

	public void run() {
		int i = 0;
		double[] w = new double[N];
		zeros(w);
		double error = 1;
		double minError = 2 * p;
		double[] minW = w;

		while ((error > 0) && (i < LIMIT)) {
			// get random number i_x between 1 and p
			int i_x = (int) Math.floor(Math.random() * (p - 1) + 1);

			// get excitement h= x[i_x].w
			double h = innerProduct(X[i_x], w);

			// get activation O= sign(h)
			int O = (int) Math.signum(h); // TODO: esto es por ser escalón

			// ∆w = η * (y[i_x] − O).x[i_x]
			double correction = learningRate * innerProduct(Y[i_x] - O, X[i_x]);

			// w = w + ∆w
			w = addCorrection(w, correction);

			// asumo que originalmente pasaban x, y, w, p para hacer:
			// 1/2 * sum(1, p, (Y[i] - O)^2)
			// dudas:
			// => Es O o O[i]? (o sea, le paso el O calculado para el X[i_x] actual o tengo
			// que calcular el O de cada X[i] en la suma?)
			error = calculateError(Y, O, p);
			if (error < minError) {
				minError = error;
				minW = w;
			}

			/*
			 * Tomar un n´umero i x al azar entre 1 y p
			 * Calcular la exitaci´on h = x[i x].w
			 * Calcular la activaci´on O = signo(h)
			 * ∆w = η ∗ (y[i x] − O).x[i x]
			 * w = w + ∆w
			 * error = CalcularError(x, y, w, p)
			 * if error < error min
			 * error min = error
			 * wmin = w
			 * end
			 * i = i + 1
			 */
			i++;
		}

		System.out.println("Minimum Weights found:");
		for (int j = 0; j < minW.length; j++) {
			System.out.print(minW[j] + " ");
		}
		System.out.println("\nminError: " + minError);
	}

	private void zeros(double[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

	private double innerProduct(int[] a, double[] b) {
		double toRet = 0;
		for (int i = 0; i < a.length; i++) {
			toRet += a[i] * b[i];
		}
		return toRet;
	}

	private double innerProduct(int a, int[] b) {
		double toRet = 0;
		for (int i = 0; i < b.length; i++) {
			toRet += a * b[i];
		}
		return toRet;
	}

	private double[] addCorrection(double[] w, double correction) {
		double[] toRet = new double[w.length];
		for (int i = 0; i < w.length; i++) {
			toRet[i] = w[i] + correction;
		}
		return toRet;
	}

	private double calculateError(int[] Y, int O, int p) {
		double out = 0;
		for (int i = 0; i < p; i++) {
			out += Math.pow(Y[i] - O, 2);
		}
		return out / 2;
	}

	public static void main(String[] args) {
		SimplePerceptron sp = new SimplePerceptron();
		sp.run();
	}

}