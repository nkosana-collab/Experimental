package org.nkosana;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServerSide {
    public static void main(String[] args) {

        // Use a Thread Pool to manage client connections efficiently
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            while (true) {
                // 1. Accept the connection (Blocks until a client connects)
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress());

                // 2. Hand the socket off to a worker thread
                threadPool.execute(new ClientHandler(socket));
            }

        } catch (IOException ex) {
            System.err.println("Server exception: " + ex.getMessage());
        }
    }
}





//import java.io.*;
//import java.net.*;
//import java.util.concurrent.*;
//
//public class MultiClientServer {
//    public static void main(String[] args) {
//        int port = 8080;
//        // Use a Thread Pool to manage client connections efficiently
//        ExecutorService threadPool = Executors.newFixedThreadPool(2);
//
//        try (ServerSocket serverSocket = new ServerSocket(port)) {
//            System.out.println("Server is listening on port " + port);
//
//            while (true) {
//                // 1. Accept the connection (Blocks until a client connects)
//                Socket socket = serverSocket.accept();
//                System.out.println("New client connected: " + socket.getInetAddress());
//
//                // 2. Hand the socket off to a worker thread
//                threadPool.execute(new ClientHandler(socket));
//            }
//        } catch (IOException ex) {
//            System.err.println("Server exception: " + ex.getMessage());
//        }
//    }
//}
