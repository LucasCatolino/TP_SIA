package sia.grupo19;

import java.util.ArrayList;

public class Unit {

	private static final double BETA = 1;
	private static final double LEARNING_RATE = 0.01;
	private ArrayList<Double> weights;
	private double activation;
	private double excitation;
	private double delta;
	private double inertia;

	private double momentum = 0.5;

	public Unit() {
		weights = new ArrayList<Double>();
		this.activation = 0;
		this.excitation = 0;
		this.delta = 0;
		this.inertia = 0;
	}

	public Unit(double momentum) {
		weights = new ArrayList<Double>();
		this.activation = 0;
		this.excitation = 0;
		this.delta = 0;
		this.inertia = 0;
		this.momentum = momentum;
	}

	public Unit(int exc, int act) {
		weights = new ArrayList<Double>();
		this.excitation = exc;
		this.activation = act;
		this.inertia = 0;
	}

	public void setWeight(double w) {
		weights.add(w);
	}

	public void calculateExcitation(int position, double prevActivation) {
		excitation = excitation + weights.get(position) * prevActivation;
	}

	public void calculateExcitation(int position, double prevActivation, boolean input) {
		excitation = prevActivation;
	}

	public void calculateActivation() {
		activation = Math.tanh(BETA * excitation); // TODO: desharcodear para usar otra funcion g(h)= tanh(bh)
	}

	public void calculateActivation(boolean input) {
		activation = excitation; // Identity function
	}

	public double getActivation() {
		return activation;
	}

	public void calculateDelta(double expectedOutput) {
		double g_derivated = BETA * (1 - Math.pow(Math.tanh(excitation), 2));// TODO: si se desharcodea la funcion para
																				// usar otra tener en cuenta que cambia
																				// la derivada: g'(h)= beta(1-g^2(h))
		delta = g_derivated * (expectedOutput - activation);
	}

	public double getWeight(int position) {
		return weights.get(position);
	}

	public double getDelta() {
		return delta;
	}

	public void calculateBackDelta(double weightedDeltas) {
		double g_derivated = BETA * (1 - Math.pow(Math.tanh(excitation), 2));// TODO: si se desharcodea la funcion para
																				// usar otra tener en cuenta que cambia
																				// la derivada: g'(h)= beta(1-g^2(h))
		delta = g_derivated * weightedDeltas;
	}

	public void updateWeight(int position, double unitActivation) {
		double wOld = weights.get(position);
		double wNew = LEARNING_RATE * delta * unitActivation;
		// inertia = 0.5 * inertia + (1 - 0.5) * wNew;
		inertia = this.momentum * inertia + (1 - this.momentum) * wNew;

		weights.set(position, wOld + inertia);
		// weights.set(position, wOld + wNew);
	}

	public void setExcitation(int exc) {
		excitation = exc;
	}

	public void setActivation(int i) {
		activation = 0;
	}

	public void setDelta(int i) {
		delta = 0;
	}
}
