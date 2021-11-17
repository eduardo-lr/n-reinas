package ed.aplicaciones;

public class Main {

    public static void main(String[] args) {

        Pila<Integer> pila = new Pila<>();
		pila.mete(5);
		pila.mete(3);
		pila.mete(8);
		pila.mete(2);
		for (int entero: pila)
			System.out.println(entero);
    }
}
