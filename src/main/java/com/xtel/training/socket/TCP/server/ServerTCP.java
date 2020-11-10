package com.xtel.training.socket.TCP.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    public static void main(String[] args) throws IOException {
        String sentence_from_client;
        String sentence_to_client;

        //tao socket server, cho tai cong 6543
        ServerSocket welcomeSocket = new ServerSocket(6543);
        while(true) {
            //cho yeu cau tu client
            Socket connectionSocket = welcomeSocket.accept();

            //tao input stream, noi toi socket
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            //tao output stream, noi toi socket
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            //doc thong tin tu socket
            sentence_from_client = inFromClient.readLine();

            sentence_to_client = sentence_from_client +" (Server accepted!)" + '\n';
            //ghi du lieu ra socket
            outToClient.writeBytes(sentence_to_client);
            return;
        }
    }
}
