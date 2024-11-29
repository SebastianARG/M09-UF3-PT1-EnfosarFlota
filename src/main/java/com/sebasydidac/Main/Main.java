package com.sebasydidac.Main;
import com.sebasydidac.Logica.Barco;
import com.sebasydidac.Logica.Tablero;
import org.fusesource.jansi.AnsiConsole;
public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();

        Barco b1 = tablero.getBarco(1);
        Barco b2 = tablero.getBarco(2);
        Barco b3 = tablero.getBarco(3);
        System.out.println(tablero.atacar("0","1"));

        tablero.imprimirTablero();
        // Desinstala Jansi al finalizar
        AnsiConsole.systemUninstall();
    }
}