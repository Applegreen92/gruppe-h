package com.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
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

                    Scanner input = new Scanner(new BufferedReader(new InputStreamReader(userSocket.getInputStream())));
                    String userString = input.nextLine();
                    ObjectMapper om = new ObjectMapper();
                    User user = om.readValue(userString,User.class);


                    // This is the the fruit of my work i tell ya !
                    System.out.println(user.toString());

                    //Verbindungen schliesen

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
