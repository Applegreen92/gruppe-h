package com.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class User implements Serializable {
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

    @DatabaseField(id = true)
    private int userID;
    @DatabaseField(canBeNull = false)
    private String userName, givenName, familyName, eMail, password;
    @DatabaseField(canBeNull = false)
    private boolean isSystemAdmin;

    // Testing Flagging system for sending stuff around to know what is what (David)
    private boolean loginFlag = false, registerFlag = false, databaseCheck = false;



    public String getUserName() { return userName; }
    public String getGivenName() { return givenName; }
    public String getFamilyName() { return familyName; }
    public String geteMail() { return eMail; }
    public String getPassword() { return password; }
    public boolean getSystemAdmin() { return isSystemAdmin; }
    public boolean getLoginFlag() { return loginFlag; }
    public boolean getRegisterFlag() { return registerFlag; }
    public boolean getDatabaseCheck() { return databaseCheck; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setGivenName(String givenName) { this.givenName = givenName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }
    public void seteMail(String eMail) { this.eMail = eMail; }
    public void setPassword(String password) { this.password = password; }
    public void setSystemAdmin(boolean systemAdmin) { isSystemAdmin = systemAdmin; }
    public void setLoginFlag(boolean loginFlag) { this.loginFlag = loginFlag; }
    public void setRegisterFlag(boolean registerFlag) { this.registerFlag = registerFlag; }
    public void setDatabaseCheck(boolean databaseCheck) { this.databaseCheck = databaseCheck; }

    @Override
    public String toString() {
        return  "userName='" + userName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", password='" + password + '\'' +
                ", isSystemAdmin=" + isSystemAdmin;
    }

}


