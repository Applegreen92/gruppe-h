package com.model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


class ServiceThread extends Thread {
    Socket socket;

    public ServiceThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            System.out.println("Client with adress " + socket.getInetAddress() + " connected.");

            Scanner input = new Scanner(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
            String userString = input.next();
            System.out.println(userString);

            //ObjectMapper om = new ObjectMapper();
            //User user = om.readValue(userString,User.class);

            System.out.println("Data from client: " + userString);


            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println(userString);
            pw.flush();
            System.out.println("Jason-Object was sent back to Client");
            System.out.println("Waiting for confirmation from Client");

            Scanner input2 = new Scanner(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
            System.out.println(input2.nextLine());



            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    public class Server {

    /*DatabaseLauncher db = new DatabaseLauncher();

    User user = new User("Aladin", "Hans", "Jürgen", "Hans-Jürgen@web.de", "12345", false);

    public void sendToDatabase() {
        db.insertUser(user);
    }*/


        User user = new User("Hans" ,"Hans-Peter","Meier", "Hans-Peter@web.de", "Peter", false);

        private static int port = 7779;

        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Started listening on port " + port);

            while (true) {

                System.out.println("Do you want to keep the server running? (yes/no)");
                Scanner scanner = new Scanner(System.in);
                String run = scanner.nextLine();
                if(run.equals("no")){
                    break;
                }

                System.out.println("Waiting for client..");
                Socket socket = serverSocket.accept();

                // create a new thread to service a client
                System.out.println("Starting a thread to service the client");
                new ServiceThread(socket).start();
            }

        }
    }


//    public void startListening(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ServerSocket serverSocket = new ServerSocket(port);
//                    System.out.println("Waiting for connection ...");
//                    Socket userSocket = serverSocket.accept();
//
//                    Scanner input = new Scanner(new InputStreamReader(new BufferedInputStream(userSocket.getInputStream())));
//                    String userString = input.next();
//                    System.out.println(userString);
//                    ObjectMapper om = new ObjectMapper();
//                    User user = om.readValue(userString,User.class);
//
//
//                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(userSocket.getOutputStream()));
//                    pw.println("User Object received: " );
//                    pw.flush();
//                    pw.close();
//
//
//
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();
//    }