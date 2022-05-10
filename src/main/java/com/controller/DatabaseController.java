package com.controller;

import com.model.Movie;
import com.model.User;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class DatabaseController {
    private String db_url = "jdbc:mysql://localhost/IMDBClone";
    private String user_name = "root";
    private String password = "";

    public boolean insertUser(@NotNull User user){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create User Table if not exists
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
    public User getUser(@NotNull User user){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create User Table if not exists
            createUserTable();

            String id = "";
            String firstname = "";
            String lastname = "";
            String email = "";
            int isAdmin = 0;
            String userName = "";
            String password = "";
            String query = "SELECT * FROM USER WHERE email = '" + user.geteMail() + "' AND PASSWORD ='" + user.getPassword() + "'";
            Statement getData = con.prepareStatement(query);
            ResultSet result = getData.executeQuery(query);
            if(result != null) {
                while(result.next()) {
                    id = result.getString("ID");
                    firstname = result.getString("FIRSTNAME");
                    lastname = result.getString("LASTNAME");
                    email = result.getString("EMAIL");
                    isAdmin = result.getInt("ISADMIN");
                    userName = result.getString("USERNAME");
                    password = result.getString("PASSWORD");
                }
            }
            boolean isAdminbool = false;
            if(isAdmin == 1){
                isAdminbool = true;
            }
            getData.close();
            if(email.equals(user.geteMail()) && password.equals(user.getPassword())) {
                User loginUser = new User(userName,firstname,lastname,email,password,isAdminbool);
                return loginUser;
            }else{
                return null;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertMovie(ArrayList<Movie> movie){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){

            //Create Movie Tables if not exists
            createMovieTable();
            createGenreTable();
            createPerson();
            createRolle();
            createMovieGenreTable();
            createMoviePersonRolle();

            //insert movie objects
            for(int x = 0; x < movie.size(); x++){
                System.out.println(x);
                //getting MovieID
                int movieID = getMovieID(movie.get(x));

                // insert movie into movie table
                if(movieID == 0){
                    Statement insertMovie = con.createStatement();
                    insertMovie.execute("INSERT INTO MOVIE (title, releaseDate, length, posterScr)" +
                            "VALUES ('" + movie.get(x).getTitle().replaceAll("\'", "") + "', " + movie.get(x).getReleaseDate() + ", " + movie.get(x).getLength() + ", '" + movie.get(x).getPosterSrc() + "')");
                    insertMovie.close();
                }else{
                    //TODO update Movie
                }

                //insert genre
                insertGenre(movie.get(x));

                //getting genreID
                ArrayList genreList = new ArrayList();
                genreList = getGenre(movie.get(x));


                //insert genre to movie
                for(int z = 0; z < genreList.size(); z++) {
                    int genreID = 0;
                    String queryRegisseur = "SELECT * FROM moviegenre WHERE genreID = " + genreList.get(z).toString() + " AND movie_id = " + movieID + "";
                    Statement getData = con.prepareStatement(queryRegisseur);
                    ResultSet result = getData.executeQuery(queryRegisseur);
                    if (result != null) {
                        while (result.next()) {
                            genreID = result.getInt("genreID");
                        }
                    }
                    getData.close();
                    //getDataMovieID.close();
                    if(genreID == 0 && Integer.parseInt((String) genreList.get(z)) > 0 && movieID > 0) {
                        //if(movieIdExists > 0){
                        //System.out.println(genreID);
                        //System.out.println(genreList.get(z));
                        //System.out.println(movieID);

                        Statement insertMovie = con.createStatement();
                            insertMovie.execute("INSERT INTO MOVIEGENRE (GENREID, MOVIE_ID)" +
                                    "VALUES (" + Integer.parseInt((String) genreList.get(z)) + ", " + movieID + ")");
                            insertMovie.close();
                        //}
                    }
                }

                //insert Person
                //regisseur ID = 1
                //author ID = 2
                //cast ID = 3
                ArrayList<Integer> personIDRegisseur = new ArrayList<>();
                ArrayList<Integer> personIDAuthor = new ArrayList<>();
                ArrayList<Integer> personIDCast = new ArrayList<>();
                insertPerson(movie.get(x));
                personIDRegisseur = getPersonIDRegisseur(movie.get(x));
                personIDAuthor = getPersonIDAuthor(movie.get(x));
                personIDCast = getPersonIDCast(movie.get(x));

                //insert person into linking table
                for(int z = 0; z < personIDRegisseur.size(); z++ ){
                    int personID = 0;
                    if(personIDRegisseur != null) {
                        String queryRegisseur = "SELECT * FROM MOVIEPERSONROLLE WHERE personID = " + personIDRegisseur.get(z) + " AND movie_id = " + movieID + " AND rolleid = 1";
                        Statement getData = con.prepareStatement(queryRegisseur);
                        ResultSet result = getData.executeQuery(queryRegisseur);
                        if (result != null) {
                            while (result.next()) {
                                personID = result.getInt("PersonID");
                            }
                        }
                        getData.close();
                    }
                    if(personID == 0 && personIDRegisseur.get(z) > 0 && movieID >0) {
                        Statement insertMovie = con.createStatement();
                        insertMovie.execute("INSERT INTO MOVIEPERSONROLLE(movie_id, personid, rolleid)" +
                                "VALUES (" + movieID + ", " + personIDRegisseur.get(z) + ", 1)");
                        insertMovie.close();
                    }
                }
                for(int z = 0; z < personIDAuthor.size(); z++ ){

                    int personID = 0;
                    if(personIDAuthor != null) {
                        String queryRegisseur = "SELECT * FROM MOVIEPERSONROLLE WHERE personID = " + personIDAuthor.get(z) + " AND movie_id = " + movieID + " AND rolleid = 2";
                        Statement getData = con.prepareStatement(queryRegisseur);
                        ResultSet result = getData.executeQuery(queryRegisseur);
                        if (result != null) {
                            while (result.next()) {
                                personID = result.getInt("PersonID");
                            }
                        }
                        getData.close();
                    }
                    if(personID == 0 && movieID > 0 && personIDAuthor.get(z) > 0) {
                        Statement insertMovie = con.createStatement();
                        insertMovie.execute("INSERT INTO MOVIEPERSONROLLE(movie_id, personid, rolleid)" +
                                "VALUES (" + movieID + ", " + personIDAuthor.get(z) + ", 2)");
                        insertMovie.close();
                    }
                }
                for(int z = 0; z < personIDCast.size(); z++ ){

                    int personID = 0;
                    if(personIDCast != null) {
                        String queryRegisseur = "SELECT * FROM MOVIEPERSONROLLE WHERE personID = " + personIDCast.get(z) + " AND movie_id = " + movieID + " AND rolleid = 3";
                        Statement getData = con.prepareStatement(queryRegisseur);
                        ResultSet result = getData.executeQuery(queryRegisseur);
                        if (result != null) {
                            while (result.next()) {
                                personID = result.getInt("PersonID");
                            }
                        }
                        getData.close();
                    }
                    if(personID == 0 && movieID >0 && personIDCast.get(z) > 0) {
                        Statement insertMovie = con.createStatement();
                        insertMovie.execute("INSERT INTO MOVIEPERSONROLLE(movie_id, personid, rolleid)" +
                                "VALUES (" + movieID + ", " + personIDCast.get(z) + ", 3)");
                        insertMovie.close();
                    }
                }

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //TODO getPersonID
    public ArrayList getPersonIDCast(Movie movie) throws SQLException {
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)) {
            ArrayList<Integer> peronID = new ArrayList<Integer>();
            if(movie.getListCast() != null) {
                for(int x = 0; x < movie.getListCast().size(); x++) {
                    String name = movie.getListCast().get(x);
                    if (name != "") {
                        String nameArr[] = name.split(" ", 2);
                        String firstname = nameArr[0];
                        String lastname = nameArr[1];
                        int regisseurID = 0;
                        String queryRegisseur = "SELECT * FROM Person WHERE firstname = '" + firstname.replaceAll("\'", "") + "' AND lastname = '" + lastname.replaceAll("\'", "") + "'";
                        Statement getData = con.prepareStatement(queryRegisseur);
                        ResultSet result = getData.executeQuery(queryRegisseur);
                        if (result != null) {
                            while (result.next()) {
                                peronID.add(result.getInt("PersonID"));
                            }
                        }
                        getData.close();
                    }
                }
            }
            return peronID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList getPersonIDAuthor(Movie movie) throws SQLException {
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)) {
            ArrayList<Integer> peronID = new ArrayList<Integer>();
            if(movie.getListAuthor() != null) {
                for(int x = 0; x < movie.getListAuthor().size(); x++) {

                    //TODO check if exists

                    String name = movie.getListAuthor().get(x);
                    if (name != "") {
                        String nameArr[] = name.split(" ", 2);
                        String firstname = nameArr[0];
                        String lastname = nameArr[1];
                        String queryRegisseur = "SELECT * FROM Person WHERE firstname = '" + firstname.replaceAll("\'", "") + "' AND lastname = '" + lastname.replaceAll("\'", "") + "'";
                        Statement getData = con.prepareStatement(queryRegisseur);
                        ResultSet result = getData.executeQuery(queryRegisseur);
                        if (result != null) {
                            while (result.next()) {
                                peronID.add(result.getInt("PersonID"));
                            }
                        }
                        getData.close();
                    }
                }
            }
            return peronID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList getPersonIDRegisseur(Movie movie) throws SQLException {
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)) {
            ArrayList<Integer> peronID = new ArrayList<Integer>();
            if(movie.getRegisseur() != null) {
                String name = movie.getRegisseur();
                if (name != "") {
                    String nameArr[] = name.split(" ", 2);
                    String firstname = nameArr[0];
                    String lastname = nameArr[1];
                    int regisseurID = 0;
                    String queryRegisseur = "SELECT * FROM Person WHERE firstname = '" + firstname.replaceAll("\'", "") + "' AND lastname = '" + lastname.replaceAll("\'", "") + "'";
                    Statement getData = con.prepareStatement(queryRegisseur);
                    ResultSet result = getData.executeQuery(queryRegisseur);
                    if (result != null) {
                        while (result.next()) {
                            peronID.add(result.getInt("PersonID"));
                        }
                    }
                    getData.close();
                }
            }
            return peronID;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertPerson(Movie movie){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)) {
            //regisseur ID = 1
            //author ID = 2
            //cast ID = 3

            //insert Person
            //regisseur
            if(movie.getRegisseur() != null) {
                String name = movie.getRegisseur();
                if(name != ""){
                    String nameArr[] = name.split(" ", 2);
                    String firstname = nameArr[0];
                    String lastname = nameArr[1];
                    int regisseurID = 0;
                    String queryRegisseur = "SELECT * FROM Person WHERE firstname = '" + firstname.replaceAll("\'", "") + "' AND lastname = '" + lastname.replaceAll("\'", "") +"'";
                    Statement getData = con.prepareStatement(queryRegisseur);
                    ResultSet result = getData.executeQuery(queryRegisseur);
                    if(result != null) {
                        while(result.next()) {
                            regisseurID = result.getInt("PersonID");
                        }
                    }

                    getData.close();
                    if(regisseurID == 0) {
                        Statement insertMovie = con.createStatement();
                        insertMovie.execute("INSERT INTO PERSON (FIRSTNAME, LASTNAME)" +
                                "VALUES ('" + firstname.replaceAll("\'", "") + "', '" + lastname.replaceAll("\'", "") + "')");
                        insertMovie.close();
                    }
                }
            }
            //autor
            if(movie.getListAuthor() != null) {
                for(int x = 0; x < movie.getListAuthor().size(); x++) {
                    String name = movie.getListAuthor().get(x);
                    if (name != "") {
                        String nameArr[] = name.split(" ", 2);
                        String firstname = nameArr[0];
                        String lastname = nameArr[1];
                        int regisseurID = 0;
                        String queryRegisseur = "SELECT * FROM Person WHERE firstname = '" + firstname.replaceAll("\'", "") + "' AND lastname = '" + lastname.replaceAll("\'", "") + "'";
                        Statement getData = con.prepareStatement(queryRegisseur);
                        ResultSet result = getData.executeQuery(queryRegisseur);
                        if (result != null) {
                            while (result.next()) {
                                regisseurID = result.getInt("PersonID");
                            }
                        }
                        getData.close();
                        if (regisseurID == 0) {
                            Statement insertMovie = con.createStatement();
                            insertMovie.execute("INSERT INTO PERSON (FIRSTNAME, LASTNAME)" +
                                    "VALUES ('" + firstname.replaceAll("\'", "") + "', '" + lastname.replaceAll("\'", "") + "')");
                            insertMovie.close();
                        }

                    }
                }
            }
            //cast
            if(movie.getListCast() != null) {
                for(int x = 0; x < movie.getListCast().size(); x++) {
                    String name = movie.getListCast().get(x);
                    if (name != "") {
                        String nameArr[] = name.split(" ", 2);
                        String firstname = nameArr[0];
                        String lastname = nameArr[1];
                        int regisseurID = 0;
                        String queryRegisseur = "SELECT * FROM Person WHERE firstname = '" + firstname.replaceAll("\'", "") + "' AND lastname = '" + lastname.replaceAll("\'", "") + "'";
                        Statement getData = con.prepareStatement(queryRegisseur);
                        ResultSet result = getData.executeQuery(queryRegisseur);
                        if (result != null) {
                            while (result.next()) {
                                regisseurID = result.getInt("PersonID");
                            }
                        }
                        getData.close();
                        if (regisseurID == 0) {
                            Statement insertMovie = con.createStatement();
                            insertMovie.execute("INSERT INTO PERSON (FIRSTNAME, LASTNAME)" +
                                    "VALUES ('" + firstname.replaceAll("\'", "") + "', '" + lastname.replaceAll("\'", "") + "')");
                            insertMovie.close();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //getting MovieID
    public int getMovieID(Movie movie) throws SQLException {
        int movieID = 0;
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)) {
            //check if movie does exists
            String queryGetMovieTitle = "SELECT * FROM Movie WHERE title = '" + movie.getTitle().replaceAll("\'", "") + "'";
            Statement getMovie = con.prepareStatement(queryGetMovieTitle);
            ResultSet resultMovie = getMovie.executeQuery(queryGetMovieTitle);
            if (resultMovie != null) {
                while (resultMovie.next()) {
                    movieID = resultMovie.getInt("MOVIE_ID");
                }
            }
        }


        return movieID;
    }

    //getGenre
    public ArrayList getGenre(Movie movie) throws SQLException {
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)) {
            ArrayList genreID = new ArrayList();
            for (int i = 0; i < movie.getListGenre().size(); i++) {
                String queryGetGenre = "SELECT * FROM GENRE WHERE genre = '" + movie.getListGenre().get(i) + "'";
                Statement getGenre = con.prepareStatement(queryGetGenre);
                ResultSet resultGenre = getGenre.executeQuery(queryGetGenre);
                if (resultGenre != null) {
                    while (resultGenre.next()) {
                        genreID.add(resultGenre.getString("GENREID"));
                    }
                }
            }
            return genreID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //inserting genre
    public void insertGenre(Movie movie){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)) {
            //check if genre does exists
            int genreID = 0;
            for(int i = 0; i < movie.getListGenre().size(); i++) {
                ResultSet resultGenre = null;
                genreID = 0;
                ArrayList genre = new ArrayList<>();
                String queryGetGenre = "SELECT * FROM GENRE WHERE GENRE = '" + movie.getListGenre().get(i) + "'";
                System.out.println(queryGetGenre);
                Statement getGenre = con.prepareStatement(queryGetGenre);
                resultGenre = getGenre.executeQuery(queryGetGenre);
                if (resultGenre != null) {
                    while (resultGenre.next()) {
                        genreID = (resultGenre.getInt("GENREID"));
                    }
                    //create a new genre if it doesnt exists
                    if (genreID > 0) {

                    } else {
                        Statement insertMovie = con.createStatement();
                        insertMovie.execute("INSERT INTO GENRE (genre)" +
                                "VALUES ('" + movie.getListGenre().get(i) + "')");
                        insertMovie.close();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void createUserTable(){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create User Table if not exists
            Statement stmt = con.createStatement();
            String sqlCreateUser = "CREATE TABLE IF NOT EXISTS USER " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " isAdmin INTEGER, " +
                    " userName VARCHAR(255), " +
                    " password VARCHAR(255))";
            System.out.println(sqlCreateUser);
            stmt.executeUpdate(sqlCreateUser);
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createMovieTable(){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create Movie Table if not exists
            Statement stmt = con.createStatement();
            String sqlCreateMovie = "CREATE TABLE IF NOT EXISTS MOVIE " +
                    "(movie_id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "title VARCHAR(255), " +
                    "releaseDate INTEGER, " +
                    "length INTEGER, " +
                    "posterScr VARCHAR(500))";
            System.out.println(sqlCreateMovie);
            stmt.executeUpdate(sqlCreateMovie);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createGenreTable(){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create Genre Table if not exists
            Statement stmt = con.createStatement();
            String sqlCreateGenre = "CREATE TABLE IF NOT EXISTS genre " +
                    "(genreid INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " genre VARCHAR(255));";
            System.out.println(sqlCreateGenre);
            stmt.executeUpdate(sqlCreateGenre);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createMovieGenreTable(){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create MovieGenre Table if not exists
            Statement stmt = con.createStatement();
            String sqlCreateMovieGenre = "CREATE TABLE IF NOT EXISTS MovieGenre " +
                    "(genreid INTEGER, " +
                    " movie_id INTEGER, " +
                    " FOREIGN KEY (genreid) REFERENCES Genre(genreid), "+
                    " FOREIGN KEY (movie_id) REFERENCES Movie(movie_id))";
            System.out.println(sqlCreateMovieGenre);
            stmt.executeUpdate(sqlCreateMovieGenre);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPerson(){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create Person Table if not exists
            Statement stmt = con.createStatement();
            String sqlCreatePerson = "CREATE TABLE IF NOT EXISTS Person " +
                    "(personID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " firstname VARCHAR(255), " +
                    " lastname VARCHAR(255))";
            System.out.println(sqlCreatePerson);
            stmt.executeUpdate(sqlCreatePerson);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createRolle(){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create Person Table if not exists
            Statement stmt = con.createStatement();
            String sqlCreateRolle = "CREATE TABLE IF NOT EXISTS rolle " +
                    "(rolleid INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " rolleName VARCHAR(255))";
            System.out.println(sqlCreateRolle);
            stmt.executeUpdate(sqlCreateRolle);
            stmt.close();


            //regisseur ID = 1
            //author ID = 2
            //cast ID = 3
            ArrayList rolle = new ArrayList();
            ArrayList rolleToAdd = new ArrayList();
            rolleToAdd.add("regisseur");
            rolleToAdd.add("author");
            rolleToAdd.add("cast");

            String queryRolle = "SELECT * FROM rolle";
            Statement getData = con.prepareStatement(queryRolle);
            ResultSet result = getData.executeQuery(queryRolle);
            if (result != null) {
                while (result.next()) {
                    rolle.add(result.getInt("rolleID"));
                }
            }
            if (rolle.size() == 0) {
                for(int x = 0; x<rolleToAdd.size(); x++){
                    Statement insertMovie = con.createStatement();
                    insertMovie.execute("INSERT INTO ROLLE (rolleName)" +
                            "VALUES ('"+ rolleToAdd.get(x) +"')");
                    insertMovie.close();
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createMoviePersonRolle(){
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //Create MoviePersonRolle Table if not exists
            Statement stmt = con.createStatement();
            String sqlCreateMoviePersonRolle = "CREATE TABLE IF NOT EXISTS MoviePersonRolle " +
                    "(movie_id INTEGER, FOREIGN KEY (movie_id) REFERENCES Movie(movie_id), " +
                    " rolleid INTEGER, FOREIGN KEY (rolleid) REFERENCES rolle(rolleid), " +
                    " personid INTEGER, FOREIGN KEY (personid) REFERENCES person(personID))";
            System.out.println(sqlCreateMoviePersonRolle);
            stmt.executeUpdate(sqlCreateMoviePersonRolle);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
