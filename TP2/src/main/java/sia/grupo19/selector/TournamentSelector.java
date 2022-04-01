package sia.grupo19.selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import sia.grupo19.Individuo;

public class TournamentSelector implements Selector {

    private int P;
    private Random rn;

    public TournamentSelector(int P) {
        this.P = P;
        rn = new Random();
    }

    @Override
    public List<Individuo> selectFrom(List<Individuo> inputPopulation) {
        if (inputPopulation.size() != 2 * P) {
            throw new Error("Population's size was not equal to 2P");
        }

        List<Individuo> shuffledList = new ArrayList<>(inputPopulation);

        List<Individuo> out = new ArrayList<>();
        while (out.size() < this.P) {
            Collections.shuffle(shuffledList);

            double u = getUmbral();
            Individuo pickA, pickB, winner;

            Individuo iA1 = shuffledList.remove(0);
            Individuo iA2 = shuffledList.remove(0);

            /*
             * double r = rn.nextDouble();
             * pickA = r < u ? pickBestOfTwo(iA1, iA2) : pickWorstOfTwo(iA1, iA2);
             * shuffledList.add(pickA.equals(iA1) ? iA2 : iA1);
             */
            pickA = pickWinnerAndReplaceLoser(u, iA1, iA2, shuffledList);

            Individuo iB1 = shuffledList.remove(0);
            Individuo iB2 = shuffledList.remove(0);
            /*
             * r = rn.nextDouble();
             * pickB = r < u ? pickBestOfTwo(iB1, iB2) : pickWorstOfTwo(iB1, iB2);
             * shuffledList.add(pickB.equals(iB1) ? iB2 : iB1);
             */
            pickB = pickWinnerAndReplaceLoser(u, iB1, iB2, shuffledList);

            /*
             * r = rn.nextDouble();
             * winner = r < u ? pickBestOfTwo(pickA, pickB) : pickWorstOfTwo(pickA, pickB);
             * shuffledList.add(winner.equals(pickA) ? pickB : pickA);
             */
            winner = pickWinnerAndReplaceLoser(u, pickA, pickB, shuffledList);

            out.add(winner);
        }

        return out;

    }

    private double getUmbral() {
        // returns a number in [0.5, 1]
        return 0.5 + (1 - 0.5) * rn.nextDouble();
    }

    private Individuo pickWinnerAndReplaceLoser(double u, Individuo i1, Individuo i2, List<Individuo> population) {
        double r = rn.nextDouble(); // pick a number from [0, 1]
        Individuo winner = r < u ? pickBestOfTwo(i1, i2) : pickWorstOfTwo(i1, i2);
        population.add(winner.equals(i1) ? i2 : i1);
        return winner;
    }

    private Individuo pickBestOfTwo(Individuo i1, Individuo i2) {
        return i1.compareTo(i2) >= 0 ? i1 : i2;
    }

    private Individuo pickWorstOfTwo(Individuo i1, Individuo i2) {
        return i1.compareTo(i2) < 0 ? i1 : i2;
    }

}
