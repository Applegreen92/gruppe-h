package com.model;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class log {

    public static void createLog(Movie movie) {

        try {
            String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
            FileWriter DataWriter = new FileWriter("movielog.txt", StandardCharsets.UTF_8, true);
            //for(Movie movie : movieList) {
                DataWriter.write(time + " " +movie.toString() + "\n");
            //}
            DataWriter.close();

        } catch (IOException e) {
            System.out.println("-------------------Logging failed-----------------");
            throw new RuntimeException(e);
        }
    }
}
