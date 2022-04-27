package com.model;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class User {
    public User(){
        super();
    }

    public User (String userName,String givenName, String familyName, String eMail, String password,boolean isSystemAdmin){
    this.givenName = givenName;
    this.familyName = familyName;
    this.eMail = eMail;
    this.password = password;
    this.isSystemAdmin = isSystemAdmin;
    this.userName = userName;
    }

    private String userName;
    private String givenName;
    private String familyName;
    private String eMail;
    private String password;
    private boolean isSystemAdmin;

    public String getUserName() { return userName; }
    public String getGivenName() { return givenName; }
    public String getFamilyName() { return familyName; }
    public String geteMail() { return eMail; }
    public String getPassword() { return password; }
    public boolean isSystemAdmin() { return isSystemAdmin; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setGivenName(String givenName) { this.givenName = givenName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }
    public void seteMail(String eMail) { this.eMail = eMail; }
    public void setPassword(String password) { this.password = password; }
    public void setSystemAdmin(boolean systemAdmin) { isSystemAdmin = systemAdmin; }

    @Override
    public String toString() {
        return "userName='" + userName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", password='" + password + '\'' +
                ", isSystemAdmin=" + isSystemAdmin;
    }

    public static void main(String[] args) {

    }
}


