package com.model;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.*;

public class DatabaseLauncher {
    private String db_url = "jdbc:mysql://localhost/IMDBClone";
    private String user_name = "root";
    private String password = "";

    public void insertMovie(Movie m) throws SQLException {
        //DB-Connection
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create Movie Table if not exists

            //TableUtils.createTableIfNotExists((ConnectionSource) con, Movie.class);

            //Dao<Movie, Integer> movieDao = DaoManager.createDao((ConnectionSource) con, Movie.class);
            //movieDao.create(m);

        }
    }

    public void insertUser(User user){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create Movie Table if not exists
            createUserTable();
            Statement stmt = con.createStatement();

            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM USER WHERE email = "+ user.geteMail() + " AND password = "+ user.getPassword());

            int isAdminNum = 0;
            if(user.getSystemAdmin() == true){
                isAdminNum = 1;
            }
            // insert the data
            if(rs == null) {
                stmt.executeUpdate("INSERT INTO USER (firstname, lastname, isAdmin, userName)" +
                        "VALUES (" + user.getGivenName() + ", " + user.getFamilyName() + ", " + isAdminNum + ", " + user.getUserName() + ")");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void getUser(User user){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create Movie Table if not exists
            createUserTable();

            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM USER WHERE email = "+ user.geteMail() + " AND password = "+ user.getPassword());

            if(rs == null) {
                return;
            }else{
                System.out.println("Passt!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUserTable(){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create Movie Table if not exists
            Statement stmt = con.createStatement();
            String sqlCreateUser = "CREATE TABLE IF NOT EXISTS USER " +
                    "(id INTEGER not NULL, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " isAdmin INTEGER," +
                    " userName VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            System.out.println(sqlCreateUser);
            stmt.executeUpdate(sqlCreateUser);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
