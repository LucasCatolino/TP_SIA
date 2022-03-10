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
