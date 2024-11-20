package com.sebasydidac.Server;

import com.sebasydidac.Clientes.FilClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author sebas & didac
 */
class ServerTCP {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(7778);
            while (true) {
                // Esperem que es connecti un client
                System.out.println("Esperant nou client...");
                Socket connexio = server.accept();
                System.out.println("Client " + connexio.getInetAddress().getHostAddress() + " connectat.");
                new FilClient(connexio).start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
