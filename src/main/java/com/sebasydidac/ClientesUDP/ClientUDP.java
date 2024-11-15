package com.sebasydidac.ClientesUDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author sebas & didac
 */
public class ClientUDP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InetAddress serverAddress;
        int serverPort = 7777;
        boolean control = true;
        DatagramSocket socket;
        try {
            serverAddress = InetAddress.getByName("192.168.18.105");
            socket = new DatagramSocket();

            while(control) {
                System.out.println("Elige:");
                System.out.println("- CREAR puerto_juego");
                System.out.println("- UNIRME");
                System.out.println("- SALIR");
                String msg = sc.nextLine();
                //enviar missatge a servidor
                byte[] bytesOUT = msg.getBytes();
                DatagramPacket outPacket = new DatagramPacket(bytesOUT, bytesOUT.length, serverAddress, serverPort);
                socket.send(outPacket);
                //rebre echo del servidor
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String receive = new String(packet.getData()).trim();
                String [] receiveSplit = receive.split("::");
                switch (receiveSplit[0]){
                    case "ADDED":
                        //AQUI LLAMAMOS AL SERVER TCP
                        //
                        break;
                    case "OCCUPIED":
                        System.out.println("El puerto ya ha sido ocupado, prueba con otro");
                        break;
                    case "EMPTY":
                        System.out.println("No hay más juegos, prueba de nuevo");
                        break;
                    case "SALIR":
                        control = false;
                        break;
                    case "ERROR":
                        System.out.println("Ha ocurrido un error inesperado, intenta de nuevo");
                        break;
                    default:
                        //AQUI VA EL CODIGO DE LLAMAR AL CLIENT TCP
                        break;
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
/**
 * @author sebas & didac
 */
class ServerTCP{
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
/**
 * @author sebas & didac
 */
class ClientTCP{
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
