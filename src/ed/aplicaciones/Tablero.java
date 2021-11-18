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

	public String resuelveNReinas() {
		String s = String.format("Tablero %dx%d \n", n, n); 
		if (resuelve()) {
			for (Reina reina : reinas) 
				s += reina.toString() + "\n";
		} else 
			s += "No hay solución \n";
		return s;
	}

	private boolean resuelve() {
		reinas.mete(new Reina(1));
		while (!reinas.esVacia()) {
			if (esComida(reinas.mira())) {
				while (!reinas.esVacia() && reinas.mira().columna == n) {
					reinas.saca();
				}
				if (!reinas.esVacia()) 
					reinas.mira().columna++;
			} else if (reinas.getLongitud() == n) 
				return true;
			else {
				int renglon = reinas.mira().renglon;
				reinas.mete(new Reina(renglon+1));
			}
		}
		return !reinas.esVacia();
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
}
