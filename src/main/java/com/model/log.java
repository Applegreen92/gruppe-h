package com.model;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class log {

    public static void createLog(ArrayList<Movie> movieList) {
        try {
            FileWriter DataWriter = new FileWriter("Filmliste.txt");
            for(Movie movie : movieList) {
                DataWriter.write(movie.toString() + "\n");
            }
            DataWriter.close();

        } catch (IOException e) {
            System.out.println("Logging failed ...");
            throw new RuntimeException(e);
        }finally {
            System.out.println("Logging failed ...");
        }
    }
}
