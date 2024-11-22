package com.sebasydidac.Logica;
import java.util.Random;

public class Tablero {
    private final Random rnd = new Random();
    private final int[] BARCOS = {4, 3, 2, 1}; // Cantidad de barcos de cada tamaño
    private final int[] TAMANOS = {4, 3, 2, 1}; // Tamaños de barcos correspondientes
    private Barco[] barcos;
    private String[][] tablero;

    public Tablero() {
        tablero = new String[10][10];
        barcos = new Barco[10];
        inicializarTablero();
        generarBarcos();
    }

    private void inicializarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = " ";
            }
        }
    }

    private boolean colocarBarco(int x, int y, int tamano, boolean horizontal) {
        // Verifica si el barco cabe en la posición especificada
        if (horizontal) {
            if (y + tamano > tablero.length) return false;
            for (int i = 0; i < tamano; i++) {
                if (!tablero[x][y + i].equals(".")) return false;
            }
            // Coloca el barco
            for (int i = 0; i < tamano; i++) {
                tablero[x][y + i] = "B";
            }
        } else {
            if (x + tamano > tablero.length) return false;
            for (int i = 0; i < tamano; i++) {
                if (!tablero[x + i][y].equals(".")) return false;
            }
            // Coloca el barco
            for (int i = 0; i < tamano; i++) {
                tablero[x + i][y] = "B";
            }
        }
        return true;
    }

    private void generarBarcos() {
        int index = 0;
        for (int t = 0; t < TAMANOS.length; t++) {
            int cantidad = BARCOS[t];
            int tamano = TAMANOS[t];
            for (int i = 0; i < cantidad; i++) {
                boolean colocado = false;
                while (!colocado) {
                    int x = generarNum();
                    int y = generarNum();
                    boolean horizontal = rnd.nextBoolean();
                    colocado = colocarBarco(x, y, tamano, horizontal);
                }
                barcos[index++] = new Barco(); // Aquí puedes inicializar el barco con más datos si es necesario
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private int generarNum() {
        return rnd.nextInt(10);
    }
}
