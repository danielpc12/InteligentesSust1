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
public class BeamSearch {
    public static void busquedaBeamSearchM(char[][] mapa, int filaInicial, int columnaInicial, int filaFinal, int columnaFinal, int beamWidth) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        boolean[][] visitado = new boolean[mapa.length][mapa[0].length];
        int[][] padreFila = new int[mapa.length][mapa[0].length];
        int[][] padreColumna = new int[mapa.length][mapa[0].length];
        int contador = 1;

        colaPrioridad.offer(new Nodo(filaInicial, columnaInicial, 0));
        visitado[filaInicial][columnaInicial] = true;

        while (!colaPrioridad.isEmpty()) {
            PriorityQueue<Nodo> colaSiguientePrioridad = new PriorityQueue<>();
            int nodosExpandidos = 0;
            while (!colaPrioridad.isEmpty() && nodosExpandidos < beamWidth) {
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

                        int nuevoCosto = actual.costo + 1 + Heuristica.manhattan(nuevaFila, nuevaColumna, filaFinal, columnaFinal);
                        colaSiguientePrioridad.offer(new Nodo(nuevaFila, nuevaColumna, nuevoCosto));
                        visitado[nuevaFila][nuevaColumna] = true;
                        padreFila[nuevaFila][nuevaColumna] = filaActual;
                        padreColumna[nuevaFila][nuevaColumna] = columnaActual;
                    }
                }

                nodosExpandidos++;
            }

            if (visitado[filaFinal][columnaFinal]) {
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
                    System.out.println("(" + posicion[0] + ", " + posicion[1] + ")");
                }
                System.out.println("Costo del camino: " + contador);
                break;
            }

            colaPrioridad = colaSiguientePrioridad;

            contador++;
        }              

        if (!visitado[filaFinal][columnaFinal]) {
            System.out.println("No se encontró un camino desde el punto inicial al punto final.");
        }
    }

    public static void busquedaBeamSearchE(char[][] mapa, int filaInicial, int columnaInicial, int filaFinal, int columnaFinal, int beamWidth) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        boolean[][] visitado = new boolean[mapa.length][mapa[0].length];
        int[][] padreFila = new int[mapa.length][mapa[0].length];
        int[][] padreColumna = new int[mapa.length][mapa[0].length];
        int contador = 1;

        colaPrioridad.offer(new Nodo(filaInicial, columnaInicial, 0));
        visitado[filaInicial][columnaInicial] = true;

        while (!colaPrioridad.isEmpty()) {
            PriorityQueue<Nodo> colaSiguientePrioridad = new PriorityQueue<>();
            int nodosExpandidos = 0;
            while (!colaPrioridad.isEmpty() && nodosExpandidos < beamWidth) {
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

                        int nuevoCosto = actual.costo + 1 + Heuristica.euclidiana(nuevaFila, nuevaColumna, filaFinal, columnaFinal);
                        colaSiguientePrioridad.offer(new Nodo(nuevaFila, nuevaColumna, nuevoCosto));
                        visitado[nuevaFila][nuevaColumna] = true;
                        padreFila[nuevaFila][nuevaColumna] = filaActual;
                        padreColumna[nuevaFila][nuevaColumna] = columnaActual;
                    }
                }

                nodosExpandidos++;
            }

            if (visitado[filaFinal][columnaFinal]) {
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
                    System.out.println("(" + posicion[0] + ", " + posicion[1] + ")");
                }
                System.out.println("Costo del camino: " + contador);
                break;
            }

            colaPrioridad = colaSiguientePrioridad;

            contador++;
        }

        if (!visitado[filaFinal][columnaFinal]) {
            System.out.println("No se encontró un camino desde el punto inicial al punto final.");
        }
    }
    
}
