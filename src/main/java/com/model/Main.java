package com.model;

import com.model.*;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
       Crawler insertMovies = new Crawler();
       insertMovies.getMoviesByGenre("action", 1);

       //DatabaseLauncher createTable = new DatabaseLauncher();
       //createTable.insertMovie();

    }
}
