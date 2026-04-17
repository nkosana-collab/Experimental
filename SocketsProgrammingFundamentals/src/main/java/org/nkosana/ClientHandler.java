package org.nkosana;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{

    public Socket client;
    public ClientHandler(Socket clientSocket) {
        client = clientSocket;
    }

    @Override
    public void run() {
        //while(true){
            //try {
                new Thread(() -> {
                    while(true) {
                        // STEP 3: Open the "mouth" (Output)
                        PrintWriter out = null;
                        try {
                            out = new PrintWriter(client.getOutputStream(), true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        Scanner text = new Scanner(System.in);
                        System.out.println("DEBUG: ");
                        String line = text.nextLine();

                        out.println(line);
                    }
                }).start();


                new Thread(() -> {
                    while(true) {
                        try {
                            // STEP 4: Open the "Ear" (Output)
                            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                            System.out.println("BUG: " + in.readLine());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
    }
}

