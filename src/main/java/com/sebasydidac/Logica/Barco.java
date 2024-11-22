package com.sebasydidac.Logica;

import java.util.List;

public class Barco {
    private String[] posiciones;
    private Boolean[] posicionesTocadas;
    private int tipo;

    public void Barco(){

    }

    public void Barco(String[] posiciones,int tipo) {
        this.posiciones = posiciones;
        this.posicionesTocadas = new Boolean[posiciones.length];
        this.tipo = tipo;
    }

    public boolean isTocado(String posX, String posY) {

        boolean tocado = false;

        for (int i = 0; i < posiciones.length; i++) {

            if(posiciones[i].split(";")[0].equals(posX) &&
                posiciones[i].split(";")[1].equals(posY)) {
                posicionesTocadas[i] = true;
                tocado = true;
            }

        }
        return tocado;
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

}
