package com.model;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

// A client sends messages to the server, the server spawns a thread to communicate with the client
// Each communication with a client is added to an array list so any message sent gets sent to every other client by looping through it

public class Client {

    // A client has a socket to connect to the server and a reader and writer to receive and send messages respectively
    private final InetSocketAddress socket = new InetSocketAddress(8000);

    //private BufferedReader bufferedReader;
    //private BufferedWriter bufferedWriter;

    // public Client() {
       // try {
           // this.socket = socket;

       // } catch (IOException e) {
         //  e.printStackTrace();

        //}
    }