/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda_Informada;

import inteligentes.Nodo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Lenovo T480
 */
public class AEstrella {
    public static void busquedaAM(char[][] mapa, int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        int[][] costoAcumulado = new int[mapa.length][mapa[0].length];
        int[][] costoTotal = new int[mapa.length][mapa[0].length];
        int[][] padreFila = new int[mapa.length][mapa[0].length];
        int[][] padreColumna = new int[mapa.length][mapa[0].length];

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                costoAcumulado[i][j] = Integer.MAX_VALUE;
                costoTotal[i][j] = Integer.MAX_VALUE;
            }
        }

        colaPrioridad.offer(new Nodo(filaInicial, columnaInicial, 0));
        costoAcumulado[filaInicial][columnaInicial] = 0;
        costoTotal[filaInicial][columnaInicial] = Heuristica.manhattan(filaInicial, columnaInicial, filaFinal, columnaFinal);

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

                    if (mapa[nuevaFila][nuevaColumna] == 'M' || mapa[nuevaFila][nuevaColumna] == 'R') {
                        continue;
                    }

                    int nuevoCostoAcumulado = costoAcumulado[filaActual][columnaActual] + 1;
                    if (nuevoCostoAcumulado < costoAcumulado[nuevaFila][nuevaColumna]) {
                        costoAcumulado[nuevaFila][nuevaColumna] = nuevoCostoAcumulado;
                        costoTotal[nuevaFila][nuevaColumna] = nuevoCostoAcumulado + Heuristica.manhattan(nuevaFila, nuevaColumna, filaFinal, columnaFinal);
                        colaPrioridad.offer(new Nodo(nuevaFila, nuevaColumna, costoTotal[nuevaFila][nuevaColumna]));
                        padreFila[nuevaFila][nuevaColumna] = filaActual;
                        padreColumna[nuevaFila][nuevaColumna] = columnaActual;
                    }
                }
            }
        }

        if (costoAcumulado[filaFinal][columnaFinal] == Integer.MAX_VALUE) {
            System.out.println("No se encontró un camino desde el punto inicial al punto final.");
        } else {
            Nodo actual = new Nodo(filaFinal, columnaFinal, 0);
            List<Integer[]> camino = new ArrayList<>();
            while (actual.fila != filaInicial || actual.columna != columnaInicial) {
                camino.add(new Integer[]{actual.fila, actual.columna});
                int filaPadre = padreFila[actual.fila][actual.columna];
                int columnaPadre = padreColumna[actual.fila][actual.columna];
                actual = new Nodo(filaPadre, columnaPadre, 0);
            }
            camino.add(new Integer[]{filaInicial, columnaInicial});
            Collections.reverse(camino);
            System.out.println("Camino encontrado:");
            for (Integer[] posicion : camino) {
                System.out.println("(" + posicion[0] + "," + posicion[1] + ")");
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
    
    public static void busquedaAE(char[][] mapa, int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        int[][] costoAcumulado = new int[mapa.length][mapa[0].length];
        int[][] costoTotal = new int[mapa.length][mapa[0].length];
        int[][] padreFila = new int[mapa.length][mapa[0].length];
        int[][] padreColumna = new int[mapa.length][mapa[0].length];

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                costoAcumulado[i][j] = Integer.MAX_VALUE;
                costoTotal[i][j] = Integer.MAX_VALUE;
            }
        }

        colaPrioridad.offer(new Nodo(filaInicial, columnaInicial, 0));
        costoAcumulado[filaInicial][columnaInicial] = 0;
        costoTotal[filaInicial][columnaInicial] = Heuristica.euclidiana(filaInicial, columnaInicial, filaFinal, columnaFinal);

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

                    if (mapa[nuevaFila][nuevaColumna] == 'M' || mapa[nuevaFila][nuevaColumna] == 'R') {
                        continue;
                    }

                    int nuevoCostoAcumulado = costoAcumulado[filaActual][columnaActual] + 1;
                    if (nuevoCostoAcumulado < costoAcumulado[nuevaFila][nuevaColumna]) {
                        costoAcumulado[nuevaFila][nuevaColumna] = nuevoCostoAcumulado;
                        costoTotal[nuevaFila][nuevaColumna] = nuevoCostoAcumulado + Heuristica.euclidiana(nuevaFila, nuevaColumna, filaFinal, columnaFinal);
                        colaPrioridad.offer(new Nodo(nuevaFila, nuevaColumna, costoTotal[nuevaFila][nuevaColumna]));
                        padreFila[nuevaFila][nuevaColumna] = filaActual;
                        padreColumna[nuevaFila][nuevaColumna] = columnaActual;
                    }
                }
            }
        }

        if (costoAcumulado[filaFinal][columnaFinal] == Integer.MAX_VALUE) {
            System.out.println("No se encontró un camino desde el punto inicial al punto final.");
        } else {
            Nodo actual = new Nodo(filaFinal, columnaFinal, 0);
            List<Integer[]> camino = new ArrayList<>();
            while (actual.fila != filaInicial || actual.columna != columnaInicial) {
                camino.add(new Integer[]{actual.fila, actual.columna});
                int filaPadre = padreFila[actual.fila][actual.columna];
                int columnaPadre = padreColumna[actual.fila][actual.columna];
                actual = new Nodo(filaPadre, columnaPadre, 0);
            }
            camino.add(new Integer[]{filaInicial, columnaInicial});
            Collections.reverse(camino);
            System.out.println("Camino encontrado:");
            for (Integer[] posicion : camino) {
                System.out.println("(" + posicion[0] + "," + posicion[1] + ")");
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
