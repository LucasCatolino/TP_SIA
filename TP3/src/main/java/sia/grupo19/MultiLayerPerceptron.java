package sia.grupo19;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MultiLayerPerceptron {

//	private static final double[][] X = { { -1, 1 }, { 1, -1 }, { -1, -1 }, { 1, 1 } }; // TODO: desharcodeame esta
//	private static final double[] Y = { 1, 1, -1, -1 }; // TODO: desharcodeame esta
//	private static final double [] [] txt= {
//		{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0},
//		{0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0},
//		{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1},
//		{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0},
//		{0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
//		{1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0},
//		{0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0},
//		{1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
//		{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0},
//		{0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0} };
	//private static final double[] txtY = { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 };
	//private static final double[] txtY = { 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1 };
//	private static final double[][] txtY2 = { {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//											{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//											{0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
//											{0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
//											{0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//											{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
//											{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
//											{0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//											{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
//											{0, 0, 0, 0, 0, 0, 0, 0, 0, 1} };
//	private static final double[][] txtY2= { {1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//			{-1, 1, -1, -1, -1, -1, -1, -1, -1, -1},
//			{-1, -1, 1, -1, -1, -1, -1, -1, -1, -1},
//			{-1, -1, -1, 1, -1, -1, -1, -1, -1, -1},
//			{-1, -1, -1, -1, 1, -1, -1, -1, -1, -1},
//			{-1, -1, -1, -1, -1, 1, -1, -1, -1, -1},
//			{-1, -1, -1, -1, -1, -1, 1, -1, -1, -1},
//			{-1, -1, -1, -1, -1, -1, -1, 1, -1, -1},
//			{-1, -1, -1, -1, -1, -1, -1, -1, 1, -1},
//			{-1, -1, -1, -1, -1, -1, -1, -1, -1, 1} };
	private static double[][] X;
	private static double[] Y;
	private static double [] [] txt;
	private static double[] txtY;
	private static double[][] txtY2;
	private static final double ERROR= 0.00001;
	private static final double ACCURACY_ERROR= 0.18;
//	private static final int LIMIT= 1000000;
//	private static final int EPOCHS= 100001;
	private static int EPOCHS;
	private static final double PERCENTAGE= 0.75;
	private static double PROBABILITY_NOISE;
	private Layer[] network;
	private int lastLayer;
	private int trainingWellClassified;
	private int testingWellClassified;
	
	private ArrayList<Integer> indexList;
	private ArrayList<Integer> trainingList;
	private ArrayList<Integer> testingList;
	private ArrayList<Double> trainingAccuracyList;
	private ArrayList<Double> testingAccuracyList;
	private double minError;
	private Layer[] bestNetwork;
	private ArrayList<String> toPrint;
	
//	public MultiLayerPerceptron(int inputSize, int outputSize, int hiddenLayers, int[] hiddenLayersSizes) {
//		
//		this.network= new Layer[hiddenLayers + 2]; //+2 because of input and output layers
//		
//		Layer inputLayer= new Layer(inputSize);
//		network[0]= inputLayer;
//		
//		for (int i = 0; i < hiddenLayers; i++) {
//			network[i+1]= new Layer(network[i], hiddenLayersSizes[i], false);
//		}
//		
//		Layer outputLayer= new Layer(network[network.length - 2], outputSize, true);
//		this.lastLayer= hiddenLayers + 1;
//		network[lastLayer]= outputLayer;
//		
//		this.indexList= new ArrayList<Integer>();
//		this.trainingList= new ArrayList<Integer>();
//		this.testingList= new ArrayList<Integer>();
//		this.trainingAccuracyList= new ArrayList<Double>();
//		this.testingAccuracyList= new ArrayList<Double>();
//		this.minError= Integer.MAX_VALUE;
//		this.bestNetwork= network;
//	}
/*
	public void run() {
		initializeUnits();
		double error= 1;
		
		initializeIndex(X.length);
		
		int epoch= 0;		
		while (error > ERROR && epoch < LIMIT) {
			System.out.println(epoch);
			//get random X_i
			int position= (int) Math.floor(Math.random() * X.length);
			System.out.println("position: " + position);

			//calculate activation for input layer
			network[0].apply(X[position]);
			
			//propagate activation to output layer
			propagate(network);
			
			//calculate delta for output layer
			network[lastLayer].calculateDelta(Y[position]);
			
			//backpropagate delta
			backpropagate();
			
			//update weights
			updateWeights();
			
			//update error
			error= calculateError(position, network[lastLayer]);
			
			//restart excitations, activations and deltas
			restartUnits();

			epoch++;
		}
	}
*/
	
	public MultiLayerPerceptron(int inputSize, int outputSize, int hiddenLayers, int[] hiddenLayersSizes, double[][] x2,
			double[] y2, int epochs, double[][] y_ex3, double noise) {
		X= x2;
		txt= x2;
		Y= y2;
		txtY= y2;
		EPOCHS= epochs;
		txtY2= y_ex3;
		PROBABILITY_NOISE= noise;
		
		toPrint= new ArrayList<>();
		
		this.network= new Layer[hiddenLayers + 2]; //+2 because of input and output layers
		
		Layer inputLayer= new Layer(inputSize);
		network[0]= inputLayer;
		
		for (int i = 0; i < hiddenLayers; i++) {
			network[i+1]= new Layer(network[i], hiddenLayersSizes[i], false);
		}
		
		Layer outputLayer= new Layer(network[network.length - 2], outputSize, true);
		this.lastLayer= hiddenLayers + 1;
		network[lastLayer]= outputLayer;
		
		this.indexList= new ArrayList<Integer>();
		this.trainingList= new ArrayList<Integer>();
		this.testingList= new ArrayList<Integer>();
		this.trainingAccuracyList= new ArrayList<Double>();
		this.testingAccuracyList= new ArrayList<Double>();
		this.minError= Integer.MAX_VALUE;
		this.bestNetwork= network;
	}

	public void run() {
		int epoch= 0;		
		initializeUnits();
		double error= 1;
		
		while (epoch < EPOCHS && error > ERROR) {
			double auxError= 0;
			initializeIndex(X.length);
			toPrint.add("Epoch: " + epoch + "\n");
			
			while (!indexList.isEmpty()) {
				//get random X_i
				int position= indexList.remove(0);
				
				//calculate activation for input layer
				network[0].apply(X[position]);
				
				//propagate activation to output layer
				propagate(network);
				
				//calculate delta for output layer
				network[lastLayer].calculateDelta(Y[position]);
				
				//backpropagate delta
				backpropagate();
				
				//update weights
				updateWeights();
				
				//update auxError
				auxError+= calculateError(position, network[lastLayer]);
								
				//restart excitations, activations and deltas
				restartUnits();				
			}
			
			epoch++;
			error= auxError/2;
			toPrint.add("Error: " + error + "\n");
			updateBestNetwork(error);
		}
	}
	
	private void updateBestNetwork(double error) {
		if (error < minError) {
			bestNetwork= network;
			minError= error;
		}
	}

	public void run3_2() {
		int epoch= 0;		
		initializeUnits();
		double error= 1;
		initializeIndex(txt.length);
		double totalTraining;
		double totalTesting;
		
		while (epoch < EPOCHS && error > ERROR) {
			toPrint.add("Epoch: " + epoch + "\n");
			double auxError= 0;
			setTrainingTestingLists();
			
			totalTraining= trainingList.size();
			totalTesting= testingList.size();
			trainingWellClassified= 0;
			testingWellClassified= 0;
			
			toPrint.add("Training\n");
			while (!trainingList.isEmpty()) {
				//get random X_i
				int position= trainingList.remove(0);
	
				//calculate activation for input layer
				network[0].apply(txt[position]);
				
				//propagate activation to output layer
				propagate(network);
				
				//calculate delta for output layer
				network[lastLayer].calculateDelta(txtY[position]);
				
				//backpropagate delta
				backpropagate();
				
				//update weights
				updateWeights();
				
				//update error
				auxError+= calculateErrorTxt(position, network[lastLayer]);
				
				//restart excitations
				restartUnits();
			}
			
			evaluateTestingList();
			
			double trainingAccuracy= (trainingWellClassified / totalTraining);
			double testingAccuracy= (testingWellClassified / totalTesting);

			trainingAccuracyList.add(trainingAccuracy);
			testingAccuracyList.add(testingAccuracy);
			
			epoch++;
			error= auxError/2;
			updateBestNetwork(error);
			
			toPrint.add("Error: " + error + "\n");
		}
		
		accuracyFileOutput("Training", trainingAccuracyList);
		accuracyFileOutput("Testing", testingAccuracyList);
	}
	
	private void accuracyFileOutput(String fileName, ArrayList<Double> accuracyList) {
		try {
            File file = new File("accuracy" + fileName);
            FileWriter myWriter = new FileWriter("accuracy" + fileName);
            
            int end= accuracyList.size();
            for (int i = 0; i < end; i++) {
            	try {
            		if ((i%100) == 0) {
            			myWriter.write("" + i + "," + accuracyList.get(i) + "\n");
					}
            	} catch (Exception e) {
            		System.err.println("IOException: " + fileName + ", " + i);
            	}
			}
            myWriter.close();
        } catch (IOException e) {
            System.out.println("IOException ocurred: " + fileName);
            e.printStackTrace();
        }
	}
	
	private void evaluateTestingList() {
		toPrint.add("Testing\n");
		while (!testingList.isEmpty()) {
			int position= testingList.remove(0);
			network[0].apply(txt[position]);
			propagate(network);
			double expected= txtY[position];
			double result= network[lastLayer].getTotalActivation();
			double errorAux= Math.abs(expected - result);
			if (Math.abs(errorAux) < ACCURACY_ERROR) {
				testingWellClassified ++;
			}
			toPrint.add("Expected: " + expected + ", result: " + result + "\n");
			restartUnits();
		}
		
	}
	private void setTrainingTestingLists() {
		int limit= (int) Math.floor(PERCENTAGE * indexList.size());
		for (int i = 0; i < limit; i++) {
			trainingList.add(indexList.get(i));
		}
		for (int i = limit; i < indexList.size(); i++) {
			testingList.add(indexList.get(i));
		}
		Collections.shuffle(indexList);
	}
	
	/*
	public void run3_2() {
		initializeUnits();
		double error= 1;
		
		int i= 0;		
		while ( i < LIMIT) {
			System.out.println(i);
			//get random X_i
			int position= (int) Math.floor(Math.random() * txt.length);
			System.out.println("position: " + position);

			//calculate activation for input layer
			network[0].apply(txt[position]);
			
			//propagate activation to output layer
			propagate(network);
			
			//calculate delta for output layer
			network[lastLayer].calculateDelta(txtY[position]);
			
			//backpropagate delta
			backpropagate();
			
			//update weights
			updateWeights();
			
			//update error
			error= calculateErrorTxt(position, network[lastLayer]);
			
			//restart excitations
			restartUnits();
			
			i++;
		}
		bestNetwork= network;
	}
*/
/*
	public void run3_3() {
		initializeUnits();
		double error= 1;
		initializeIndex(txt.length);
		
		int i= 0;		
		while (error > ERROR && i < LIMIT) {
			System.out.println(i);
			//get random X_i
			int position= (int) Math.floor(Math.random() * txt.length);
			System.out.println("position: " + position);

			//calculate activation for input layer
			network[0].apply(txt[position]);
			
			//propagate activation to output layer
			propagate();
			
			//calculate delta for output layer
			network[lastLayer].calculateDelta3_3(txtY2[position]);
			
			//backpropagate delta
			backpropagate();
			
			//update weights
			updateWeights();
			
			//update error
			error= calculateErrorTxt3(position, network[lastLayer]);
			
			//restart excitations
			restartUnits();

			i++;
		}
	}
	*/
	
	public void run3_3() {
		int epoch= 0;		
		initializeUnits();
		double error= 1;
		double auxError;
		
		while (error > ERROR && epoch < EPOCHS) {
			toPrint.add("Epoch: " + epoch + "\n");
			auxError= 0;
			initializeIndex(txt.length);

			while (!indexList.isEmpty()) {
				//get random X_i
				int position= indexList.remove(0);
				
				//calculate activation for input layer
				network[0].apply(txt[position]);
				
				//propagate activation to output layer
				propagate(network);
				
				//calculate delta for output layer
				network[lastLayer].calculateDelta3_3(txtY2[position]);
				
				//backpropagate delta
				backpropagate();
				
				//update weights
				updateWeights();
				
				//update error
				auxError+= calculateErrorTxt3(position, network[lastLayer]);
				
				//restart excitations
				restartUnits();
			}

			epoch ++;
			error= auxError/2;
			updateBestNetwork(error);
			toPrint.add("Error: " + error + "\n");
		}
	}
	
	private void initializeIndex(int limit) {
		for (int i = 0; i < limit; i++) {
			indexList.add(i);
		}
		Collections.shuffle(indexList);
	}

	private void restartUnits() {
		for (int i = 1; i < lastLayer + 1; i++) {
			network[i].restartUnits();
		}		
	}

	private double calculateError(int position, Layer layer) {
		double errorToRet= Y[position] - layer.getTotalActivation();
		toPrint.add("Expected: " + Y[position] + " result: " + layer.getTotalActivation() + "\n");
		return Math.pow(errorToRet, 2);
	}
	
	private double calculateErrorTxt(int position, Layer layer) {
		double expected= txtY[position];
		double result= layer.getTotalActivation();
		double errorToRet= expected - result;
		toPrint.add("Expected: " + expected + " result: " + result + "\n");
		if (Math.abs(errorToRet) < ACCURACY_ERROR) {
			trainingWellClassified ++;
		}
		return Math.pow(errorToRet, 2);
	}
	
	private double calculateErrorTxt3(int position, Layer layer) {
		double errorToRet= 0;
		for (int i = 0; i < txtY2.length; i++) {
			double act= layer.getUnitActivation(i);
			errorToRet+= Math.pow(txtY2[position][i] - act, 2);
			toPrint.add("Expected: " + txtY2[position][i] + " result: " + act + "\n");
		}
		return errorToRet;
	}

	private void updateWeights() {
		for (int i = 1; i < lastLayer + 1; i++) {
			network[i].updateWeights();
		}		
	}

	private void backpropagate() {
		for (int i = lastLayer - 1; i > 0; i--) {
			network[i].calculateDelta();
		}
	}

	private void propagate(Layer[] network) {
		for (int i = 1; i < lastLayer; i++) { //i= 1 because first layer was already calculated
			network[i].apply();
		}
		//System.out.println("capa: " + lastLayer);
		network[lastLayer].apply(true);
		
	}

	private void initializeUnits() {	
		for (int i = 0; i < lastLayer + 1; i++) {
			network[i].initializeWeights();
		}
		
	}

	public void evaluate1() {
		toPrint.add("\nFinal output" + "\n");
		for (int i = 0; i < X.length; i++) {
			bestNetwork[0].apply(X[i]);
			propagate(bestNetwork);
			toPrint.add("Expected: " + Y[i] + ", result: " + bestNetwork[lastLayer].getTotalActivation() + "\n");
			restartUnits();
		}
	}
	
	private void evaluate2() {
		for (int i = 0; i < txt.length; i++) {
			bestNetwork[0].apply(txt[i]);
			propagate(bestNetwork);
			System.out.println("Expected: " + txtY[i] + ", result: " + bestNetwork[lastLayer].getTotalActivation());
			restartUnits();
		}
	}
	
	public void testNoise() {
		double[][] noiseText= new double[txt.length][txt[0].length];

		for (int i = 0; i < noiseText.length; i++) {
			for (int j = 0; j < noiseText[0].length; j++) {
				noiseText[i][j]= txt[i][j];
			}
		}
		
		for (int i = 0; i < noiseText.length; i++) {
			for (int j = 0; j < noiseText[0].length; j++) {
				double prob= Math.random();
				if (prob < PROBABILITY_NOISE) {
					double aux;
					int position= (j + 1== noiseText[0].length) ? j-1 : j+1;
					aux= noiseText[i][position];
					/*try {
						aux= noiseText[i][j+1];
						position= j+1;
					} catch (Exception e) {
						aux= noiseText[i][j-1];
						position= j+1;
					}*/
					noiseText[i][position]= noiseText[i][j];
					noiseText[i][j]= aux;
				}
			}
		}
		
		for (int i = 0; i < noiseText.length; i++) {
			for (int j = 0; j < noiseText[0].length; j++) {
				System.out.println(txt[i][j] + " - " + noiseText[i][j] );
			}
			
		}
		noiseFileOutput("Original", txt);
		noiseFileOutput("Noise", noiseText);
		
	}
	
	private void noiseFileOutput(String fileName, double[][] txtInput) {
		toPrint.add("\nNoise added\n");
		try {
            File file = new File("accuracy" + fileName);
            FileWriter myWriter = new FileWriter("error" + fileName);
            
            for (int i = 0; i < txtInput.length; i++) {
    			bestNetwork[0].apply(txtInput[i]);
    			propagate(bestNetwork);
    			for (int j = 0; j < txtY2[0].length; j++) {
    				double expected= txtY2[i][j];
    				double result= bestNetwork[lastLayer].getUnitActivation(j);
    				toPrint.add("Expected: " + expected + ", result: " + result + "\n");
    				try {
    					myWriter.write("" + expected + "," + result + "\n");
    				} catch (Exception e) {
    					// TODO: handle exception
    				}
				}
    			restartUnits();
    		}

            myWriter.close();
        } catch (IOException e) {
            System.out.println("IOException ocurred: " + fileName);
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
//		int[] hiddenLayersSizes= {4, 3};
//		MultiLayerPerceptron multiLayerPerceptron= new MultiLayerPerceptron(2, 1, 2, hiddenLayersSizes);
//		multiLayerPerceptron.run();
//		multiLayerPerceptron.evaluate1();
		
//		int[] hiddenLayersSizes2= {25, 20, 15, 10, 5};
//		MultiLayerPerceptron multiLayerPerceptrontxt= new MultiLayerPerceptron(35, 1, 5, hiddenLayersSizes2);
//		multiLayerPerceptrontxt.run3_2();
//		System.out.println();
		
//		int[] hiddenLayersSizes3= {20, 15, 12, 10, 10};
//		MultiLayerPerceptron multiLayerPerceptrontxt3= new MultiLayerPerceptron(35, 10, 5, hiddenLayersSizes3);
//		multiLayerPerceptrontxt3.run3_3();
//		multiLayerPerceptrontxt3.testNoise();
	}

	public void printFile(String fileName) {
		try {
            File file = new File("resources/" + fileName);
            FileWriter myWriter = new FileWriter("resources/" + fileName);
            
            while (!toPrint.isEmpty()) {
            	try {
            		myWriter.write(toPrint.remove(0));
            	} catch (Exception e) {
            		System.err.println("IOException: " + fileName);
            	}
			}
            myWriter.close();
        } catch (IOException e) {
            System.out.println("IOException ocurred: " + fileName);
            e.printStackTrace();
        }
	}

}
