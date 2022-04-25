
package com.model;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Client {

    Socket clientSocket;
    User user = new User("Hans","Jürgen","Hans-Jürgen@web.de","12345",false);

    public Client() {
        try {
            this.clientSocket= new Socket("127.0.0.1",8000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendUser (){
        try {

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(clientSocket.getOutputStream())));
            writer.write("Hi");
            writer.flush();
            writer.close();

            //oWriter.writeObject(user);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        }
    }




