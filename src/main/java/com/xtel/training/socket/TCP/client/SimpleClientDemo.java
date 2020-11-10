package com.xtel.training.socket.TCP.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClientDemo {
    public static void main(String[] args) {
        // dia chi may chu
        final String serverHost = "localhost";
        Socket socketOfClient = null;
        BufferedWriter os = null;
        BufferedReader is = null;

        try {
            // gui yeu cau ket noi toi server dang lang nghe
            // localhost, cong 9999
            socketOfClient = new Socket(serverHost, 9999);
            // tao luong dau ra tai client (gui du lieu toi server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            // luong dau vao tai client (nhan du lieu tu server)
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Error host" + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("Can't get I/O for the connecion to" + serverHost);
            return;
        }
        try {
            // ghi du lieu vao luong dau ra cua socket tai client
            os.write("hello");
            os.newLine(); // ket thuc dong
            os.flush(); // day du lieu di
            os.write("Phu");
            os.newLine();
            os.flush();
            os.write("quit");
            os.newLine();
            os.flush();

            // doc du lieu tra loi tu phia server bang cach doc luong dau vao cua socket tai client
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                if (responseLine.indexOf("OK") != -1) {
                    break;
                }
            }
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host:" + e);
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }
    }
}
