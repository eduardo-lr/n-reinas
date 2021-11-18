package ed.aplicaciones;

public class Tablero {

	private class Posicion {

		public int renglon;
		
		public char columna;

		public Posicion(int renglon, char columna) {
			this.renglon = renglon;
			this.columna = columna;
		}

		@Override public boolean equals(Object object) {
			if (object == null || object.getClass() != this.getClass()) 
    	        return false;
			
			Posicion posicion = (Posicion) object;

			return renglon == posicion.renglon && columna == posicion.columna;
		}
	}

	private class Reina {
	
		public Posicion posicion;

		public Reina(Posicion posicion) {
			this.posicion = posicion;
		}

		public void mueve(Direccion direccion) {
			switch(direccion) {
				case DERECHA:
					posicion.columna++;
				case IZQUIERDA:
					posicion.columna--;
				case ARRIBA:
					posicion.renglon++;
				case ABAJO:
					posicion.renglon--;
			}
		}

		@Override public boolean equals(Object object) {
			if (object == null || object.getClass() != this.getClass()) 
    	        return false;
			
			Reina reina = (Reina) object;

			return posicion.equals(reina.posicion);
		}
	
		@Override public String toString() {
			return String.format("Renglón %d, columna %c \n", posicion.renglon, posicion.columna);
		}
	}
	
	private int n;

	private Pila<Reina> reinas;

	public Tablero(int n) {
		this.n = n;
		this.reinas = new Pila<Reina>();
	}

	public Pila ResuelveNReinas() throws SinSolucion {
		while (!reinas.esVacia()) {
			Reina ultimaAgregada = reinas.mira();
			if (esComida(ultimaAgregada)) {
				while (!reinas.esVacia() && reinas.mira().posicion.columna == n) {
					reinas.saca();
				}
				if (!reinas.esVacia())
					reinas.mira().mueve(Direccion.DERECHA);
			} else if (reinas.getLongitud() == n) 
				return reinas;
			else
				reinas.mira().mueve(Direccion.ARRIBA);
		}
		if (reinas.esVacia())
			throw new SinSolucion("No hay solucion");
		else
			return reinas; 
	}

	public boolean esComida(Reina ultimaAgregada) {
		for (Reina reina : reinas) {
			if (ultimaAgregada.equals(reina))
				continue;
			if (ultimaAgregada.posicion.columna == reina.posicion.columna || 
					ultimaAgregada.posicion.renglon == reina.posicion.renglon)
				return true;
			if (estanEnDiagonal(ultimaAgregada, reina))
				return true;
		}
		return false;
	}

	private boolean estanEnDiagonal(Reina ultimaAgregada, Reina reina) {
		return (estanEnDiagonalDerecha(ultimaAgregada, reina) || 
					estanEnDiagonalIzquierda(ultimaAgregada, reina));
	}

	private boolean estanEnDiagonalDerecha(Reina ultimaAgregada, Reina reina) {
		return false;
	}

	private boolean estanEnDiagonalIzquierda(Reina ultimaAgregada, Reina reina) {
		return false;
	}
}
