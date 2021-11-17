package ed.aplicaciones;

import java.util.NoSuchElementException;

/**
 * Clase para pilas de tipo genérico.
 */
public class Pila<T> {

    /**
     * Clase interna para nodos.
     */
    private class Nodo {
        /** El elemento del nodo. */
        public T elemento;
        /** El siguiente nodo. */
        public Nodo siguiente;

        /**
         * Construye un nodo con un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
			this.elemento = elemento;
        }
    }

    /** La cabeza de la pila. */
    private Nodo cabeza;
    /** El rabo de la pila. */
    private Nodo rabo;
	/** La longitud de la pila. */
	private int longitud;

    /**
     * Agrega un elemento al tope de la pila.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void mete(T elemento) {
		if (elemento == null) 
			throw new IllegalArgumentException();
		Nodo n = new Nodo(elemento);
		if (cabeza == null) 
			cabeza = rabo = n;
		else {
			n.siguiente = cabeza;
			cabeza = n;
		}
		longitud++;
    }

    /**
     * Elimina el elemento en la pila y lo regresa.
     * @return el elemento en el tope de la pila.
     * @throws NoSuchElementException si la pila está vacía.
     */
    public T saca() {
		if (esVacia()) 
			throw new NoSuchElementException();
		T t = cabeza.elemento;
		cabeza = cabeza.siguiente;
		if (cabeza == null) 
			rabo = null;
		longitud--;
		return t;
    }

    /**
     * Nos permite ver el elemento en el tope de la pila sin sacarlo
     * de la misma.
     * @return el elemento en el tope de la pila.
     * @throws NoSuchElementException si la pila está vacía.
     */
    public T mira() {
		if (esVacia()) 
			throw new NoSuchElementException();
		return cabeza.elemento;
    }

    /**
     * Nos dice si la pila está vacía.
     * @return <code>true</code> si la pila no tiene elementos,
     *         <code>false</code> en otro caso.
     */
    public boolean esVacia() {
		return cabeza == null;
    }

	/**
	 * Método para obtener la longitud de la pila
	 * @return la longitud de la pila
	 */
	public int getLongitud() {
		return longitud;
	}

}
