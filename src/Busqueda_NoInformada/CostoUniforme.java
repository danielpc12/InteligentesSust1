/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda_NoInformada;

import inteligentes.Nodo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import inteligentes.Nodo;

/**
 *
 * @author Lenovo T480
 */
public class CostoUniforme {
    public static void busquedaCostoUniforme(char[][] mapa, int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        boolean[][] visitado = new boolean[mapa.length][mapa[0].length];
        int[][] padreFila = new int[mapa.length][mapa[0].length];
        int[][] padreColumna = new int[mapa.length][mapa[0].length];
        int contador = 1;

        colaPrioridad.offer(new Nodo(filaInicial, columnaInicial, 0));
        visitado[filaInicial][columnaInicial] = true;

        while (!colaPrioridad.isEmpty()) {
            Nodo actual = colaPrioridad.poll();
            int filaActual = actual.fila;
            int columnaActual = actual.columna;

            if (filaActual == filaFinal && columnaActual == columnaFinal) {
                break;
            }

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

                    int nuevoCosto = actual.costo + 1;
                    colaPrioridad.offer(new Nodo(nuevaFila, nuevaColumna, nuevoCosto));
                    visitado[nuevaFila][nuevaColumna] = true;
                    padreFila[nuevaFila][nuevaColumna] = filaActual;
                    padreColumna[nuevaFila][nuevaColumna] = columnaActual;

                    // Imprimir el número de iteración y la posición visitada
                    System.out.println("Iteración " + contador + ": (" + nuevaFila + ", " + nuevaColumna + ")");
                    contador++;
                }
            }
        }

        if (!visitado[filaFinal][columnaFinal]) {
            System.out.println("No se encontró un camino desde el punto inicial al punto final.");
        } else {
            System.out.println("Camino encontrado:");
            List<Integer[]> camino = new ArrayList<>();
            Integer[] actual = new Integer[]{filaFinal, columnaFinal};
            while (actual[0] != filaInicial || actual[1] != columnaInicial) {
                camino.add(actual);
                int filaPadre = padreFila[actual[0]][actual[1]];
                int columnaPadre = padreColumna[actual[0]][actual[1]];
                actual = new Integer[]{filaPadre, columnaPadre};
            }
            camino.add(actual);
            Collections.reverse(camino);
            for (Integer[] posicion : camino) {
                System.out.println("(" + posicion[0] + ", " + posicion[1] + ")");
            }
            
            camino.add(new Integer[]{filaInicial, columnaInicial});
            Collections.reverse(camino);

            for (int i = 0; i < camino.size(); i++) {
                Integer[] posicion = camino.get(i);
                int fila = posicion[0];
                int columna = posicion[1];
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
