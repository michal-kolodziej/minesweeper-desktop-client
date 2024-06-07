package com.gomora.typeclicker.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Client {
    private final String ip;
    private final int port;
    private final Consumer<String> onServerMessageReceived;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String ip, int port, Consumer<String> onServerMessageReceived) {
        this.ip = ip;
        this.port = port;
        this.onServerMessageReceived = onServerMessageReceived;
    }

    public void startConnection() throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                String response;
                while ((response = in.readLine()) != null) {
                    onServerMessageReceived.accept(response);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Exception caught when trying to close resources");
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public void sendMessageToServer(String msg) {
        out.println(msg);
    }
}
