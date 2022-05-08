package com.model;

import com.controller.DatabaseController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class myServer {



    public static void start() throws IOException {



        int requestPort = 5010;
        int newClientPort = 5011;


        while (true) {

            ServerSocket serverSocket = new ServerSocket(requestPort);
            /*Create a temporary connection on our request port 10 to send
              a port for stable connection to every Client */

            System.out.println("Waiting for Client ...");
            Socket clientSocketTemp = serverSocket.accept();



            PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientSocketTemp.getOutputStream()));
            pw.println(newClientPort);
            pw.flush();
            serverSocket.close();
            ServerSocket stableConnectedServerSocket = new ServerSocket(newClientPort);
            Socket stableConnectionSocket = stableConnectedServerSocket.accept();
            pw.close();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("[Server] : waiting for input ...");
                    while(stableConnectionSocket.isConnected()) {



                        try {

                            ObjectMapper om = new ObjectMapper();
                            Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(stableConnectionSocket.getInputStream())));
                            PrintWriter pw = new PrintWriter(new OutputStreamWriter(stableConnectionSocket.getOutputStream()));




                            if (scanner.hasNextLine()) {
                                String inputString = scanner.nextLine();
                                System.out.println(inputString);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("create Streams failed");
                            throw new RuntimeException(e);
                        }

                    }


                }
            }).start();

            newClientPort++;
        }

    }










    public static void sendJson(PrintWriter output,String Json) throws JsonProcessingException {
        DatabaseController db = new DatabaseController();
        ObjectMapper om = new ObjectMapper();
        if(Json.contains("eMail")){
            User user = om.readValue(Json,User.class);
            if(user.getRegisterFlag() == true){
                db.insertUser(user);
                System.out.println("User is created ...");
                return;
            }else if(user.getLoginFlag() == true){
                // Here you need a method to get someone out of the database and the return to the Client
                //String JsonUser = om.writeValueAsString(user);
                //output.println();
                //output.flush();
                //return;
            }
        }
    }




}













