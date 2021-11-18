package ed.aplicaciones;

public class Main {

    public static void main(String[] args) {

    }

	private static Pila ResuelveNReinas(int n) throws SinSolucion {
		Pila<Reina> pila = new Pila<>();
		while (!pila.esVacia()) {
			Reina ultimaAgregada = pila.mira();
			if (ultimaAgregada.esComida(pila)) {
				while (!pila.esVacia() && pila.mira().getColumna() == n) {
					pila.saca();
				}
				if (!pila.esVacia())
					pila.mira().mueveColumna();
			} else if (pila.getLongitud() == n) 
				return pila;
			else
				pila.mira().mueveRenglon();
		}
		
		if (pila.esVacia())
			throw new SinSolucion("No hay solucion");
		else
			return pila; 
	}
}
