
package com.model;

import java.io.*;
import java.lang.runtime.ObjectMethods;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.dao.RawRowObjectMapper;


public class Client {
    User user = new User("Aladin","Hans","Jürgen","Hans-Jürgen@web.de","12345",false);
    Socket clientSocket;

    public Client() {
        try {

            this.clientSocket= new Socket("127.0.0.1",8000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendUser (){
        try {

            String userJsonString = new ObjectMapper().writeValueAsString(this.user);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(clientSocket.getOutputStream())));
            pw.write(userJsonString);
            pw.flush();
            pw.close();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        }
    }




