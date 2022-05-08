package com.model;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;



public class MyClient extends Application{
    public MyClient() throws IOException {
        /* for the Login we create a client which requests a connection over port 4 and gets a new
        port for usage from the Server afterwards since we don't have a switch for our Project.*/
        Socket requestSocket = new Socket("localhost", 5010);
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(requestSocket.getInputStream())));
        int portForConnection = scanner.nextInt();
        System.out.println("Connected to Server over port : " + portForConnection);
        scanner.close();
        this.clientSocket = new Socket("localhost", portForConnection);
        this.printwriter = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(clientSocket.getOutputStream())));
        this.scanner = new Scanner(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
    }


    private Socket clientSocket;
    public static PrintWriter printwriter;
    public static Scanner scanner;
    private User user;

    public Socket getClientSocket() {
        return this.clientSocket;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static void startClient() throws IOException {
        launch();

    }


    public static Stage current;
    public static MyClient currentClient;

    public static Stage getCurr() {
        return current;
    }


    public static MyClient getCurrClient() {
        return currentClient;
    }

    public void start(Stage stage) throws IOException {
        current = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        //FXMLLoader loader = new FXMLLoader(MyClient.class.getResource("Login.fxml"));
        Scene scene = new Scene(root);
        current.setTitle("Login");
        current.setScene(scene);
        current.show();
    }}

