package com.xtel.training.socket.TCP.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServerProgram {
    public static void main(String[] args) {
        ServerSocket listener = null;
        String line;
        BufferedReader is;
        BufferedWriter os;
        Socket socketOfServer = null;
        // mo mot serverSocket tai cong 9999
        try {
            listener = new ServerSocket(9999);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        try {
            System.out.println("Server is waiting to accept user...");
            // chap nhan yeu cau ket noi tu phia client va nhan duoc mot doi tuong socket tai server

            socketOfServer = listener.accept();
            System.out.println("Accept a client");
            // mo luong vao ra tren socket tai server
            is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

            // nhan duoc du lieu tu nguoi dung va gui lai tra loi
            while (true) {
                // doc du lieu toi server (do client gui toi)
                line = is.readLine();
                // ghi vao luong dau ra cua socket o server ( gui toi client)
                os.write(">>" + line);
                // ket thuc dong
                os.newLine();
                // day du lieu di
                os.flush();

                // neu nguoi dung muon ket thuc tro chuyen
                if (line.equals("quit")) {
                    os.write(">>OK");
                    os.newLine();
                    os.flush();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        System.out.println("Server Stopped!");
    }
}
