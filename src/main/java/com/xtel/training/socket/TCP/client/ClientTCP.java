package com.xtel.training.socket.TCP.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientTCP {
    public static void main(String[] args) throws IOException {
        String sentence_to_server;
        String sentence_from_server;

        //tao inputStream(tu ban phim)
        System.out.print("Input from client: ");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        //lay chuoi ky tu nhap tu ban phim
        sentence_to_server = inFromUser.readLine();
        //tao socket cho client ket noi den server qua ID address và port number
        Socket clientSocket = new Socket("192.168.1.191", 6543);
        //tao outputStream noi voi Socket
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        //tao inputStream noi voi Socket
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //gui chuoi ky tu toi Server thong qua outputStream da noi voi Socket o tren
        outToServer.writeBytes(sentence_to_server + '\n');
        //doc tin tu Server thong qua InputSteam da noi voi socket
        sentence_from_server = inFromServer.readLine();
        System.out.println("FROM SERVER: " + sentence_from_server);
        //dong lien ket socket
        clientSocket.close();
    }
}
