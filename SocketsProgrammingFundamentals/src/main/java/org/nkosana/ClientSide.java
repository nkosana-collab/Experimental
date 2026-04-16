package org.nkosana;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSide {
    public static void main(String[] args) {
        // STEP 1: DIal the number
        try {
            Socket socket = new Socket("127.0.0.1", 5000);

            while(true){
                // STEP 2: Open the "Ear" (Input)
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("BUG: " + in.readLine());

                // STEP 3: Open the "Mouth" (Output)
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

                Scanner text = new Scanner(System.in);
                System.out.print("DEBUG: ");
                String line = text.nextLine();

                out.println(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
