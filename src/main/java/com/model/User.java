package com.model;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class User {

    public User (String givenName, String familyName, String eMail, String password,boolean isSystemAdmin){
    this.givenName = givenName;
    this.familyName = familyName;
    this.eMail = eMail;
    this.password = password;
    this.isSystemAdmin = isSystemAdmin;
    }

    private String givenName;
    private String familyName;
    private String eMail;
    private String password;
    private boolean isSystemAdmin;

    public String getGivenName() { return givenName; }
    public String getFamilyName() { return familyName; }
    public String geteMail() { return eMail; }
    public String getPassword() { return password; }
    public boolean isSystemAdmin() { return isSystemAdmin; }



    //Server Connection stuff
    InetSocketAddress socketAddress = new InetSocketAddress(8000);
    public void sendMessage (String message)  {

        Socket socket = new Socket();
        try {
            socket.connect(socketAddress,10000);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println(message);
            pw.flush();

            pw.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
