package sia.grupo19;

public class MultiLayerPerceptron {

	private static final double[][] X = { { -1, 1 }, { 1, -1 }, { -1, -1 }, { 1, 1 } }; // TODO: desharcodeame esta
	private static final double[] Y = { -1, -1, -1, 1 }; // TODO: desharcodeame esta
	private static final String FUNCTION= "sigmoid";
	private static final int LIMIT= 500;
	private Layer[] network; //rows: layers, columns: units
	private int lastLayer;
	
	public MultiLayerPerceptron(int inputSize, int outputSize, int hiddenLayers, int[] hiddenLayersSizes) {
		
		this.network= new Layer[hiddenLayers + 2]; //+2 because of input and output layers
		
		Layer inputLayer= new Layer(inputSize);
		network[0]= inputLayer;
		
		for (int i = 0; i < hiddenLayers; i++) {
			network[i+1]= new Layer(network[i], hiddenLayersSizes[i], false);
		}
		
		Layer outputLayer= new Layer(network[network.length - 2], outputSize, true);
		this.lastLayer= hiddenLayers + 1;
		network[lastLayer]= outputLayer;
	}
	
	public void run() {
		initializeUnits();
		double error= 1;
		
		int i= 0;		
		while (error > 0.001 && i < LIMIT) {
			System.out.println(i);
			//get random X_i
			int position= (int) Math.floor(Math.random() * X.length);
			
			//calculate activation for input layer
			network[0].apply(X[position]);
			
			//propagate activation to output layer
			propagate();
			
			//calculate delta for output layer
			network[lastLayer].calculateDelta(Y[position]);
			
			//backpropagate delta
			backpropagate();
			
			//update weights
			updateWeights();
			
			//update error
			error= calculateError(position, network[lastLayer]);
			
			i++;
		}
	}
	
	private double calculateError(int position, Layer layer) {
		double errorToRet= Y[position] - layer.getTotalActivation();
		System.out.println("Expected: " + Y[position] + " result: " + layer.getTotalActivation());
		return Math.abs(errorToRet);
	}

	private void updateWeights() {
		for (int i = 1; i < lastLayer + 1; i++) {
			network[i].updateWeights();
		}		
	}

	private void backpropagate() {
		for (int i = lastLayer - 1; i < 0; i--) {
			network[i].calculateDelta();
		}
	}

	private void propagate() {
		for (int i = 1; i < lastLayer + 1; i++) { //i= 1 because first layer was already calculated
			network[i].apply();
		}
		
	}

	private void initializeUnits() {	
		for (int i = 0; i < lastLayer + 1; i++) {
			network[i].initializeWeights();
		}
		
	}

	public static void main(String[] args) {
		int[] hiddenLayersSizes= {3};
		MultiLayerPerceptron multiLayerPerceptron= new MultiLayerPerceptron(2, 1, 1, hiddenLayersSizes);
		
		multiLayerPerceptron.run();
	}

}
