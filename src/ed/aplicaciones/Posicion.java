package ed.aplicaciones;

public class Posicion {

	private int renglon;
		
	private int columna;

	public Posicion(int renglon) {
		this.renglon = renglon;
		this.columna = 1;
	}

	public int getRenglon() {
		return renglon;
	}

	public int getColumna() {
		return columna;
	}

	public void setRenglon(int renglon) {
		this.renglon = renglon;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	@Override public boolean equals(Object object) {
		if (object == null || object.getClass() != this.getClass()) 
            return false;
		
		Posicion posicion = (Posicion) object;

		return renglon == posicion.renglon && columna == posicion.columna;
	}
}
