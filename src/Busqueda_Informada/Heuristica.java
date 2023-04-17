/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda_Informada;

/**
 *
 * @author Lenovo T480
 */
public class Heuristica {
        public static int manhattan(int filaActual, int columnaActual, int filaFinal, int columnaFinal) {
        return Math.abs(filaActual - filaFinal) + Math.abs(columnaActual - columnaFinal);
    }

    public static int euclidiana(int filaActual, int columnaActual, int filaFinal, int columnaFinal) {
        return (int) Math.sqrt(Math.pow(filaFinal - filaActual, 2) + Math.pow(columnaFinal - columnaActual, 2));
    }
}
