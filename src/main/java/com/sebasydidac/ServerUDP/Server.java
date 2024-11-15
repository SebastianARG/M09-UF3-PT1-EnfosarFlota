package com.sebasydidac.ServerUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server {
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
                    String[] msgCutted = msg.split(" ");
                    String response;
                    switch (msgCutted[0]){
                        case "CREAR":
                            if(!existePuerto(msgCutted[1], cola)){
                                cola.add(String.format("%s::%s",address,msgCutted[1]));
                                response = "ADDED";
                            }else{
                                response = "OCCUPIED";
                            }
                            break;
                        case "UNIRME":
                            response = cola.isEmpty()?"EMPTY":cola.removeFirst();
                            break;
                        default:
                            response = "ERROR";
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