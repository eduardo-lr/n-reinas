package ed.aplicaciones;

public class Reina {
	
	private Posicion posicion;

	public Reina(Posicion posicion) {
		this.posicion = posicion;
	}

	public void mueve() {
		posicion.setColumna(posicion.getColumna()+1);
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	@Override public boolean equals(Object object) {
		if (object == null || object.getClass() != this.getClass()) 
   	        return false;
		
		Reina reina = (Reina) object;
		
		return posicion.equals(reina.posicion);
	}
	
	@Override public String toString() {
		return String.format("Renglón %d, columna %c", 
								posicion.getRenglon(), IntToChar(posicion.getColumna()));
	}

	private char IntToChar(int entero) {
		return (char) (entero + 96);
	}
}
