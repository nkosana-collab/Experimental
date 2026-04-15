package org.nkosana;

import java.io.*;
import java.net.*;

public class HelloServer {
    public static void main(String[] args) {
        // Port 5000 is our "Door Number"
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000...");

            // This line blocks (pauses) until a client connects
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Create a way to send data to the client
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println("Hello from the Server! Connection successful.");

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }
}