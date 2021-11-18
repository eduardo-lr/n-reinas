package ed.aplicaciones;

public class Tablero {

	private class Posicion {

		public int renglon;
		
		public int columna;

		public Posicion(int renglon, int columna) {
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
			return String.format("Renglón %d, columna %c \n", 
									posicion.renglon, (char) (posicion.columna + 96));
		}
	}
	
	private int n;

	private Pila<Reina> reinas;

	public Tablero(int n) {
		this.n = n;
		this.reinas = new Pila<Reina>();
	}

	public void resuelveNReinas() {
		try {
			resuelve();
			for (Reina reina : reinas) {
				System.out.println(reina.toString());
			}
		} catch (SinSolucion ss) {
			System.out.println(ss.getMessage());
			System.exit(1);
		}
	}

	private Pila resuelve() throws SinSolucion {
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

	private boolean esComida(Reina ultimaAgregada) {
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
		return (seCruzan(ultimaAgregada, reina, Direccion.DERECHA) || 
					seCruzan(ultimaAgregada, reina, Direccion.IZQUIERDA));
	}

	private boolean seCruzan(Reina ultimaAgregada, Reina reina, Direccion direccion) {
		Posicion posicion = reina.posicion;
		boolean seCruzaron = false;
		while (reina.posicion.renglon < n) {
			if (direccion == Direccion.IZQUIERDA && reina.posicion.columna <= 1)
				break;
			else if (direccion == Direccion.DERECHA && reina.posicion.columna >= n)
				break;
			reina.mueve(direccion);
			reina.mueve(Direccion.ARRIBA);
			if (ultimaAgregada.equals(reina)) {
				seCruzaron = true;
				break;
			}
		}
		reina.posicion = posicion;
		return seCruzaron;
	}
}
