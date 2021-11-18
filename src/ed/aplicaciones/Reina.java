package ed.aplicaciones;

public class Reina {

	private int renglon;

	private int columna;

	public Reina(int renglon, int columna) {
		this.renglon = renglon;
		this.columna = columna;
	}

	public int getRenglon() {
		return renglon;
	}

	public int getColumna() {
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
}
