package com.testPackage.stuffToDelete;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket ss = new ServerSocket(7778);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept();
        System.out.println("Connection from " + socket + "!");

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        // this prints out our Object with all its parameters as a String
        // in this case it is our "User" Object which is initialized in the ObjectClient Class
        System.out.println(objectInputStream.readObject().toString());

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream())));
        pw.write("Server: User Data received!");
        pw.flush();
        pw.close();


        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
    }

}