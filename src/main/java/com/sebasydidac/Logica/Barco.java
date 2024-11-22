package com.sebasydidac.Logica;

import java.util.List;

public class Barco {
    private String[] posiciones;
    private Boolean[] posicionesTocadas;
    private int tipo;
    //AGREGADO SEBAS
    private boolean[] tocado; // Estado de las celdas del barco, verdadero si está tocado

    public  Barco(){

    }
    public Barco(String[] posiciones, int tipo) {
        this.posiciones = posiciones;
        this.posicionesTocadas = new Boolean[posiciones.length];
        this.tipo = tipo;
        this.tocado = new boolean[posiciones.length]; // Inicializa el array tocado con el tamaño correcto
    }



    //AGREGADO SEBAS
    // Marcar la celda del barco como tocada
    public void marcarTocado(int posicion) {
        // Verifica que la posición sea válida
        if (posicion >= 0 && posicion < tocado.length) {
            tocado[posicion] = true;  // Marca como tocado
        }
    }



    public boolean isHundido(){
        Boolean hundido = false;
        int tocadas = 0;

        for (int i = 0; i < posicionesTocadas.length; i++) {
            if(posicionesTocadas[i] == true){
                tocadas++;
            }
        }

        if(tocadas == posiciones.length){
            hundido = true;
        }

        return hundido;
    }

    // GETTERS SETTERS
    public void setPosiciones(String[] posiciones){
        this.posiciones = posiciones;
    }

    public int getTipo(){
        return tipo;
    }

    public void setTipo(int tipo){
        this.tipo = tipo;
    }
    public String[] getPosiciones() {
        return posiciones;
    }
    //AGREGADO SEBAS
    public boolean[] getTocado() {
        return tocado;
    }

    //agregado sebas
    public boolean isTocado(String posX, String posY) {
        for (int i = 0; i < posiciones.length; i++) {
            String[] pos = posiciones[i].split(";");
            String barcoX = pos[0];
            String barcoY = pos[1];

            // Verificar si las coordenadas coinciden y marcar la posición como tocada
            if (barcoX.equals(posX) && barcoY.equals(posY)) {
                posicionesTocadas[i] = true; // Marcar la posición como tocada
                tocado[i] = true; // También marcar en el array tocado
                return true;
            }
        }
        return false; // Si no se encuentra la posición, devolver falso
    }





    //borrado sebas
//    public boolean isTocado(String posX, String posY) {
//
//        boolean tocado = false;
//
//        for (int i = 0; i < posiciones.length; i++) {
//
//            if(posiciones[i].split(";")[0].equals(posX) &&
//                    posiciones[i].split(";")[1].equals(posY)) {
//                posicionesTocadas[i] = true;
//                tocado = true;
//            }
//
//        }
//        return tocado;
//    }

}
