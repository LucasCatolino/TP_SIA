package sia.grupo19.cross;

import java.util.Arrays;

import sia.grupo19.Individuo;

public class MultipleCross implements Cross {
	
	private int x_lenght;
	private int cuttings;
	
	public MultipleCross(int x_lenght, int cuts) {
        this.x_lenght = x_lenght;
        this.cuttings= cuts;
	}

	@Override
	public Individuo[] cross(Individuo father, Individuo mother) {
		
		double[] son1 = new double[x_lenght];
		double[] son2 = new double[x_lenght];
		
		int[] positions= new int[cuttings+1];
		//positions= [0, (rand), 11]
		positions[0]= 0;
		positions[cuttings]= x_lenght;
		
		int i= 1;
		while(i < cuttings+1) {
			int index = (int) Math.floor(Math.random() * x_lenght); // Random value between 0 and 11: index of cutting
			if (!Arrays.stream(positions).anyMatch(j ->j == index)) {
				positions[i]= index;
				i++;
			}
		}
		Arrays.sort(positions);
		
		int parity= 0; //parity is to swap between cuts
		for (int k = 0; k < positions.length-1; k++) {
			int base= positions[k];
			int top= positions[k+1];
			for (int j = base; j < top; j++) {
				son1[j]= (parity % 2 == 0) ? father.getX()[j] : mother.getX()[j];
				son2[j]= (parity % 2 == 0) ? mother.getX()[j] : father.getX()[j];
			}
			parity++;
		}
		
		Individuo[] toRet = new Individuo[2];
		toRet[0] = new Individuo(son1);
		toRet[1] = new Individuo(son2);
		return toRet;
	}

}
