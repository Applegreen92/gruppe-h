package com.testPackage;

import java.io.*;
import java.lang.runtime.ObjectMethods;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import com.model.User;
import com.view.MainAppGUI;
import org.json.JSONObject;

import static javafx.application.Application.launch;


public class MyClient {
    public MyClient() throws IOException {



        /* for the Login we create a client wich requests a connection over port 4 and gets a new
        port for usage from the Server afterwards since we don't have a switch for our Project.*/
        Socket requestSocket = new Socket("localhost", 4999);
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(requestSocket.getInputStream())));
        int portForConnection = scanner.nextInt();
        System.out.println("Connected to Server over port : " + portForConnection);
        scanner.close();
        this.clientSocket = new Socket("localhost", portForConnection);
    }

    private Socket clientSocket;
    private User user;

















    public static void main(String[] args) throws IOException {
        new MyClient();

    }


}

