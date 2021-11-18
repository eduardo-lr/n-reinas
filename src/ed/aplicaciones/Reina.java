package ed.aplicaciones;

public class Reina {

	private int renglon;

	private char columna;

	public Reina(int renglon, char columna) {
		this.renglon = renglon;
		this.columna = columna;
	}

	public int getRenglon() {
		return renglon;
	}

	public char getColumna() {
		return columna;
	}

	public void mueveRenglon() {
		renglon++;
	}

	public void mueveColumna() {
		columna++;
	}

	public boolean esComida(Pila<Reina> pila) {
		return false;
	}

	@Override public boolean equals(Object object) {
		return false;
	}

	@Override public String toString() {
		return String.format("Renglón %d, columna %c \n", renglon, columna);
	}
}
