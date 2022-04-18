package org.openjfx;

public class User {

    private String passwort;
    private int matrNr;



    public String getPasswort() {
        return passwort;
    }

    public int getMatrNr() {
        return matrNr;
    }

    public void setPasswort (String passwort){
        this.passwort = passwort;
    }
    public void setMatrNr(int number) {
        this.matrNr = number;
    }
    public User(String passwort, int matrNr) {
        this.passwort = passwort;
        this.matrNr = matrNr;

    }

}

