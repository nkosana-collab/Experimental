package org.nkosana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {
    public static void main(String[] args) {

        // STEP 1: Open the port.
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            // STEP 2: Wait for a visitor.
            Socket clientSocket = serverSocket.accept();

            // STEP 3: Open the "mouth" (Output)
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);

            // STEP 4: Open the "Ear" (Output)
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}