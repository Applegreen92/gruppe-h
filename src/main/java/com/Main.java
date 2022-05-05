package com;

import com.controller.RegisterController;
import com.model.*;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {



        Server server = new Server();
        server.startListening();
        //User user = new User("Aladin","Hans","Jürgen","Hans-Jürgen@web.de","12345",false);
        Client client = new Client();

        //client.loginGetUserData("Aladin","12345");*/



        //Crawler insertMovies = new Crawler();
        //insertMovies.getMoviesByGenre("action", 1, 2000,2001);

         //Crawler insertMovies = new Crawler();
         //insertMovies.getMoviesByGenre("action", 1, 2000,2006);

       DatabaseLauncher createTable = new DatabaseLauncher();

       //Movie movie = new Movie("Test","TestBild", 2000, 1);
        createTable.createUserTable();
        User user = new User("Felix", "Bernardi", true, "test","Felix.Bernardi@web.de", "Test123");
        createTable.insertUser(user);



        //Test for poster by genre: appears to be fully functional


    }
}
