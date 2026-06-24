package com.crazyrabbitentertainment.client.core;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientMain {

    public ClientMain() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddr = InetAddress.getByName("localhost");

            byte[] data = "Hello server!".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddr, 5000);
            socket.send(packet);

            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            System.out.println("Server says: " +
                    new String(response.getData(), 0, response.getLength()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClientMain();
    }
}
