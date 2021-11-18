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
			if (esComida(ultimaAgregada)) {
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

	public boolean esComida(Reina ultimaAgregada) {
		for (Reina reina : reinas) {
			if (ultimaAgregada.equals(reina))
				continue;
			if (ultimaAgregada.getColumna() == reina.getColumna() || 
					ultimaAgregada.getRenglon() == reina.getRenglon())
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
