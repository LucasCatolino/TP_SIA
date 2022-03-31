package sia.grupo19.cross;

import sia.grupo19.Individuo;

public class SimpleCross implements Cross {
	
	private int x_lenght;

	public SimpleCross(int x_lenght) {
        this.x_lenght = x_lenght;
	}

	@Override
	public Individuo[] cross(Individuo father, Individuo mother) {
		int index = (int) Math.floor(Math.random() * x_lenght); // Random value between 0 and 11
		double[] son1 = new double[11];
		double[] son2 = new double[11];
		for (int i = 0; i < index; i++) {
			son1[i] = father.getX()[i];
			son2[i] = mother.getX()[i];
		}
		for (int i = index; i < x_lenght; i++) {
			son1[i] = mother.getX()[i];
			son2[i] = father.getX()[i];
		}
		Individuo[] toRet = new Individuo[2];
		toRet[0] = new Individuo(son1);
		toRet[1] = new Individuo(son2);
		return toRet;
	}

}
