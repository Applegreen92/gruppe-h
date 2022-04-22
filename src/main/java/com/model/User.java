package com.model;

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


}
