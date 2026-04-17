package org.nkosana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSide {
    public static void main(String[] args) {

        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5000)) {
                while (true) {
                    Socket clientSocket = serverSocket.accept(); // Blocks here, but in its own thread
// Handle client in another thread for concurrency
                    new Thread(new ClientHandler(clientSocket)).start();
                }
            } catch (IOException e) { e.printStackTrace(); }
        }).start();

    }
}

