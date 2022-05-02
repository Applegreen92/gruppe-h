package com.example.guiproject;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTest {


    private final int port = 7779;



    public void startListening(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    System.out.println("Waiting for connection ...");
                    Socket userSocket = serverSocket.accept();

                    Scanner input = new Scanner(new InputStreamReader(new BufferedInputStream(userSocket.getInputStream())));
                    String userString = input.next();
                    System.out.println(userString);
                    ObjectMapper om = new ObjectMapper();
                    User user = om.readValue(userString,User.class);
                    System.out.println(user.toString());


                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(userSocket.getOutputStream()));
                    pw.println("User Object received: " );
                    pw.flush();
                    pw.close();





                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
