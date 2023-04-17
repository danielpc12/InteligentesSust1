/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda_Informada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lenovo T480
 */
public class HillClimbing {
    public static void busquedaHillClimbingM(char[][] mapa, int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        int costo = 0;
        List<Integer[]> camino = new ArrayList<>();
        int filaActual = filaInicial;
        int columnaActual = columnaInicial;

        while (filaActual != filaFinal || columnaActual != columnaFinal) {
            int mejorFila = filaActual;
            int mejorColumna = columnaActual;
            int mejorHeuristica = Heuristica.manhattan(filaActual, columnaActual, filaFinal, columnaFinal);

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

                    if (mapa[nuevaFila][nuevaColumna] == 'M' || mapa[nuevaFila][nuevaColumna] == 'R') {
                        continue;
                    }

                    int nuevaHeuristica = Heuristica.manhattan(nuevaFila, nuevaColumna, filaFinal, columnaFinal);

                    if (nuevaHeuristica < mejorHeuristica) {
                        mejorFila = nuevaFila;
                        mejorColumna = nuevaColumna;
                        mejorHeuristica = nuevaHeuristica;
                    }
                }
            }

            if (mejorFila == filaActual && mejorColumna == columnaActual) {
                // No se encontró un camino mejor, se termina la búsqueda
                System.out.println("No se encontró un camino desde el punto inicial al punto final.");
                break;
            }

            // Se agrega el paso al camino y se actualiza la posición actual
            camino.add(new Integer[]{mejorFila, mejorColumna});
            costo++;
            filaActual = mejorFila;
            columnaActual = mejorColumna;
        }

        if (filaActual == filaFinal && columnaActual == columnaFinal) {
            // Se encontró un camino, se imprime
            System.out.println("Camino encontrado:");
            for (Integer[] posicion : camino) {
                System.out.println("(" + posicion[0] + ", " + posicion[1] + ")");
            }
            System.out.println("Costo del camino: " + costo);
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

    public static void busquedaHillClimbingE(char[][] mapa, int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        int costo = 0;
        List<Integer[]> camino = new ArrayList<>();
        int filaActual = filaInicial;
        int columnaActual = columnaInicial;

        while (filaActual != filaFinal || columnaActual != columnaFinal) {
            int mejorFila = filaActual;
            int mejorColumna = columnaActual;
            int mejorHeuristica = Heuristica.euclidiana(filaActual, columnaActual, filaFinal, columnaFinal);

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

                    if (mapa[nuevaFila][nuevaColumna] == 'M' || mapa[nuevaFila][nuevaColumna] == 'R') {
                        continue;
                    }

                    int nuevaHeuristica = Heuristica.euclidiana(nuevaFila, nuevaColumna, filaFinal, columnaFinal);

                    if (nuevaHeuristica < mejorHeuristica) {
                        mejorFila = nuevaFila;
                        mejorColumna = nuevaColumna;
                        mejorHeuristica = nuevaHeuristica;
                    }
                }
            }

            if (mejorFila == filaActual && mejorColumna == columnaActual) {
                // No se encontró un camino mejor, se termina la búsqueda
                System.out.println("No se encontró un camino desde el punto inicial al punto final.");
                break;
            }

            // Se agrega el paso al camino y se actualiza la posición actual
            camino.add(new Integer[]{mejorFila, mejorColumna});
            costo++;
            filaActual = mejorFila;
            columnaActual = mejorColumna;
        }

        if (filaActual == filaFinal && columnaActual == columnaFinal) {
            // Se encontró un camino, se imprime
            System.out.println("Camino encontrado:");
            for (Integer[] posicion : camino) {
                System.out.println("(" + posicion[0] + ", " + posicion[1] + ")");
            }
            System.out.println("Costo del camino: " + costo);
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
