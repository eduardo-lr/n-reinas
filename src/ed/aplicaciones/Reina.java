package ed.aplicaciones;

public class Reina {

	private int renglon;
		
	private int columna;

	public Reina(int renglon) {
		this.renglon = renglon;
		this.columna = 1;
	}

	public void mueve() {
		columna++;
	}

	public int getRenglon() {
		return renglon;
	}

	public int getColumna() {
		return columna;
	}

	@Override public boolean equals(Object object) {
		if (object == null || object.getClass() != this.getClass()) 
   	        return false;
		
		Reina reina = (Reina) object;
		
		return renglon == reina.renglon && columna == reina.columna;
	}
	
	@Override public String toString() {
		return String.format("Renglón %d, columna %c", 
								renglon, IntToChar(columna));
	}

	private char IntToChar(int entero) {
		return (char) (entero + 96);
	}
}
