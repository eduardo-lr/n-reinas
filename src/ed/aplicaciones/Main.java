package ed.aplicaciones;

/**
 * Clase principal del proyecto.
 */
public class Main {

    public static void main(String[] args) {
	
		if (!args[0].equals("${arg}")) {
			try {
				int i = Integer.parseInt(args[0]);
				if (i <= 0)
					throw new IllegalArgumentException();
				Tablero tablero = new Tablero(i);
				System.out.printf(tablero.resuelveNReinas());
			} catch (NumberFormatException e) {
				System.out.println("El argumento dado no es un número entero");
				System.exit(1);
			} catch (IllegalArgumentException e) {
				System.out.println("El tamaño del tablero debe ser positivo");
				System.exit(1);
			}
		} else {
			/* Algunos casos de prueba. */
			int[] casos = {1,2,3,5,8,23};

			for (int i : casos) {
				Tablero tablero = new Tablero(i);
				System.out.println(tablero.resuelveNReinas());
			}
		}
    }
}
