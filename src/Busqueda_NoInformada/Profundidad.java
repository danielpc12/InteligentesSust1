/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda_NoInformada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Lenovo T480
 */
public class Profundidad {

    public void busquedaProfundidad(char[][] mapa, int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        Stack<Integer[]> pila = new Stack<>();
        boolean[][] visitado = new boolean[mapa.length][mapa[0].length];
        int[][] padreFila = new int[mapa.length][mapa[0].length];
        int[][] padreColumna = new int[mapa.length][mapa[0].length];
        int contador = 1;

        pila.push(new Integer[]{filaInicial, columnaInicial});

        while (!pila.isEmpty()) {
            Integer[] actual = pila.pop();
            int filaActual = actual[0];
            int columnaActual = actual[1];

            if (visitado[filaActual][columnaActual]) {
                continue;
            }

            visitado[filaActual][columnaActual] = true;

            if (filaActual == filaFinal && columnaActual == columnaFinal) {
                break;
            }

            List<Integer[]> posiblesMovimientos = new ArrayList<>();

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 && j != 0) {
                        continue;
                    }

                    int nuevaFila = filaActual + i;
                    int nuevaColumna = columnaActual + j;

                    if (nuevaFila < 0 || nuevaFila >= mapa.length || nuevaColumna < 0 || nuevaColumna >= mapa[0].length) {
                        continue;
                    }

                    if (visitado[nuevaFila][nuevaColumna] || mapa[nuevaFila][nuevaColumna] == 'M' || mapa[nuevaFila][nuevaColumna] == 'R') {
                        continue;
                    }

                    int distancia = 'Z' - mapa[nuevaFila][nuevaColumna]; // Calcula la distancia a la letra "Z"

                    posiblesMovimientos.add(new Integer[]{nuevaFila, nuevaColumna, distancia});
                }
            }

            // Ordena la lista de posibles movimientos por la distancia de forma descendente
            posiblesMovimientos.sort((a, b) -> b[2] - a[2]);

            for (Integer[] movimiento : posiblesMovimientos) {
                int nuevaFila = movimiento[0];
                int nuevaColumna = movimiento[1];

                pila.push(new Integer[]{nuevaFila, nuevaColumna});
                padreFila[nuevaFila][nuevaColumna] = filaActual;
                padreColumna[nuevaFila][nuevaColumna] = columnaActual;

                // Imprimir el número de iteración y la posición visitada
                System.out.println("Iteración " + contador + ": Visitando posición (" + nuevaFila + ", " + nuevaColumna + ")");
                contador++;
            }
        }

        if (!visitado[filaFinal][columnaFinal]) {
            System.out.println("No se encontró camino.");
        } else {
            List<Integer[]> camino = new ArrayList<>();
            int fila = filaFinal;
            int columna = columnaFinal;

            while (fila != filaInicial || columna != columnaInicial) {
                camino.add(new Integer[]{fila, columna});
                int filaPadre = padreFila[fila][columna];
                int columnaPadre = padreColumna[fila][columna];
                fila = filaPadre;
                columna = columnaPadre;
            }

            camino.add(new Integer[]{filaInicial, columnaInicial});
            Collections.reverse(camino);

            System.out.println("El camino encontrado es:");
            for (Integer[] posicion : camino) {
                System.out.println("(" + posicion[0] + ", " + posicion[1] + ")");
            }
            camino.add(new Integer[]{filaInicial, columnaInicial});
            Collections.reverse(camino);

            for (int i = 0; i < camino.size(); i++) {
                Integer[] posicion = camino.get(i);
                fila = posicion[0];
                columna = posicion[1];
                mapa[fila][columna] = '+';
            }

            for (int i = 0; i < mapa.length; i++) {
                for (int j = 0; j < mapa[0].length; j++) {
                    System.out.print(mapa[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
