package com.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class myServer {



    public static void start() throws IOException {



        int requestPort = 5008;
        int newClientPort = 5009;



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
    public static void sendJsonToClient(Scanner input ,PrintWriter output,String userJson){
        output.println(userJson);
        output.flush();
    }
    public static void sendJsonToDatabase(String userJson, int databasePort) throws IOException {
        Socket databaseSocket = new Socket("localhost",databasePort);
        PrintWriter output = new PrintWriter(new OutputStreamWriter(databaseSocket.getOutputStream()));
        output.println(userJson);
        output.flush();
        output.close();
    }




}













