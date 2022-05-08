package com.model;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


import com.controller.AddMovieManually;
import com.view.AddMovieApplication;
import com.view.MainAppGUI;



public class MyClient {
    public MyClient() throws IOException  {
        /* for the Login we create a client wich requests a connection over port 4 and gets a new
        port for usage from the Server afterwards since we don't have a switch for our Project.*/
        Socket requestSocket = new Socket("localhost", 4999);
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(requestSocket.getInputStream())));
        int portForConnection = scanner.nextInt();
        System.out.println("Connected to Server over port : " + portForConnection);
        scanner.close();
        this.clientSocket = new Socket("localhost", portForConnection);
        this.printwriter = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(clientSocket.getOutputStream())));
        this.scanner = new Scanner(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
    }



    private Socket clientSocket = null;
    final PrintWriter printwriter;
    final Scanner scanner;
    private User user;

    public Socket getClientSocket() { return this.clientSocket; }
    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }










    public static void main(String[] args) throws IOException {
        MyClient client = new MyClient();
        AddMovieApplication.main();
        //AddMovieManually add = new AddMovieManually(client.getClientSocket());

    }


}

