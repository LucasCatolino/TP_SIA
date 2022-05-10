package sia.grupo19;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ex3_3 {

	private static int hiddenLayers;
	private static int[] hiddenLayersSizes;
	private static int inputSize;
	private static int outputSize;
	private static double[][] x;
	private static double[][] y;
	private static int epochs;
	private static double noiseProb;
	
	private static void readFile() {
        InputStream inputStream = Ex3_1.class.getClassLoader().getResourceAsStream("ex3_3.txt");
        assert inputStream != null;
        Scanner inputScanner = new Scanner(inputStream);
        
        ArrayList<Integer> hiddenSizes= new ArrayList<>();
//        while (inputScanner.hasNext()) {
//        	System.out.println(inputScanner.next());
//        }
        
    	inputScanner.next(); //Epochs
		epochs= Integer.parseInt(inputScanner.next());
		
		inputScanner.next(); //HiddenLayers
		hiddenLayers= Integer.parseInt(inputScanner.next());
		
		inputScanner.next(); //HiddenLayersSizes
		for (int i = 0; i < hiddenLayers; i++) {
			hiddenSizes.add(Integer.parseInt(inputScanner.next()));
		}
		
		inputScanner.next(); //InputSize
		inputSize= Integer.parseInt(inputScanner.next());
		
		inputScanner.next(); //XSize
		int xSize= Integer.parseInt(inputScanner.next());
		
		inputScanner.next(); //inputX
		inputScanner.next(); //{
		inputScanner.next(); //{
		
		x= new double[xSize][inputSize];
		
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < inputSize; j++) {
				x[i][j]= Integer.parseInt(inputScanner.next().replace(",", ""));
			}
			inputScanner.next(); //} o }
			inputScanner.next(); //{
		}
		
		inputScanner.next(); //OutputSize
		outputSize= Integer.parseInt(inputScanner.next());
		
		inputScanner.next(); //YSize
		int ySize= Integer.parseInt(inputScanner.next());
		
		inputScanner.next(); //outputY
		inputScanner.next(); //{
		inputScanner.next(); //{

		y= new double[ySize][outputSize];
		
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < outputSize; j++) {
				y[i][j]= Integer.parseInt(inputScanner.next().replace(",", ""));
			}
			inputScanner.next(); //} o }
			inputScanner.next(); //{
		}
		
		inputScanner.next(); //NoiseProb
		noiseProb= Double.parseDouble(inputScanner.next());
		
		inputScanner.close();
				
        hiddenLayersSizes= new int[hiddenSizes.size()];
        for (int i = 0; i < hiddenLayers; i++) {
			hiddenLayersSizes[i]= hiddenSizes.remove(0);
		}
	}
	
	public static void main(String[] args) {
		readFile();
		
		System.out.println("Starting");
		MultiLayerPerceptron multiLayerPerceptron= new MultiLayerPerceptron(inputSize, outputSize, hiddenLayers, hiddenLayersSizes, x, null, epochs, y, noiseProb);
		multiLayerPerceptron.run3_3();
		multiLayerPerceptron.testNoise();
		multiLayerPerceptron.printFile("Ex3_3.out");
		System.out.println("Finished");
	}
}
