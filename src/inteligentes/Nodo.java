/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteligentes;

/**
 *
 * @author Lenovo T480
 */
    public class Nodo implements Comparable<Nodo> {

        public int fila;
        public int columna;
        public int costo;

        public Nodo(int fila, int columna, int costo) {
            this.fila = fila;
            this.columna = columna;
            this.costo = costo;
        }

        @Override
        public int compareTo(Nodo o) {
            return Integer.compare(costo, o.costo);
        }
    }