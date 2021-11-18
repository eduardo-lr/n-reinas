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
		return (seCruzan(ultimaAgregada, reina, Direccion.DERECHA) || 
					seCruzan(ultimaAgregada, reina, Direccion.IZQUIERDA));
	}

	private boolean seCruzan(Reina ultimaAgregada, Reina reina, Direccion direccion) {
		Posicion posicion = reina.getPosicion();
		boolean seCruzaron = false;
		while (reina.getPosicion().getRenglon() < ultimaAgregada.getPosicion().getRenglon()) {
			if (direccion == Direccion.IZQUIERDA && 
					reina.getPosicion().getColumna() <= ultimaAgregada.getPosicion().getColumna())
				break;
			else if (direccion == Direccion.DERECHA && 
					reina.getPosicion().getColumna() >= ultimaAgregada.getPosicion().getColumna())
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
