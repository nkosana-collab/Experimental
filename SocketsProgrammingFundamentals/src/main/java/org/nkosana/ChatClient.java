package org.nkosana;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            System.out.println("Connected to the server!");

            // 1. START THE "EAR" (Background Thread for Receiving)
            Thread readThread = new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String serverMessage;
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println("\nServer: " + serverMessage);
                        System.out.print("You: "); // Keep the prompt clean
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });
            readThread.start();

            // 2. START THE "MOUTH" (Main Thread for Sending)
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("You: ");
                String myMsg = scanner.nextLine();
                writer.println(myMsg);

                if (myMsg.equalsIgnoreCase("exit")) break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
