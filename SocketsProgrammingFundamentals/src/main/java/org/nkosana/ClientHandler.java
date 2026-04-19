package org.nkosana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket clientSocket) {
        this.client = clientSocket;
    }

    // This allows other threads to send a message TO this specific client
    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Broadcasting: " + line);
                ServerSide.broadcast(line, this); // Send to everyone else!
            }
        } catch (IOException e) {
            System.out.println("A client disconnected.");
        } finally {
            ServerSide.clients.remove(this); // Clean up
            try { client.close(); } catch (IOException e) {}
        }
    }
}