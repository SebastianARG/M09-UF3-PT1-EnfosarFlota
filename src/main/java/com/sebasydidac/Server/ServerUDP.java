package com.sebasydidac.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sebas & didac
 */
public class ServerUDP {
    public static void main(String[] args) {
        int port = 7777;
        DatagramSocket socket;
        List<String> cola = new ArrayList<String>();
        try {
            socket = new DatagramSocket(port);
            System.out.printf("Escoltant al port [%d]...", port);
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("Esperant un nou paquet...");
                try {
                    //missatge del client
                    socket.receive(packet);
                    String msg = new String(packet.getData()).trim();
                    String address = packet.getAddress().getHostAddress();
                    System.out.printf("El cliente:[%s] envió [%s]\n",address,msg);
                    String[] msgSplit = msg.split(" ");
                    String response;
                    switch (msgSplit[0]){
                        case "CREAR":
                            //Devuelve added::null si se añade a la lista la ip::puerto
                            //Si ese puerto no esta ocupado
                            if(!existePuerto(msgSplit[1], cola)){
                                cola.add(String.format("%s::%s",address,msgSplit[1]));
                                response = "ADDED::NULL";
                            }else{
                                //devuelve occupied si ya existe ese puerto en la lista
                                response = "OCCUPIED::NULL";
                            }
                            break;
                        case "UNIRME":
                            //devuelve empty::null si no hay juegos
                            //y devuelve ip::puerto si hay juegos
                            response = cola.isEmpty()?"EMPTY::NULL":cola.removeFirst();
                            break;
                        case "SALIR":
                            //Devuelve salir para que cliente salga
                            response = "SALIR::NULL";
                            break;
                        default:
                            //Si se produce un error inesperado
                            response = "ERROR::NULL";
                            break;
                    }
                    byte[] bytesOUT = response.getBytes();
                    System.out.println(packet.getSocketAddress().toString());
                    DatagramPacket outPacket = new DatagramPacket(bytesOUT, bytesOUT.length, packet.getSocketAddress());
                    socket.send(outPacket);

                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean existePuerto(String puerto, List<String> cola) {
        String[] puertos;
        if(!cola.isEmpty()){
            for(String c : cola){
                puertos = c.split("::");
                if(puertos[1].equals(puerto)){
                    return true;
                }
            }
        }
        return false;
    }
}
