package com.sebasydidac.ClientesUDP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author sebas & didac
 */
public class FilClient extends Thread {

    private Socket conn;

    public FilClient(Socket conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        try {
            // Obrim els cananls de comunicació amb el client
            DataInputStream in = new DataInputStream(conn.getInputStream());
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            // Repetir l'intercanvi de dades mentre el client estigui connectat.
            int suma = 0;
            String msg = in.readUTF();
            while (!msg.equals("CERRAR")) {
                try {
                    suma += Integer.parseInt(msg);
                    System.out.println("Nombre rebut: " + msg);
                } catch (NumberFormatException e) {
                    System.out.println("Nombre mal format: " + msg);
                }

                msg = in.readUTF();

                System.out.println("Total suma: " + suma);
                out.writeUTF(String.valueOf(suma));
            }
            // Tanquem la connexió amb el client
            in.close();
            out.close();
            conn.close();
            System.out.println("Connexió tancada");
        } catch (IOException ex) {
            Logger.getLogger(FilClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}

