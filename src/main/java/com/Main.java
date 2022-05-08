package com;

import com.controller.DatabaseController;
import com.model.*;
import com.testPackage.Client;
import com.testPackage.Server;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MyClient.startClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();


                //Server server = new Server();
                //server.startListening();
                //User user = new User("Aladin","Hans","Jürgen","Hans-Jürgen@web.de","12345",false);
                //Client client = new Client();

                //client.loginGetUserData("Aladin","12345");*/


                //Crawler insertMovies = new Crawler();
                //insertMovies.getMoviesByGenre("action", 1, 2000,2001);

                //Crawler insertMovies = new Crawler();
                //insertMovies.getMoviesByGenre("action", 1, 2000,2006);
/*
        DatabaseController createTable = new DatabaseController();

        //String title,String posterSrc, int date, int length,String regisseur,ArrayList genre,ArrayList author,ArrayList cast
        ArrayList genre = new ArrayList();
        genre.add("Test");
        genre.add("Lappen");
        genre.add("123");
        genre.add("haha");
        ArrayList author = new ArrayList();
        author.add("TestAuthor2 2");
        ArrayList cast = new ArrayList();
        cast.add("TestCast2 TestCast2");
        Movie movie = new Movie("Test2", "posterSrc2",2002,145,"regisseurfirst regisseurlast", genre,author,cast);
        ArrayList testMovie = new ArrayList();
        testMovie.add(movie);
        createTable.insertMovie(testMovie);
*/
                //Movie movie = new Movie("Test","TestBild", 2000, 1);
                //createTable.createUserTable();
                //createTable.createMovieTable();
                //createTable.createGenreTable();
                //createTable.createMovieGenreTable();
                //createTable.createPerson();
                //createTable.createRolle();
                //createTable.createMoviePersonRolle();
                //User user = new User("Felix", "Bernardi", true, "test","Felixu.123@web.de", "Test123");
                //createTable.insertUser(user);
                //createTable.getUser(user);


                //Test for poster by genre: appears to be fully functional


                ;
    }


}