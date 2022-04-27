package com.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ObjectClient {

    public static void main(String[] args) throws IOException {

        User user1 = new User("Olli999", "Oliver", "Kahn", "OlliKahn999@hotmail.com", "WirBrauchenEier", false);

        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Connected!");

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


        System.out.println("Sending user-object to Server...");

        // this sends our "User" Object to the server
        // in this case it is our new User with userName "Olli999"
        objectOutputStream.writeObject(user1);

        System.out.println("Closing socket and terminating programm.");
        socket.close();
    }
}