package ed.aplicaciones;

public class Tablero {

	private class Reina {

		public int renglon;
		
		public int columna;

		public Reina(int renglon) {
			this.renglon = renglon;
			this.columna = 1;
		}

		@Override public boolean equals(Object object) {
			if (object == null || object.getClass() != this.getClass()) 
   	    	    return false;
		
			Reina reina = (Reina) object;
		
			return renglon == reina.renglon && columna == reina.columna;
		}
	
		@Override public String toString() {
			return String.format("Renglón %d, columna %c", 
								renglon, (char) (columna + 96));
		}
	}
	
	private int n;

	private Pila<Reina> reinas;

	public Tablero(int n) {
		this.n = n;
		this.reinas = new Pila<Reina>();
	}

	public void resuelveNReinas() throws SinSolucionEncontrada {
		reinas.mete(new Reina(1));
		while (!reinas.esVacia()) {
			if (esComida(reinas.mira())) {
				while (!reinas.esVacia() && reinas.mira().columna == n) {
					reinas.saca();
				}
				if (!reinas.esVacia()) 
					reinas.mira().columna++;
			} else if (reinas.getLongitud() == n) {
				return;
			} else {
				int renglon = reinas.mira().renglon;
				reinas.mete(new Reina(renglon+1));
			}
		}
		if (reinas.esVacia())
			throw new SinSolucionEncontrada("No hay solucion");
		else {
			return;
		}
	}

	private boolean esComida(Reina ultimaAgregada) {
		for (Reina reina : reinas) {
			if (ultimaAgregada.equals(reina))
				continue;
			if (ultimaAgregada.columna == reina.columna)
				return true;
			if (estanEnDiagonal(ultimaAgregada, reina))
				return true;
		}
		return false;
	}

	private boolean estanEnDiagonal(Reina ultimaAgregada, Reina reina) {
		int pasos = ultimaAgregada.renglon - reina.renglon;
		if (ultimaAgregada.columna < reina.columna)
			return ultimaAgregada.columna == reina.columna-pasos;
		else 
			return ultimaAgregada.columna == reina.columna+pasos;
	}

	@Override public String toString() {
		String s = "";
		for (Reina reina : reinas) 
			s += reina.toString() + "\n";
		return s;
	}
}
