package org.nkosana;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientSide {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            System.out.println("Connected to the chat server!");

            // 1. Start a background thread to "Listen" (The Ear)
            new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("\nIncoming: " + serverMessage);
                        System.out.print("You: "); // Keep the prompt visible
                    }
                } catch (IOException e) {
                    System.out.println("Connection to server lost.");
                }
            }).start();

            // 2. Main thread handles "Talking" (The Mouth)
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("You: ");
                    String userInput = scanner.nextLine();
                    out.println(userInput);

                    if (userInput.equalsIgnoreCase("exit")) break;
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
