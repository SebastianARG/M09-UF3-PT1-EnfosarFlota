package com.sebasydidac.Main;
import com.sebasydidac.Logica.Barco;
import com.sebasydidac.Logica.Tablero;
import org.fusesource.jansi.AnsiConsole;
public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();

        Barco b1 = tablero.getBarco(1);
        Barco b2 = tablero.getBarco(2);
        b1.marcarTocado(1);b1.marcarTocado(2);
        b2.marcarTocado(2);
        tablero.imprimirTablero();
        // Desinstala Jansi al finalizar
        AnsiConsole.systemUninstall();
    }
}