package com.example.guiproject;

public class User {

    private String vorname, nachname, email, passwort;
    private int matrNr;

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }

    public int getMatrNr() {
        return matrNr;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatrNr(int matrNr) {
        this.matrNr = matrNr;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public String toString() {
        return "User{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", email='" + email + '\'' +
                ", passwort='" + passwort + '\'' +
                ", matrNr=" + matrNr +
                '}';
    }
    public User() {
        super();
    }
    public User(String vorname, String nachname, String email, String passwort, int matrNr) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.passwort = passwort;
        this.matrNr = matrNr;

    }

}