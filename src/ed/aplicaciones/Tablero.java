package ed.aplicaciones;

public class Tablero {
	
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
				while (!reinas.esVacia() && reinas.mira().getColumna() == n) {
					reinas.saca();
				}
				if (!reinas.esVacia()) 
					reinas.mira().mueve();
			} else if (reinas.getLongitud() == n) {
				return;
			} else {
				int renglon = reinas.mira().getRenglon();
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
			if (ultimaAgregada.getColumna() == reina.getColumna())
				return true;
			if (estanEnDiagonal(ultimaAgregada, reina))
				return true;
		}
		return false;
	}

	private boolean estanEnDiagonal(Reina ultimaAgregada, Reina reina) {
		int pasos = ultimaAgregada.getRenglon() - reina.getRenglon();
		if (ultimaAgregada.getColumna() < reina.getColumna())
			return ultimaAgregada.getColumna() == reina.getColumna()-pasos;
		else 
			return ultimaAgregada.getColumna() == reina.getColumna()+pasos;
	}

	@Override public String toString() {
		String s = "";
		for (Reina reina : reinas) 
			s += reina.toString() + "\n";
		return s;
	}
}
