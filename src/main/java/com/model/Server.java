package com.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    /**  @author raphaelkruska
     *  Folgendes klappt jetzt mit diesem Code: Der Client sendet ne Message und wird als User mit dem Usernamen aus der Userklasse angezeigt.
    Wenn du "BYE" schreibst beendest du automatisch die Connection.
    Probier es mal aus und guck ob es bei dir auch funktioniert! Habe deinen Code mal auskommentiert, da ich beim Ausführen immer ne Exception bekommen habe.
     */


    private final int port = 8000;



    public void startListening(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    System.out.println("Waiting for connection ...");
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