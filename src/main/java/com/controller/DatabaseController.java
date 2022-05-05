package com.controller;

import com.model.Movie;
import com.model.User;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class DatabaseController {
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

    public boolean insertUser(@NotNull User user){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create Movie Table if not exists
            createUserTable();
            String email = "";

            String query = "SELECT * FROM USER WHERE email = '"+ user.geteMail() +"'";
            Statement getData = con.prepareStatement(query);
            ResultSet result = getData.executeQuery(query);
            if(result != null) {
                while(result.next()) {
                    email = result.getString("EMAIL");
                }
            }
            getData.close();

            int isAdminNum = 0;
            if(user.getSystemAdmin() == true){
                isAdminNum = 1;
            }
            // insert the data
            if(email.equals(user.geteMail())) {
                return false;
            }else{
                Statement stmt = con.createStatement();
                stmt.execute("INSERT INTO USER (firstname, lastname, isAdmin, userName, password, email)" +
                        "VALUES ('" + user.getGivenName() + "', '" + user.getFamilyName() + "', " + isAdminNum + ", '" + user.getUserName() + "','" + user.getPassword() + "', '"+ user.geteMail() +"')");
                stmt.close();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean getUser(@NotNull User user){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create Movie Table if not exists
            createUserTable();

            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM USER WHERE email = "+ user.geteMail() + " AND password = "+ user.getPassword());

            if(rs == null) {
                return false;
            }else{
                return true;
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
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " isAdmin INTEGER," +
                    " userName VARCHAR(255), " +
                    " password VARCHAR(255))";
            System.out.println(sqlCreateUser);
            stmt.executeUpdate(sqlCreateUser);
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
