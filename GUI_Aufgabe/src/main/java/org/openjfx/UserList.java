package org.openjfx;

public class UserList {
    private User top;
    private User  next;

    private UserList(User user) {
        this.top = user;
    }
    private void setNext(User user) {
        this.next = user;
    }
    private User getNext() {
        return this.next;
    }
    public boolean checkUser(int matrikelnummer, String passwort) {
        if (top == null) {
            return false;
        }
        User node = top;
        while (node != null) {
            if (node.getMatrNr() == matrikelnummer && node.getPasswort().equals(passwort)) {
                return true;
            }
            node = node;
        }
        return false;
    }
}
