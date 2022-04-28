package com;

import com.model.*;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {


/*
        Server server = new Server();
        server.startListening();
        //User user = new User("Aladin","Hans","Jürgen","Hans-Jürgen@web.de","12345",false);
        Client client = new Client();
        client.sendUser();
        client.loginGetUserData("Aladin","12345");*/



        Crawler insertMovies = new Crawler();
        insertMovies.getMoviesByGenre("action", 1, 2000,2006);

       //DatabaseLauncher createTable = new DatabaseLauncher();
       //createTable.insertMovie();


        //Test for poster by genre: appears to be fully functional


    }
}
