package ed.aplicaciones;

/**
 * Clase principal del proyecto.
 */
public class Main {

    public static void main(String[] args) {

		/* Algunos casos de prueba. */
		int[] casos = {1,2,3,5,8,23};

		for (int i : casos) {
			Tablero tablero = new Tablero(i);
			System.out.println(tablero.resuelveNReinas());
		}
    }
}
