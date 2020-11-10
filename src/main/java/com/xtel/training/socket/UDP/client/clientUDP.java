package com.xtel.training.socket.UDP.client;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class clientUDP {
    public static void main(String args[]) throws Exception {
        //tao socket phia client
        DatagramSocket clientSocket = new DatagramSocket(6789);
        //tao dia chi IP address boi ten localhost)
        InetAddress IPAddress = InetAddress.getByName("localhost");

        while(true){
            //tao du lieu(group of bytes) cho goi tin nhan va goi tin gui
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[2048];
//
            //lay dong van ban nhap tu ban phim va gan cho bien sentence
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            String sentence = inFromUser.readLine();
//
            //tao du lieu gui voi du lieu vua nhap tu ban phim
            sendData = sentence.getBytes();
            //tao goi tin de truyen di
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            //truyen den server bang phuong thuc send()
            clientSocket.send(sendPacket);
//
            //tao goi tin nhan
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            //lay bien receivePacket de nhan goi tin bang phuong thuc receive()
            clientSocket.receive(receivePacket);

            //Chuyển dữu liệu trong gói tin vừa nhận thành kiểu String và in ra
            String modified_Sentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modified_Sentence);

            if(sentence.compareTo("quit") == 0)
                break;
        }
        //Đóng socket
        clientSocket.close();
    }
}
