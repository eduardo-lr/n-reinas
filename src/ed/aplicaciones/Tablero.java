package ed.aplicaciones;

public class Tablero {
	
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
		int pasos = ultimaAgregada.getPosicion().getRenglon() - reina.getPosicion().getRenglon();
		if (ultimaAgregada.getPosicion().getColumna() < reina.getPosicion().getColumna())
			return ultimaAgregada.getPosicion().getColumna() == reina.getPosicion().getColumna()-pasos;
		else 
			return ultimaAgregada.getPosicion().getColumna() == reina.getPosicion().getColumna()+pasos;
	}
}
