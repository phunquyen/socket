package com.xtel.training.socket.UDP.server;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
public class serverUDP {

    public static void main(String args[]) throws Exception {
        // tao socket phia server voi so hieu cong 9876
        DatagramSocket serverSocket = new DatagramSocket(9876);
        while(true) {
            //tao bien receiveData de nhan du lieu tu goi tin den
            byte[] receiveData = new byte[1024];
            //tao sendData de nhan du lieu gui len goi tin đi
            byte[] sendData  = new byte[2048];
//
            //tao bien receivePacket de nhan goi tin tu socket
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            //nhan goi tin qua phuong thuc receive()
            serverSocket.receive(receivePacket);
            //chuyen du lieu vua nhan ve dang String
            String sentence = new String(receivePacket.getData());
            //
            //lay dia chi IP cua ben gui
            InetAddress IPAddress = receivePacket.getAddress();
            //lay so hieu cong ben gui
            int port = receivePacket.getPort();
            //xu ly du lieu vua nhan
            String sentence_to_client = sentence+ " (server accpeted!)";

            //tao goi tin de gui di client
            sendData = sentence_to_client.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            //gui goi tin di
            serverSocket.send(sendPacket);
        }
    }
}