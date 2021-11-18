package ed.aplicaciones;

public class Tablero {

	private class Posicion {

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

	private class Reina {
	
		private Posicion posicion;

		public Reina(Posicion posicion) {
			this.posicion = posicion;
		}

		public void mueve(Direccion direccion) {
			switch(direccion) {
				case DERECHA:
					posicion.setColumna(posicion.getColumna()+1);
					break;
				case IZQUIERDA:
					posicion.setColumna(posicion.getColumna()-1);
					break;
				case ARRIBA:
					posicion.setRenglon(posicion.getRenglon()+1);
					break;
				case ABAJO:
					posicion.setRenglon(posicion.getRenglon()-1);
					break;
			}
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
		} catch (SinSolucionEncontrada ss) {
			System.out.println(ss.getMessage());
			System.exit(1);
		}
	}

	private void resuelve() throws SinSolucionEncontrada {
		reinas.mete(new Reina(new Posicion(1)));
		while (!reinas.esVacia()) {
			if (esComida(reinas.mira())) {
				while (!reinas.esVacia() && reinas.mira().getPosicion().getColumna() == n) {
					reinas.saca();
				}
				if (!reinas.esVacia()) 
					reinas.mira().mueve(Direccion.DERECHA);
			} else if (reinas.getLongitud() == n) 
				return;
			else {
				int renglon = reinas.mira().getPosicion().getRenglon();
				reinas.mete(new Reina(new Posicion(renglon+1)));
			}
		}
		if (reinas.esVacia())
			throw new SinSolucionEncontrada("No hay solucion");
		else
			return; 
	}

	private boolean esComida(Reina ultimaAgregada) {
		for (Reina reina : reinas) {
			if (ultimaAgregada.equals(reina))
				continue;
			if (ultimaAgregada.getPosicion().getColumna() == reina.getPosicion().getColumna() || 
					ultimaAgregada.getPosicion().getRenglon() == reina.getPosicion().getRenglon())
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
		Posicion posicion = reina.getPosicion();
		boolean seCruzaron = false;
		while (reina.posicion.renglon < n) {
			if (direccion == Direccion.IZQUIERDA && reina.getPosicion().getColumna() <= 1)
				break;
			else if (direccion == Direccion.DERECHA && reina.getPosicion().getColumna() >= n)
				break;
			reina.mueve(direccion);
			reina.mueve(Direccion.ARRIBA);
			if (ultimaAgregada.equals(reina)) {
				seCruzaron = true;
				break;
			}
		}
		reina.setPosicion(posicion);
		return seCruzaron;
	}
}
