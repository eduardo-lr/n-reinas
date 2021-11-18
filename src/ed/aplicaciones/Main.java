package ed.aplicaciones;

public class Main {

    public static void main(String[] args) {
		Tablero tablero = new Tablero(8);
		try {
			tablero.resuelveNReinas();
			System.out.printf(tablero.toString());
		} catch (SinSolucionEncontrada ss) {
			System.out.println(ss.getMessage());
			System.exit(1);
		}
    }
}
