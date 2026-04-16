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

        // STEP 1: Open the port.
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            // STEP 2: Wait for a visitor.
            Socket clientSocket = serverSocket.accept();

            while(true){
                // STEP 3: Open the "mouth" (Output)
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);

                Scanner text = new Scanner(System.in);
                System.out.print("DEBUG: ");
                String line = text.nextLine();

                out.println(line);

                // STEP 4: Open the "Ear" (Output)
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("BUG: " + in.readLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}