# Proyecto Aplicación de Pilas: <i>Backtracking</i>

Este proyecto resuelve el <a href="https://en.wikipedia.org/wiki/Eight_queens_puzzle">problema de las n-reinas</a> (dado un tablero de ajedrez de n × n casillas, se desea colocar n reinas sin que se coman las unas a las otras) usando el algoritmo de <a href="https://en.wikipedia.org/wiki/Backtracking"><i>backtracking</i></a>.

El programa está escrito en <a href="https://www.java.com/es/">Java</a> y se compila usando <a href="https://ant.apache.org/">Apache Ant</a>. Para ejecutarlo hay dos alternativas. Si se corre la instrucción
```sh
ant run
```

Entonces el programa se ejecuta para algunos casos de prueba, a saber, para los tableros de 1, 2, 3, 5 y 8 casillas. La salida obtenida es la siguiente: 
```sh
     [java] Tablero 1x1 
     [java] Renglón 1, columna a
     [java] 
     [java] Tablero 2x2 
     [java] No hay solución 
     [java] 
     [java] Tablero 3x3 
     [java] No hay solución 
     [java] 
     [java] Tablero 5x5 
     [java] Renglón 5, columna d
     [java] Renglón 4, columna b
     [java] Renglón 3, columna e
     [java] Renglón 2, columna c
     [java] Renglón 1, columna a
     [java] 
     [java] Tablero 8x8 
     [java] Renglón 8, columna d
     [java] Renglón 7, columna b
     [java] Renglón 6, columna g
     [java] Renglón 5, columna c
     [java] Renglón 4, columna f
     [java] Renglón 3, columna h
     [java] Renglón 2, columna e
     [java] Renglón 1, columna a
     [java] 
```

Por otro lado, el programa también acepta valores dados por el usuario. Si se desea que el programa resuelva el problema para N casillas, se debe ejecutar la instrucción:
```sh
ant run -Darg=N
```

Si el argumento proporcionado no es un número entero positivo, el programa muestra un mensaje de error y termina su ejecución. Para obtener la documentación del proyecto:
```sh
ant generate-javadoc
```
