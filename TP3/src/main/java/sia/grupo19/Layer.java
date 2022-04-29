package sia.grupo19;

public class Layer {
	
	private Unit[] units;
	private Layer previousLayer;

	public Layer(int size) {
		this.units= new Unit[size + 1];
		
		for (int i = 0; i < size + 1; i++) {
			units[i]= new Unit();
		}
	}

	public Layer(Layer layer, int size) {
		this.previousLayer= layer;
		this.units= new Unit[size + 1];
		
		for (int i = 0; i < size + 1; i++) {
			units[i]= new Unit();
		}
	}

	public void initializeWeights() {
		for (int i = 0; i < units.length; i++) {
			if (previousLayer == null) {
				double w= Math.random();
				units[i].setWeight(w);
			} else {
				for (int j = 0; j < previousLayer.getSize(); j++) {
					double w= Math.random();
					units[i].setWeight(w);
				}				
			}
			
		}
	}

	private int getSize() {
		return units.length;
	}

	public void apply(double[] input) {
		units[0].calculateExcitation(0, -1);
		
		for (int i = 0; i < input.length; i++) {
			units[i].calculateExcitation(i+1, input[i]);
		}
		
	}
	
}
