package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tablero {

	private String estado;
	private int zeroPos;
	// private String[] rotaciones = { "-1", "-1", "-1", "-1" };
	private static String GOAL = "123456780";
	private static int[][] COORD = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 },
			{ 2, 1 }, { 2, 2 } };
	private static int ASCII = 48;

	public Tablero(String cadena) {
		estado = cadena;
		zeroPos = estado.indexOf("0");
	}

	public String getEstado() {
		return estado;
	}

	public List<String> getRotaciones() {
		List<String> rotaciones = new ArrayList<>();
		switch (zeroPos) {
			case 0:
				rotaciones.add(swap(estado, 0, 1));
				rotaciones.add(swap(estado, 0, 3));
				break;
			case 1:
				rotaciones.add(swap(estado, 1, 0));
				rotaciones.add(swap(estado, 1, 2));
				rotaciones.add(swap(estado, 1, 4));
				break;
			case 2:
				rotaciones.add(swap(estado, 2, 1));
				rotaciones.add(swap(estado, 2, 5));
				break;
			case 3:
				rotaciones.add(swap(estado, 3, 0));
				rotaciones.add(swap(estado, 3, 4));
				rotaciones.add(swap(estado, 3, 6));
				break;
			case 4:
				rotaciones.add(swap(estado, 4, 1));
				rotaciones.add(swap(estado, 4, 3));
				rotaciones.add(swap(estado, 4, 5));
				rotaciones.add(swap(estado, 4, 7));
				break;
			case 5:
				rotaciones.add(swap(estado, 5, 2));
				rotaciones.add(swap(estado, 5, 4));
				rotaciones.add(swap(estado, 5, 8));
				break;
			case 6:
				rotaciones.add(swap(estado, 6, 3));
				rotaciones.add(swap(estado, 6, 7));
				break;
			case 7:
				rotaciones.add(swap(estado, 7, 4));
				rotaciones.add(swap(estado, 7, 6));
				rotaciones.add(swap(estado, 7, 8));
				break;
			case 8:
				rotaciones.add(swap(estado, 8, 5));
				rotaciones.add(swap(estado, 8, 7));
				break;
			default:
				break;
		}
		return rotaciones;
	}

	private String swap(String e, int i, int j) {
		char[] cadena = e.toCharArray();
		char aux = cadena[i];
		cadena[i] = cadena[j];
		cadena[j] = aux;

		String cadenaSwapeada = "";
		for (int k = 0; k < cadena.length; k++) {
			cadenaSwapeada = cadenaSwapeada + cadena[k];
		}

		return cadenaSwapeada;
	}

	public int getManhattan() {
		int manhattan = 0;
		for (int i = 0; i < estado.length(); i++) {
			int origin = (int) estado.charAt(i) - ASCII;
			int dest = (int) GOAL.charAt(i) - ASCII;
			if (origin != dest) {
				int moveThis = estado.indexOf((char) (origin + ASCII));
				int moveHere = estado.indexOf((char) (dest + ASCII));
				manhattan = manhattan + correction(moveThis, moveHere);
			}
		}
		return (manhattan / 2);
	}

	private int correction(int origin, int dest) {
		int a = calcularCateto(origin, dest, 0);
		int b = calcularCateto(origin, dest, 1);
		return (int) Math.signum(a) * a + (int) Math.signum(b) * b; // Toma el modulo
	}

	public double getEuclidean() {
		double euclid = 0;
		for (int i = 0; i < estado.length(); i++) {
			int origin = (int) estado.charAt(i) - ASCII;
			int dest = (int) GOAL.charAt(i) - ASCII;
			if (origin != dest) {
				int moveThis = estado.indexOf((char) (origin + ASCII));
				int moveHere = estado.indexOf((char) (dest + ASCII));
				euclid = euclid + diagonal(moveThis, moveHere);
			}
		}
		return (euclid / 2);
	}

	private double diagonal(int origin, int dest) {
		int a = calcularCateto(origin, dest, 0);
		int b = calcularCateto(origin, dest, 1);
		return Math.sqrt(a * a + b * b);
	}

	private int calcularCateto(int origin, int dest, int i) {
		return COORD[origin][i] - COORD[dest][i];
	}

	public int getInvalid() {
		int invalid = 0;
		for (int i = 0; i < estado.length(); i++) {
			int origin = (int) estado.charAt(i) - ASCII;
			int dest = (int) GOAL.charAt(i) - ASCII;
			if (origin != dest) {
				invalid++;
			}
		}
		return invalid;
	}

	public boolean goalReached() {
		return estado.compareTo(GOAL) == 0;
	}

	// Para pruebas
	public void printTablero() {
		for (int i = 0; i < estado.length(); i++) {
			System.out.print(estado.charAt(i));
			if (i == 2 || i == 5) {
				System.out.println();
			}
		}
		System.out.println();
		System.out.println();
	}

	public String writeTablero() {
		String s = "";
		for (int i = 0; i < estado.length(); i++) {
			s += estado.charAt(i);
			if (i == 2 || i == 5) {
				s += "\n";
			}
		}
		s += "\n";
		return s;
	}

	public boolean isSolvable() {
		// Si est??? mal armada la matriz no es resoluble
		if (!this.wellConfigured()) {
			return false;
		}

		int inv = 0;
		// Elimino el espacio vacio
		String estadoAux = this.estado.replace("0", "");

		// Por cada elemento, veo si los que vienen despu???s son menores, aumentanto el
		// inv
		for (int i = 0; i < estadoAux.length(); i++) {
			int pre = (int) estadoAux.charAt(i) - ASCII;
			for (int j = i; j < estadoAux.length(); j++) {
				int post = (int) estadoAux.charAt(j) - ASCII;
				if (pre > post) {
					inv++;
				}
			}
		}

		// Si inv es par es resoluble, sino no
		return (inv % 2 == 0) ? true : false;
	}

	private boolean wellConfigured() {
		// Si tiene mas o menos elementos esta mal configurado
		if (this.estado.length() != 9) {
			return false;
		}

		// Creo un string ordenado con el tablero que entra
		char[] estadoAux = this.estado.toCharArray();
		Arrays.sort(estadoAux);

		// Comparo el string con lo que es un tablero bien configurado
		return (String.valueOf(estadoAux).compareTo("012345678") == 0);
	}

}
