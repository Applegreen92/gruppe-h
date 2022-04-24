package com.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private final int port = 8000;

    public void startListening(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    Socket userSocket = serverSocket.accept();

                    Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(userSocket.getInputStream())));
                    if (scanner.hasNextLine()){
                        System.out.println("[Server] Message from Client: "+scanner.nextLine());
                    }
                    //Verbindungen schliesen
                    scanner.close();
                    userSocket.close();
                    serverSocket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
