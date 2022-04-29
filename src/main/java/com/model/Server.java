package com.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {


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


//    public static void main(String[] args) throws IOException {
//
//        Socket socket = null;
//        InputStreamReader inputStreamReader = null;
//        OutputStreamWriter outputStreamWriter = null;
//        BufferedReader bufferedReader = null;
//        BufferedWriter bufferedWriter = null;
//        ServerSocket serverSocket = null;
//
//        serverSocket = new ServerSocket(port);
//
//        while (true) {
//
//            try {
//
//                socket = serverSocket.accept();
//
//                inputStreamReader = new InputStreamReader(socket.getInputStream());
//                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
//
//                bufferedReader = new BufferedReader(inputStreamReader);
//                bufferedWriter = new BufferedWriter(outputStreamWriter);
//
//                while (true) {
//
//                    String msgFromClient = bufferedReader.readLine();
//
//                    System.out.println(msgFromClient);
//
//                    bufferedWriter.write("Message received");
//                    bufferedWriter.newLine();
//                    bufferedWriter.flush();
//
//                    if (msgFromClient.equalsIgnoreCase("BYE"))
//                        break;
//                }
//
//                socket.close();
//                inputStreamReader.close();
//                outputStreamWriter.close();
//                bufferedReader.close();
//                bufferedWriter.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }