package com.unicamp.mc322.enchantedlegends.client;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnchantedLegendsClient {
    public static void main(String[] args) throws IOException {
        try {
            Socket client = new Socket("localhost", 23171);
            PrintStream out = new PrintStream( client.getOutputStream() );
            BufferedReader in = new BufferedReader( new InputStreamReader( client.getInputStream() ) );

            String line = in.readLine();
            while( line != null )
            {
                System.out.println( line );
                line = in.readLine();
            }

            // Close our streams
            in.close();
            out.close();
            client.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getCause());
        } finally {
            System.out.println("Connection to server closed.");
        }
    }
}
