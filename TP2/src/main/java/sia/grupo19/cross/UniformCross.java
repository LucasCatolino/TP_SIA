package sia.grupo19.cross;

import sia.grupo19.Individuo;

public class UniformCross implements Cross {

	private int x_lenght;
	private static final double UNIFORM_PROB= 0.5;
	
	public UniformCross(int x_lenght) {
        this.x_lenght = x_lenght;
	}

	@Override
	public Individuo[] cross(Individuo father, Individuo mother) {

		double[] son1 = new double[x_lenght];
		double[] son2 = new double[x_lenght];

		for (int i = 0; i < x_lenght; i++) {
			double rand= Math.random();
			//if rand < 0.5 swap, else no
			son1[i]= (rand < UNIFORM_PROB) ? father.getX()[i] : mother.getX()[i];
			son2[i]= (rand < UNIFORM_PROB) ? mother.getX()[i] : father.getX()[i];
		}
		
		Individuo[] toRet = new Individuo[2];
		toRet[0] = new Individuo(son1);
		toRet[1] = new Individuo(son2);
		return toRet;
	}

}
