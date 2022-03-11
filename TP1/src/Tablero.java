import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//package main;

public class Tablero {

	private String estado;
	private int zeroPos;
	private String[] rotaciones = { "-1", "-1", "-1", "-1" };

	public Tablero(String cadena) {
		estado = cadena;
		zeroPos = estado.indexOf("0");
	}

	public String getEstado() {
		return estado;
	}

	public String[] getRotaciones() {

		switch (zeroPos) {
			case 0:
				rotaciones[0] = swap(estado, 0, 1);
				rotaciones[1] = swap(estado, 0, 3);
				break;
			case 1:
				rotaciones[0] = swap(estado, 1, 0);
				rotaciones[1] = swap(estado, 1, 2);
				rotaciones[2] = swap(estado, 1, 4);
				break;
			case 2:
				rotaciones[0] = swap(estado, 2, 1);
				rotaciones[1] = swap(estado, 2, 5);
				break;
			case 3:
				rotaciones[0] = swap(estado, 3, 0);
				rotaciones[1] = swap(estado, 3, 4);
				rotaciones[2] = swap(estado, 3, 6);
				break;
			case 4:
				rotaciones[0] = swap(estado, 4, 1);
				rotaciones[1] = swap(estado, 4, 3);
				rotaciones[2] = swap(estado, 4, 5);
				rotaciones[3] = swap(estado, 4, 7);
				break;
			case 5:
				rotaciones[0] = swap(estado, 5, 2);
				rotaciones[1] = swap(estado, 5, 4);
				rotaciones[2] = swap(estado, 5, 8);
				break;
			case 6:
				rotaciones[0] = swap(estado, 6, 3);
				rotaciones[1] = swap(estado, 6, 7);
				break;
			case 7:
				rotaciones[0] = swap(estado, 7, 4);
				rotaciones[1] = swap(estado, 7, 6);
				rotaciones[2] = swap(estado, 7, 8);
				break;
			case 8:
				rotaciones[0] = swap(estado, 8, 5);
				rotaciones[1] = swap(estado, 8, 7);
				break;
			default:
				break;
		}
		return rotaciones;
	}

	public double getH() {
		// TODO: should hSelected be some value that comes from beyond/the node/some
		// constant?
		switch (hSelected) {
			case 0:
				return getEuclidean();
			case 1:
				return getManhattan();
			case 2:
				return getInvalid();
		}
	}

	private double getEuclidean() {
		// TODO: make this into some constant
		Map<Character, Coord> template = new HashMap<>();
		template.put('1', new Coord(0, 0));
		template.put('2', new Coord(0, 1));
		template.put('3', new Coord(0, 2));
		template.put('4', new Coord(1, 0));
		template.put('5', new Coord(1, 1));
		template.put('6', new Coord(1, 2));
		template.put('7', new Coord(2, 0));
		template.put('8', new Coord(2, 1));
		template.put('0', new Coord(2, 2));

		// is there a better way to iterate than to make this? hopefully
		Character[] keys = template.keySet().toArray(new Character[0]);

		double ret = 0.0;

		for (int i = 0; i < template.size(); i++) {
			if (estado.charAt(i) != keys[i]) {
				Coord tileCoordInEstado = template.get(estado.charAt(i));
				Coord idealCoord = template.get(keys[i]);

				Coord calc = new Coord(
						Math.abs(tileCoordInEstado.getX() - idealCoord.getX()),
						Math.abs(tileCoordInEstado.getY() - idealCoord.getY()));

				ret += Math.sqrt(calc.getX() * calc.getX() + calc.getY() * calc.getY());
			}
		}

		return ret;
	}

	private double getManhattan() {
		// TODO: make this into some constant
		Map<Character, Coord> template = new HashMap<>();
		template.put('1', new Coord(0, 0));
		template.put('2', new Coord(0, 1));
		template.put('3', new Coord(0, 2));
		template.put('4', new Coord(1, 0));
		template.put('5', new Coord(1, 1));
		template.put('6', new Coord(1, 2));
		template.put('7', new Coord(2, 0));
		template.put('8', new Coord(2, 1));
		template.put('0', new Coord(2, 2));

		// is there a better way to iterate than to make this? hopefully
		Character[] keys = template.keySet().toArray(new Character[0]);

		double ret = 0.0;

		for (int i = 0; i < template.size(); i++) {
			if (estado.charAt(i) != keys[i]) {
				Coord tileCoordInEstado = template.get(estado.charAt(i));
				Coord idealCoord = template.get(keys[i]);

				ret += Math.abs(tileCoordInEstado.getX() - idealCoord.getX())
						+ Math.abs(tileCoordInEstado.getY() - idealCoord.getY());
			}
		}

		return ret;
	}

	private double getInvalid() {
		return 42; // TODO: think of something more interesting
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

}
