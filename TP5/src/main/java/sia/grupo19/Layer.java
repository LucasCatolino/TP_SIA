package sia.grupo19;

public class Layer {

	private Unit[] units;
	private Layer previousLayer;
	private Layer nextLayer;

	public Layer(int size) {
		this.units = new Unit[size + 1];

		for (int i = 0; i < size + 1; i++) {
			units[i] = new Unit();
		}
	}

	public Layer(Layer layer, int size, boolean last) {
		int limit = (last) ? size : size + 1;
		this.units = new Unit[limit];

		this.previousLayer = layer;
		previousLayer.setNextLayer(this);

		for (int i = 0; i < limit; i++) {
			units[i] = new Unit();
		}
	}

	public Layer(int size, double momentum) {
		this.units = new Unit[size + 1];

		for (int i = 0; i < size + 1; i++) {
			units[i] = new Unit(momentum);
		}
	}

	public Layer(Layer layer, int size, boolean last, double momentum) {
		int limit = (last) ? size : size + 1;
		this.units = new Unit[limit];

		this.previousLayer = layer;
		previousLayer.setNextLayer(this);

		for (int i = 0; i < limit; i++) {
			units[i] = new Unit(momentum);
		}
	}

	private void setNextLayer(Layer layer) {
		this.nextLayer = layer;
	}

	public void initializeWeights() {
		for (int i = 0; i < units.length; i++) {
			if (previousLayer == null) {
				units[i].setWeight(1);
			} else {
				for (int j = 0; j < previousLayer.getSize(); j++) {
					double w = Math.random() - 0.5;
					units[i].setWeight(w);
				}
			}
		}
	}

	private int getSize() {
		return units.length;
	}

	public void apply(double[] input) {
		// calculate excitation
		units[0].calculateExcitation(0, 1, true);
		for (int i = 0; i < input.length; i++) {
			units[i + 1].calculateExcitation(0, input[i], true);
		}

		// calculate activation
		for (int i = 0; i < units.length; i++) {
			units[i].calculateActivation(true);
		}
	}

	public void apply() {
		// calculate excitation
		units[0].setExcitation(1);
		// units[0].calculateExcitation(0, -1);
		for (int i = 1; i < units.length; i++) {
			for (int j = 0; j < previousLayer.getSize(); j++) {
				double prevActivation = previousLayer.getUnitActivation(j);
				units[i].calculateExcitation(j, prevActivation);
			}
		}

		// calculate activation
		units[0].calculateActivation(true);
		for (int i = 1; i < units.length; i++) {
			units[i].calculateActivation();
		}
	}

	public double getUnitActivation(int position) {
		return units[position].getActivation();
	}

	public void calculateDelta(double expectedOutput) {
		for (int i = 0; i < units.length; i++) {
			units[i].calculateDelta(expectedOutput);
		}
	}

	public void calculateDelta() {
		for (int i = 0; i < units.length; i++) {
			double weightedDeltas = 0;
			for (int j = 0; j < nextLayer.getSize(); j++) { // TODO: see if is j= 0 or j= 1
				// weightedDeltas+= units[i].getWeight(i) * nextLayer.getUnitDelta(j);
				weightedDeltas += nextLayer.getUnitWeight(i, j) * nextLayer.getUnitDelta(j);
			}
			units[i].calculateBackDelta(weightedDeltas);
		}

	}

	private double getUnitWeight(int childPosition, int actualPosition) {
		return units[actualPosition].getWeight(childPosition);
	}

	private double getUnitDelta(int position) {
		return units[position].getDelta();
	}

	public void updateWeights() {
		for (int i = 1; i < units.length; i++) { // i= 1 because first unit is fictional and it doesn't change it's
													// weight
			for (int j = 0; j < previousLayer.getSize(); j++) {
				units[i].updateWeight(j, previousLayer.getUnitActivation(j));
			}
		}
	}

	public double getTotalActivation() {
		double layerActivation = 0;
		for (int i = 0; i < units.length; i++) {
			layerActivation += units[i].getActivation();
		}
		return layerActivation;
	}

	public void apply(boolean b) {
		// calculate excitation
		for (int i = 0; i < units.length; i++) {
			for (int j = 0; j < previousLayer.getSize(); j++) {
				double prevActivation = previousLayer.getUnitActivation(j);
				units[i].calculateExcitation(j, prevActivation);
			}
		}

		// calculate activation
		for (int i = 0; i < units.length; i++) {
			units[i].calculateActivation();
		}
	}

	public void restartUnits() {
		for (int i = 0; i < units.length; i++) {
			units[i].setExcitation(0);
			units[i].setActivation(0);
			units[i].setDelta(0);
		}
	}

	public void calculateDelta3_3(double[] expectedOutput) {
		for (int i = 0; i < units.length; i++) {
			units[i].calculateDelta(expectedOutput[i]);
		}
	}

}
