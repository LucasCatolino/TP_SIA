package sia.grupo19;

public class MultiLayerPerceptron {

	private static final double[][] X = { { -1, 1 }, { 1, -1 }, { -1, -1 }, { 1, 1 } }; // TODO: desharcodeame esta
	private static final double[] Y = { -1, -1, -1, 1 }; // TODO: desharcodeame esta
	private static final String FUNCTION= "sigmoid";
	private static final int LIMIT= 100;
	private Layer[] network; //rows: layers, columns: units
	
	public MultiLayerPerceptron(int inputSize, int outputSize, int hiddenLayers, int[] hiddenLayersSizes) {
		
		this.network= new Layer[hiddenLayers + 2]; //+2 because of input and output layers
		
		Layer inputLayer= new Layer(inputSize);
		network[0]= inputLayer;
		
		for (int i = 0; i < hiddenLayers; i++) {
			network[i+1]= new Layer(network[i], hiddenLayersSizes[i]);
		}
		
		Layer outputLayer= new Layer(outputSize);
		network[hiddenLayers + 1]= outputLayer;
	}
	
	public void run() {
		initializeUnits();
		double error= 1;
		
		int i= 0;		
		while (error > 0.1 || i < LIMIT) {
			//get random X_i
			int position= (int) Math.floor(Math.random() * X.length);
			
			network[0].apply(X[position]);
			
			
			i++;
		}
		
	}
	
	private void initializeUnits() {		
		for (int i = 1; i < network.length; i++) {
			network[i].initializeWeights();
		}
		
	}

	public static void main(String[] args) {
		int[] hiddenLayersSizes= {3};
		MultiLayerPerceptron multiLayerPerceptron= new MultiLayerPerceptron(2, 1, 1, hiddenLayersSizes);
		
		multiLayerPerceptron.run();
	}

}
