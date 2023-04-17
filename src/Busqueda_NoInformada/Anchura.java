/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda_NoInformada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Lenovo T480
 */
public class Anchura {

    public void busquedaAnchura(char[][] mapa, int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        Queue<Integer[]> cola = new LinkedList<>();
        boolean[][] visitado = new boolean[mapa.length][mapa[0].length];
        int[][] padreFila = new int[mapa.length][mapa[0].length];
        int[][] padreColumna = new int[mapa.length][mapa[0].length];
        int contador = 1;
        int nivel = 0;
        List<Integer>[] niveles = new ArrayList[mapa.length * mapa[0].length];

        cola.offer(new Integer[]{filaInicial, columnaInicial});
        visitado[filaInicial][columnaInicial] = true;
        niveles[nivel] = new ArrayList<>();
        niveles[nivel].add(filaInicial * mapa[0].length + columnaInicial);

        while (!cola.isEmpty()) {
            Integer[] actual = cola.poll();
            int filaActual = actual[0];
            int columnaActual = actual[1];

            if (filaActual == filaFinal && columnaActual == columnaFinal) {
                break;
            }

            nivel++;
            niveles[nivel] = new ArrayList<>();

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

                    cola.offer(new Integer[]{nuevaFila, nuevaColumna});
                    visitado[nuevaFila][nuevaColumna] = true;
                    padreFila[nuevaFila][nuevaColumna] = filaActual;
                    padreColumna[nuevaFila][nuevaColumna] = columnaActual;
                    niveles[nivel].add(nuevaFila * mapa[0].length + nuevaColumna);

                    // Imprimir el número de iteración y la posición visitada
                    System.out.println("Iteración " + contador + ": (" + nuevaFila + ", " + nuevaColumna + ")");
                    contador++;
                }
            }
        }

        if (!visitado[filaFinal][columnaFinal]) {
            System.out.println("No se encontró un camino desde el punto inicial al punto final.");
        } else {
            System.out.println("Camino encontrado en el nivel " + nivel + ":");
            List<Integer[]> camino = new ArrayList<>();
            Integer[] actual = new Integer[]{filaFinal, columnaFinal};
            while (actual[0] != filaInicial || actual[1] != columnaInicial) {
                camino.add(actual);
                int filaPadre = padreFila[actual[0]][actual[1]];
                int columnaPadre = padreColumna[actual[0]][actual[1]];
                actual = new Integer[]{filaPadre, columnaPadre};
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
