package com.crazyrabbitentertainment.server.core;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerMain {

    public static final int PORT = 5000;

    private DatagramSocket udpSocket;

    public ServerMain() {
        try {
            udpSocket = new DatagramSocket(PORT);
            System.out.println("UDP Server running...");

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                udpSocket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("UDP Client: " + msg);

                // Echo back
                DatagramPacket response = new DatagramPacket(
                        msg.getBytes(),
                        msg.length(),
                        packet.getAddress(),
                        packet.getPort()
                );
                udpSocket.send(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServerMain();
    }
}
