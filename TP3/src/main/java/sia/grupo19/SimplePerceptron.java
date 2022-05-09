package sia.grupo19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;

import sia.grupo19.params.EpochInfo;
import sia.grupo19.params.IterationInfo;
import sia.grupo19.params.SimpleParams;
import sia.grupo19.params.SimpleSolution;
import sia.grupo19.params.SimpleParams.SimplePerceptronMode;
import sia.grupo19.params.SimpleSolution.CutOffReason;

public class SimplePerceptron {

	public double ACCURACY_EPSILON = 0.25;

	private int LIMIT;
	private double learningRate;

	private double[][] X;
	private double[] Y;

	private int N;
	private int p;

	private double minAcceptable;

	private double minExpected, maxExpected;

	private SimpleParams params;

	public SimplePerceptron() {
		this.N = X[0].length + 1;
		this.p = X.length;

		System.out.println("N: " + N + " p: " + p);
	}

	// running the simple perceptron by itself with params
	public SimplePerceptron(SimpleParams params) {
		this.params = params;

		this.N = params.getTrainingDataInputDimension() + 1;
		this.p = params.getTrainingDataInputSize();

		this.X = addBias(params.getTrainingDataInputs(), p, N);
		// System.out.println("new X " + new Gson().toJson(this.X));

		if (params.getPerceptronMode() == SimplePerceptronMode.NONLINEAR) {
			/**
			 * For the non-linear perceptron we need to rescale the expected outputs to the
			 * range [-1, 1]
			 * since g: R -> [-1, 1] for both logistic and tanh
			 *
			 * "but wouldn't that mean we can only predict values within the original
			 * training data expected outputs range?":
			 * - Yes.
			 * "and woundn't we be only able to answer in this rescaled range?":
			 * - Yes. We could save the original range's min and max to scale the output
			 * once again... but that's not really necessary.
			 */
			this.minExpected = Arrays.stream(params.getTrainingDataOutputs()).min().getAsDouble();
			this.maxExpected = Arrays.stream(params.getTrainingDataOutputs()).max().getAsDouble();

			/**
			 * rescaler initially works in the positive including 0 domain, but we can just
			 * substract 1 on each rescale!
			 * so the new range is [0, 2] -> [-1, 1]
			 */
			this.Y = scaleValues(params.getTrainingDataOutputs(), minExpected, maxExpected, 0, 2);
			// System.out.println(new Gson().toJson(params.getTrainingDataOutputs()));
			System.out.println(new Gson().toJson(this.Y));
		} else {
			this.ACCURACY_EPSILON = params.getMinAcceptable();

			this.Y = params.getTrainingDataOutputs();
		}

		this.LIMIT = params.getIterationLimit();
		this.learningRate = params.getLearningRate();

		this.minAcceptable = params.getMinAcceptable();

		System.out.println("N: " + N + " p: " + p);
	}

	// running the simple perceptron via the simple cross validator
	public SimplePerceptron(SimpleParams params, double minExpectedOutput, double maxExpectedOutput) {
		this.params = params;

		this.N = params.getTrainingDataInputDimension() + 1;
		this.p = params.getTrainingDataInputSize();

		this.X = addBias(params.getTrainingDataInputs(), p, N);

		if (params.getPerceptronMode() == SimplePerceptronMode.NONLINEAR) {
			this.minExpected = minExpectedOutput;
			this.maxExpected = maxExpectedOutput;
			/**
			 * rescaler initially works in the positive including 0 domain, but we can just
			 * substract 1 on each rescale!
			 * so the new range is [0, 2] -> [-1, 1]
			 */
			this.Y = scaleValues(params.getTrainingDataOutputs(), minExpectedOutput, maxExpectedOutput, 0, 2);
			// System.out.println(new Gson().toJson(this.Y));
		} else {
			this.Y = params.getTrainingDataOutputs();
			this.ACCURACY_EPSILON = params.getMinAcceptable();

		}

		this.LIMIT = params.getIterationLimit();
		this.learningRate = params.getLearningRate();

		this.minAcceptable = params.getMinAcceptable();

		// System.out.println("N: " + N + " p: " + p);
	}

	public SimpleSolution run() {
		long startTime = System.currentTimeMillis();

		SimpleSolution solution = new SimpleSolution();
		solution.setParams(params);

		int i = 0;
		double[] w = new double[N];
		zeros(w);
		// System.out.println("w[w.length -1] = " + w[w.length - 1]);
		double error = 1;
		double minError = 2 * p;
		double[] minW = w;

		List<Integer> indices = getNewIndices(p);
		int epochs = 0;

		while ((error > minAcceptable) && (i < LIMIT)) {
			// get random number i_x between 1 and p
			if (indices.isEmpty()) {
				indices = getNewIndices(p);
				epochs++;
				solution.addEpochsInfo(
						new EpochInfo(w,
								// calculateAccuracy(w, X, Y, params.getMinAcceptable()),
								calculateAccuracy(w, X, Y, ACCURACY_EPSILON),
								error));
			}

			int i_x = indices.remove(0);

			// get excitement h= x[i_x].w
			double h = innerProduct(X[i_x], w);

			double O = calculateActivation(h, params.getPerceptronMode());

			double[] correction = new double[N];
			switch (this.params.getPerceptronMode()) {
				default:
				case STEP:
				case LINEAR:
					// ∆w = η * (y[i_x] − O).x[i_x]
					correction = scalarProduct(learningRate * (Y[i_x] - O), X[i_x]);
					break;
				case NONLINEAR:
					// ∆w = η * (y[i_x] − O).g'(h).x[i_x]
					correction = scalarProduct(learningRate * (Y[i_x] - O) * derivativeG(h), X[i_x]);
					break;
			}

			// w = w + ∆w
			w = addCorrection(w, correction);

			error = calculateError(X, Y, w, p);
			if (error < minError) {
				minError = error;
				minW = w;
			}
			// let's just show something at least
			if (solution.getBestIteration() == null || error < solution.getBestIteration().getError()) {
				solution.setBestIteration(new IterationInfo(w, error));
			}

			solution.addIterationInfo(new IterationInfo(w, error));

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
		long stopTime = System.currentTimeMillis();

		solution.setIterations(i);
		solution.setEpochs(epochs);

		if (i % p != 0) {
			solution.addEpochsInfo(new EpochInfo(w, calculateAccuracy(w, X, Y, ACCURACY_EPSILON), error));
		}

		System.out
				.println("final acc: " + calculateAccuracy(solution.getBestIteration().getW(), X, Y, ACCURACY_EPSILON));

		solution.setElapsedTimeMillis(stopTime - startTime);
		solution.setStopReason(error <= minAcceptable ? CutOffReason.MINACCEPTABLE : CutOffReason.MAXITER);

		return solution;
	}

	public SimpleSolution runExtended(double[][] testingSetInputs, double[] testingSetOutputs) {
		long startTime = System.currentTimeMillis();

		SimpleSolution solution = new SimpleSolution();
		solution.setParams(params);

		int i = 0;
		double[] w = new double[N];
		zeros(w);
		// System.out.println("w[w.length -1] = " + w[w.length - 1]);
		double error = 1;
		double minError = 2 * p;
		double[] minW = w;

		List<Integer> indices = getNewIndices(p);
		int epochs = 0;

		while ((error > minAcceptable) && (i < LIMIT)) {
			// get random number i_x between 1 and p
			if (indices.isEmpty()) {
				indices = getNewIndices(p);
				epochs++;
				solution.addEpochsInfo(
						new EpochInfo(w,
								calculateAccuracy(w, testingSetInputs, testingSetOutputs, ACCURACY_EPSILON),
								error));
			}

			int i_x = indices.remove(0);

			// get excitement h= x[i_x].w
			double h = innerProduct(X[i_x], w);

			double O = calculateActivation(h, params.getPerceptronMode());

			double[] correction = new double[N];
			switch (this.params.getPerceptronMode()) {
				default:
				case STEP:
				case LINEAR:
					// ∆w = η * (y[i_x] − O).x[i_x]
					correction = scalarProduct(learningRate * (Y[i_x] - O), X[i_x]);
					break;
				case NONLINEAR:
					// ∆w = η * (y[i_x] − O).g'(h).x[i_x]
					correction = scalarProduct(learningRate * (Y[i_x] - O) * derivativeG(h), X[i_x]);
					break;
			}

			// w = w + ∆w
			w = addCorrection(w, correction);

			error = calculateError(X, Y, w, p);
			if (error < minError) {
				minError = error;
				minW = w;
			}
			// let's just show something at least
			if (solution.getBestIteration() == null || error < solution.getBestIteration().getError()) {
				solution.setBestIteration(new IterationInfo(w, error));
			}

			solution.addIterationInfo(new IterationInfo(w, error));
			i++;
		}
		long stopTime = System.currentTimeMillis();

		solution.setIterations(i);
		solution.setEpochs(epochs);

		solution.setElapsedTimeMillis(stopTime - startTime);
		solution.setStopReason(error <= minAcceptable ? CutOffReason.MINACCEPTABLE : CutOffReason.MAXITER);

		return solution;
	}

	private double calculateError(double[][] X, double[] Y, double w[], int p) {
		double out = 0;
		for (int i = 0; i < p; i++) {
			// get excitement h= x[i_x].w
			// double h = innerProduct(X[i], w) + w[w.length - 1];
			double h = innerProduct(X[i], w);

			double O = calculateActivation(h, params.getPerceptronMode());

			// out += Math.pow(Y[i] - (O + w[w.length - 1]), 2);
			out += Math.pow(Y[i] - (O), 2);
		}
		return out / 2;
	}

	public double predict(double[] w, double[] X) {
		double h = innerProduct(X, w);
		double O = calculateActivation(h, params.getPerceptronMode());

		return O;
	}

	public double calculateAccuracy(double[] w, double[][] testInputs, double[] testOutputs, double expectedError) {

		double[] rescaledOutputs;
		if (params.getPerceptronMode() == SimplePerceptronMode.NONLINEAR) {
			rescaledOutputs = scaleValues(testOutputs, this.minExpected, this.maxExpected, 0, 2);
		} else {
			rescaledOutputs = testOutputs;
		}

		int correct = 0;
		for (int i = 0; i < testOutputs.length; i++) {
			double diff = Math.abs(predict(w, testInputs[i]) - rescaledOutputs[i]);
			if (diff <= expectedError) {
				correct++;
			}
		}

		return ((double) correct) / testOutputs.length;
	}

	/*
	 **********************
	 * HELPER FUNCTIONS *
	 **********************
	 */

	private double[][] addBias(double[][] in, int size, int dim) {
		double[][] out = new double[size][dim];
		for (int i = 0; i < size; i++) {
			double[] newElem = new double[dim];
			for (int j = 0; j < dim - 1; j++) {
				newElem[j] = in[i][j];
			}
			newElem[dim - 1] = 1;
			out[i] = newElem;
		}
		return out;
	}

	private List<Integer> getNewIndices(int size) {
		List<Integer> out = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			out.add(i);
		}

		Collections.shuffle(out);

		return out;
	}

	private void zeros(double[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
		// array[array.length - 1] = -1;
	}

	private double innerProduct(double[] a, double[] b) {
		double toRet = 0;
		for (int i = 0; i < a.length; i++) {
			toRet += a[i] * b[i];
		}
		return toRet;
	}

	private double[] scalarProduct(double a, double[] b) {
		double[] toRet = new double[b.length];
		for (int i = 0; i < b.length; i++) {
			toRet[i] += a * b[i];
		}
		return toRet;
	}

	private double[] addCorrection(double[] w, double[] correction) {
		double[] toRet = new double[w.length];
		for (int i = 0; i < w.length; i++) {
			toRet[i] = w[i] + correction[i];
		}
		return toRet;
	}

	private double g(double excitation) {
		switch (params.getSigmoidType()) {
			default:
			case TANH:
				return Math.tanh(params.getBeta() * excitation);
			case LOGISTIC:
				return 1 / (1 + Math.exp(-2 * params.getBeta() * excitation));
		}
	}

	private double derivativeG(double excitation) {
		double auxG = g(excitation); // why call g 3 times when you can call it once?
		switch (params.getSigmoidType()) {
			default:
			case TANH:
				return params.getBeta() * (1 - Math.pow(auxG, 2));
			case LOGISTIC:
				return 2 * params.getBeta() * auxG * (1 - auxG);
		}
	}

	private double calculateActivation(double h, SimplePerceptronMode mode) {
		double O;
		switch (this.params.getPerceptronMode()) {
			default:
			case STEP:
				// get activation O= sign(h)
				// O = (int) Math.signum(h - w[N - 1]);
				O = Math.signum(h);
				break;
			case LINEAR:
				O = h;
				break;
			case NONLINEAR:
				O = g(h);
				break;
		}

		return O;
	}

	private double[] scaleValues(double[] ins, double insMin, double insMax, double outsMin, double outsMax) {
		double scale = (outsMax - outsMin) / (insMax - insMin);
		double[] out = new double[ins.length];
		for (int i = 0; i < ins.length; i++) {
			out[i] = ((ins[i] - insMin) * scale) - 1;
			// out[i] = 2 * (ins[i] - insMin) / (insMax - insMin) - 1; slack equation
		}

		return out;
	}

	public static void main(String[] args) {
		SimplePerceptron sp = new SimplePerceptron();
		sp.run();
	}

}
