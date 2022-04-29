package sia.grupo19;

import java.util.ArrayList;

public class Unit {
	
	private ArrayList<Double> weights;
	private double activation;
	private double excitation;
	
	public Unit() {
		weights= new ArrayList<Double>();
		this.activation= 0;
		this.excitation= 0;
	}

	public void setWeight(double w) {
		weights.add(w);
	}

	public void calculateExcitation(int position, double prevActivation) {
		excitation= excitation + weights.get(position) * prevActivation;
	}
}
