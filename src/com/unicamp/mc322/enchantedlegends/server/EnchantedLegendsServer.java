package com.unicamp.mc322.enchantedlegends.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EnchantedLegendsServer extends Thread {
    private int port;
    private ServerSocket serverSocket;
    private static final EnchantedLegendsServer instance = new EnchantedLegendsServer();

    public EnchantedLegendsServer() {
        this(23171);
    }

    public EnchantedLegendsServer(int port) {
        this.port = port;

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (Exception exception) {
            System.err.println("Exception creating EnchantedLegendsServer: " + exception.getCause());
        }
    }

    public static EnchantedLegendsServer getInstance() {
        return instance;
    }

    public void start() {
        System.out.println("Server listening port " + port);
        Socket client;
        try {
            client = serverSocket.accept();
            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            this.serverSocket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
    }
}
