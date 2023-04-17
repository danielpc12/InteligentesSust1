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
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Busqueda_NoInformada.Anchura;
import Busqueda_NoInformada.Profundidad;
import Busqueda_NoInformada.CostoUniforme;

import Busqueda_Informada.BeamSearch;
import Busqueda_Informada.HillClimbing;
import Busqueda_Informada.AEstrella;

public class BusquedaMapa {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        char[][] mapa = cargarMapa("C:\\Users\\Lenovo T480\\Desktop\\mapa.txt");
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }

        char[][] mapaAbecedario = cargarMapa("C:\\Users\\Lenovo T480\\Desktop\\mapa.txt");
        char letra = 'A';
        char letra2 = 'A';
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                mapa[i][j] = letra;
                letra++;
                if (letra == '[') {
                    letra = 'A';
                } else if (letra == '{') {
                    letra = 'A';
                    mapa[i][j] = letra;
                    letra++;
                }
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Seleccione el algoritmo de búsqueda:");
        System.out.println("1. Anchura");
        System.out.println("2. Profundidad");
        System.out.println("3. Costo Uniforme");
        System.out.println("4. Beam Search");
        System.out.println("5. Hill Climbing");
        System.out.println("6. A*");
        int opcion = scanner.nextInt();

        System.out.println("Ingrese las coordenadas del punto inicial (fila, columna):");
        int filaInicial = scanner.nextInt();
        int columnaInicial = scanner.nextInt();

        System.out.println("Ingrese las coordenadas de la meta (fila, columna):");
        int filaFinal = scanner.nextInt();
        int columnaFinal = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Búsqueda por Anchura");
                Anchura anchura = new Anchura();
                anchura.busquedaAnchura(mapa, filaInicial, columnaInicial, filaFinal, columnaFinal);
                break;
            case 2:
                System.out.println("Búsqueda por Profundidad");
                Profundidad profundidad = new Profundidad();
                profundidad.busquedaProfundidad(mapa, filaInicial, columnaInicial, filaFinal, columnaFinal);
                break;
            case 3:
                System.out.println("Búsqueda por Costo Uniforme");
                CostoUniforme costoUniforme = new CostoUniforme();
                costoUniforme.busquedaCostoUniforme(mapa, filaInicial, columnaInicial, filaFinal, columnaFinal);
                break;
            case 4:
                System.out.println("Búsqueda por Beam Search");
                System.out.print("Ingrese el valor del beam width: ");
                int beamWidth = scanner.nextInt();
                System.out.println("Seleccione la heurística:");
                System.out.println("1. Manhattan");
                System.out.println("2. Euclidiana");
                int heuristicaBeam = scanner.nextInt();
                BeamSearch beamSearch = new BeamSearch();
                if (heuristicaBeam == 1) {
                    beamSearch.busquedaBeamSearchM(mapa, filaInicial, columnaInicial, filaFinal, columnaFinal, beamWidth);
                } else if (heuristicaBeam == 2) {
                    beamSearch.busquedaBeamSearchE(mapa, filaInicial, columnaInicial, filaFinal, columnaFinal, beamWidth);
                } else {
                    System.out.println("Heurística no válida");
                }
                break;
            case 5:
                System.out.println("Búsqueda por Hill Climbing");
                System.out.println("Seleccione la heurística:");
                System.out.println("1. Manhattan");
                System.out.println("2. Euclidiana");
                int heuristicaHill = scanner.nextInt();
                HillClimbing hillClimbing = new HillClimbing();
                if (heuristicaHill == 1) {
                    hillClimbing.busquedaHillClimbingM(mapa, filaInicial, columnaInicial, filaFinal, columnaFinal);
                } else if (heuristicaHill == 2) {
                    hillClimbing.busquedaHillClimbingE(mapa, filaInicial, columnaInicial, filaFinal, columnaFinal);
                } else {
                    System.out.println("Heurística no válida");
                }
                break;
            case 6:
                System.out.println("Búsqueda por A*");
                System.out.println("Seleccione la heurística:");
                System.out.println("1. Manhattan");
                System.out.println("2. Euclidiana");
                int heuristicaA = scanner.nextInt();
                AEstrella aEstrella = new AEstrella();
                if (heuristicaA == 1) {
                    aEstrella.busquedaAM(mapa, filaInicial, columnaInicial, filaFinal, columnaFinal);
                } else if (heuristicaA == 2) {
                    aEstrella.busquedaAE(mapa, filaInicial, columnaInicial, filaFinal, columnaFinal);
                } else {
                    System.out.println("Heurística no válida");
                }
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    public static char[][] cargarMapa(String archivo) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(archivo));
    ArrayList<String> filasList = new ArrayList<>();
    int columnas = 0;
    while (scanner.hasNextLine()) {
        String fila = scanner.nextLine();
        if (fila.length() > 0) {
            filasList.add(fila.replaceAll(",", ""));
            columnas = Math.max(columnas, filasList.get(filasList.size()-1).length());
        }
    }
    int filas = filasList.size();
    char[][] mapa = new char[filas][columnas];
    for (int i = 0; i < filas; i++) {
        String fila = filasList.get(i);
        for (int j = 0; j < fila.length(); j++) {
            mapa[i][j] = fila.charAt(j);
        }
    }
    return mapa;
}

}
