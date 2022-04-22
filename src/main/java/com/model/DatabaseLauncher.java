package com.model;
import com.controller.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseLauncher {
    private String db_url = "jdbc:mysql://localhost/IMDBClone";
    private String user_name = "root";
    private String password = "";

    public void insertMovie() throws SQLException {
        // DB-Connection
        try(JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(db_url,user_name,password)){
            //Create Movie Table if not exists
            TableUtils.createTableIfNotExists(connectionSource, Movie.class);
            Dao<Movie, Integer> TeacherDao = DaoManager.createDao(connectionSource, Movie.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
