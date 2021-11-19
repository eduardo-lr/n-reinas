package ed.aplicaciones;

/**
 * Clase para modelar un tablero.
 */
public class Tablero {

	/**
	 * Clase interna privada para modelar reinas. Una reina tiene
	 * un renglón y una columna asociados.
	 */
	private class Reina {

		/** El renglón en el cual está la reina. */
		public int renglon;
		
		/** La columna en la que está la reina. */
		public int columna;

		/**
		 * Constructor de reinas.
		 * @param renglon el renglon donde empieza la reina.
		 */
		public Reina(int renglon) {
			this.renglon = renglon;
			this.columna = 1;
		}

		/**
		 * Nos dice si la reina es igual al objeto recibido.
		 * @param object el objeto a comparar con la reina. 
		 * @return <code>true</code> si la reina es igual al objeto recibido;
	     *         <code>false</code> en otro caso.
		 */
		@Override public boolean equals(Object object) {
			if (object == null || object.getClass() != this.getClass()) 
   	    	    return false;
			Reina reina = (Reina) object;
			return renglon == reina.renglon && columna == reina.columna;
		}
	
		/**
		 * Regresa una representacion en cadena de la reina.
		 */
		@Override public String toString() {
			return String.format("Renglón %d, columna %c", 
								renglon, (char) (columna + 96));
		}
	}
	
	/** El tamaño del tablero. */
	private int n;

	/** La pila de reinas del tablero. */
	private Pila<Reina> reinas;

	/**
	 * Construye un tablero de tamaño n.
	 * @param n el tamaño del tablero.
	 */
	public Tablero(int n) {
		this.n = n;
		this.reinas = new Pila<Reina>();
	}

	/**
	 * Método para obtener una representacion en cadena 
	 * de la solución (si existe) del problema de N reinas. 
	 * Éste es el método llamado por la clase principal.
	 * @return una representación en cadena de la solución.
	 */
	public String resuelveNReinas() {
		String s = String.format("Tablero %dx%d \n", n, n); 
		if (resuelve()) {
			for (Reina reina : reinas) 
				s += reina.toString() + "\n";
		} else 
			s += "No hay solución \n";
		return s;
	}

	/**
	 * Éste método busca la solución al problema de las N
	 * reinas usando <l>backtracking</l>. El algoritmo  
	 * opera sobre la pila de reinas del tablero.
	 * @return <code>true</code> si el problema tiene solución;
	 *         <code>false</code> en otro caso.
	 */
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

	/**
	 * Método auxiliar para saber si una reina es comida
	 * por el resto de las reinas en el tablero.
	 * @param ultimaAgregada la última reina agregada al tablero.
	 * @return <code>true</code> si la última reina es comida;
	 *         <code>false</code> en otro caso.
	 */
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

	/**
	 * Método auxiliar para saber si la última reina agregada
	 * está en diagonal con una reina de la parte inferior del tablero.
	 * @param ultimaAgregada la última reina agregada al tablero.
	 * @param reina una reina en algún renglón inferior en el tablero.
	 * @return <code>true</code> si la última reina está en diagonal con la otra reina;
	 *         <code>false</code> en otro caso.
	 */
	private boolean estanEnDiagonal(Reina ultimaAgregada, Reina reina) {
		int pasos = ultimaAgregada.renglon - reina.renglon;
		if (ultimaAgregada.columna < reina.columna)
			return ultimaAgregada.columna == reina.columna-pasos;
		else 
			return ultimaAgregada.columna == reina.columna+pasos;
	}
}
