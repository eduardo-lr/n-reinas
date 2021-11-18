package ed.aplicaciones;

public class Tablero {
	
	private int n;

	private Pila<Reina> reinas;

	public Tablero(int n) {
		this.n = n;
		this.reinas = new Pila<Reina>();
	}

	public Pila ResuelveNReinas() throws SinSolucion {
		while (!reinas.esVacia()) {
			Reina ultimaAgregada = reinas.mira();
			if (ultimaAgregada.esComida(reinas)) {
				while (!reinas.esVacia() && reinas.mira().getColumna() == n) {
					reinas.saca();
				}
				if (!reinas.esVacia())
					reinas.mira().mueveColumna();
			} else if (reinas.getLongitud() == n) 
				return reinas;
			else
				reinas.mira().mueveRenglon();
		}
		
		if (reinas.esVacia())
			throw new SinSolucion("No hay solucion");
		else
			return reinas; 
	}
}
