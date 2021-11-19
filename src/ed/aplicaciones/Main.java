package ed.aplicaciones;

/**
 * Clase principal del proyecto.
 */
public class Main {

    public static void main(String[] args) {
		Tablero tablero = new Tablero(8);
		System.out.printf(tablero.resuelveNReinas());
    }
}
