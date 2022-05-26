package com.example.application.data.generator;

import com.example.application.data.service.*;
import com.example.application.data.entity.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.String;
//todo import logging functionality
    //import com.model.log;
import java.io.IOException;
import java.util.ArrayList;



public class Crawler {
    private final MoviePersonPartLinkService moviePersonPartLinkService;
    ArrayList<Movie> MovieList = new ArrayList<Movie>();
    ArrayList hreflink = new ArrayList();
    ArrayList posterLink = new ArrayList();
    ArrayList movieGenreArray = new ArrayList();

    ArrayList cast = new ArrayList();
    ArrayList writer = new ArrayList();
    ArrayList director = new ArrayList();

    int numTotalMovies = 0;


    Elements body = null;
    Document movies = null;
    int pass = 1;

    public Crawler(MoviePersonPartLinkService moviePersonPartLinkService) {
        this.moviePersonPartLinkService = moviePersonPartLinkService;
    }
    //TODO Tobi das Movie Objekt erstellen
    public void insertMovieIntoDB(ArrayList<Movie> MovieList){
        moviePersonPartLinkService.addNewMovie(MovieList);
    }


    public void getMoviesByGenre(String genre, int start, int startDate, int endDate) throws IOException {
        try {
            int totalDiff = endDate - startDate + 1;
            int diff = endDate - startDate + 1;
            int count = 0;
            if (diff >= 12) {
                count = 3000 / diff;
            } else {
                count = 250;
            }

            //getting the total summ of movies
            for (int x = 0; x < diff; x++) {
                int startDateForTotalAmountMovies = startDate + x;

                //getting the Movies Elements
                getMovies(startDateForTotalAmountMovies, endDate, genre, count, start);

                //filter on the total amount of movies
                Elements numOfMoviesElement = movies.select("div.desc");
                Elements numOfMoviesSpan = numOfMoviesElement.select("span");
                Element firstElmenetOfNumOfMoviesSpan = numOfMoviesSpan.first();

                //getting the number of the String
                if (firstElmenetOfNumOfMoviesSpan.text().length() <= 11) {
                    numTotalMovies += Integer.parseInt((firstElmenetOfNumOfMoviesSpan.text().split(" ")[0]).replaceAll("[^a-zA-Z0-9]", ""));
                    //System.out.println(numTotalMoviesPerYear);
                } else {
                    numTotalMovies += Integer.parseInt((firstElmenetOfNumOfMoviesSpan.text().split(" ")[2]).replaceAll("[^a-zA-Z0-9]", ""));
                    //System.out.println(numTotalMoviesPerYear);
                }
            }
            //Looping until you get the num of total movies or you have at least 3000
            while ((MovieList.size() < 3000) && (MovieList.size() < numTotalMovies)) {

                //Reset the StartDate for Loop & link creation
                int startDateForLoop = startDate;
                int countMovies = 0;
                while ((countMovies < 3000) && (countMovies < numTotalMovies)) {
                    //Navigates to the IMDB website based on the passed genre and copy's html-code into the document 'movies'
                    if (startDate > 1 && endDate > 1) {
                        if (endDate < startDate) {
                            System.out.println("Error! endDate is greater then startDate!");
                        } else {
                            diff = endDate - startDateForLoop;
                        }
                    }
                    if (count > 250) {
                        count = 250;
                    }
                    getMovies(startDateForLoop, endDate, genre, count, start);

                    body = movies.select("div.lister-list");
                    //System.out.println(body);

                    if (!body.isEmpty()) {
                        getPoster(body);
                        //System.out.println(posterLink.toString());

                        //Elements movieImage = movies.select("img.loadlate");

                        //Loading the Image links into an ArrayList
                        //for(Element movieImage: movies.select("img.loadlate")){
                        //    String moviePoster = movieImage.attr("loadlate");
                        //    posterLink.add(moviePoster);
                        //    //System.out.println(moviePoster);
                        //}

                        //lister.item is the list with the movie entries.
                        for (Element movie : body.select("h3, lister-item-header")) {
                            Elements movieLink = movie.select("a[href^=/title/tt]");
                            String linkHref = movieLink.attr("href");
                            hreflink.add(linkHref);
                        }

                        for (int x = 0; x < hreflink.size(); x++) {
                            System.out.println("Movies: " + hreflink.size());
                            System.out.println("Movie: " + x);
                            //Creating the new Document foreach movie
                            String movieUrl = "https://www.imdb.com" + hreflink.get(x);
                            Document focusMovie = Jsoup.connect(movieUrl).get();

                            //getting the Header Element f the Movies contains title, length, release
                            Elements movieHeader = focusMovie.select("div.sc-94726ce4-1");

                            Elements movieHeaderList = movieHeader.select("ul.ipc-inline-list");
                            int countLiElement = 1;
                            String length = "";

                            //looping for the Length
                            for (Element movieHeaderListItem : movieHeaderList.select("li.ipc-inline-list__item")) {
                                if (countLiElement >= 3) {
                                    //selects the length
                                    //System.out.println("length");
                                    length = movieHeaderListItem.text();
                                    System.out.println(length);
                                    countLiElement = 1;
                                } else {
                                    countLiElement += countLiElement;
                                }
                            }
                            //Selects the Title
                            String title = movieHeader.select("h1").text();

                            //selects the release and age
                            String release = movieHeader.select("span.sc-8c396aa2-2").text();
                            //cutting the age off
                            if (release.length() > 4) {
                                release = release.substring(0, 4);
                            }

                            //WORKING getting Genres
                            Elements movieGenres = focusMovie.select("div.sc-16ede01-4");
                            for (Element movieGenreList : movieGenres.select("a.sc-16ede01-3")) {
                                String movieGenre = movieGenreList.text();
                                movieGenreArray.add(movieGenre);
                            }

                            //3rd website
                            Elements moviePersons = focusMovie.select("div.sc-fa02f843-0");
                            Elements movieDirector = moviePersons.select("li, href.cast");

                            for (Element persons : movieDirector.select("ul, li")) {
                                String person = persons.text();
                                sortPersons(person);
                            }


                            System.out.println(title);
                            System.out.println(length);
                            System.out.println(release);
                            if(writer.size() == 0){
                                writer.add("");
                            }
                            if(cast.size() == 0){
                                cast.add("");
                            }
                            if(movieGenreArray.size() == 0){
                                movieGenreArray.add("");
                            }
                            if(director.size() == 0){
                                director.add("");
                            }
                            if(x == 149){
                                System.out.println("Test");
                            }
                            for(int y = 0; writer.size() > y; y++){
                                System.out.print(writer.get(y));
                            };
                            if(release == ""){
                                release = "0";
                            }
                            ArrayList tempGenreArray = new ArrayList();
                            tempGenreArray.addAll(movieGenreArray);
                            ArrayList tempWriterArray = new ArrayList();
                            tempWriterArray.addAll(writer);
                            ArrayList tempCastArray = new ArrayList();
                            tempCastArray.addAll(cast);
                            //Collections.copy(movieGenreArray, tempGenreArray);
                            Movie movie = new Movie(title,
                                    posterLink.get(x).toString(),
                                    Integer.parseInt(release),
                                    convertLength(length),
                                    tempGenreArray,
                                    director.get(0).toString(),
                                    tempWriterArray,
                                    tempCastArray);
                            MovieList.add(movie);
                            //Create Log data
                            Log movieLogger = new Log();
                            movieLogger.createLog(movie);
                            clearAllLists();
                        }
                        countMovies += hreflink.size();
                    }
                    if (diff > 0) {
                        startDateForLoop += 1;
                    } else if (diff == 0) {
                        start += 250;
                    }
                    posterLink.clear();
                    hreflink.clear();
                }
                countMovies = 0;
                count = (3000 - (count * totalDiff * pass)) / totalDiff;
                pass = pass + 1;
            }
            //TODO <DB controller flag>
            insertMovieIntoDB(MovieList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void getPoster(Elements body) {
        for (Element movieImage : body.select("img.loadlate")) {
            String moviePoster = movieImage.attr("loadlate");
            posterLink.add(moviePoster);
            //System.out.println(moviePoster);
        }
    }

    public void getMovies(int startDate, int endDate, String genre, int count, int start) throws IOException {
        if ((genre == "" || genre == null) && (startDate > 1 && endDate > 1)) {
            movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&release_date=" + startDate + "-01-01," + startDate + "-12-31&count=" + count + "&start=" + start + "&ref_=adv_nxt").get();
        } else if (startDate < 1 && endDate < 1) {
            movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&genres=" + genre + "&start=" + start + "&count=" + count + "&explore=genres&ref_=adv_nxt").get();
        } else if (startDate == 2000 && endDate == 2022) {
            movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&release_date=" + startDate + "-01-01," + startDate + "-12-31&count=137&start=1&ref_=adv_nxt").get();
        } else if ((startDate > 1 && endDate >= startDate) && genre != "" || genre == null) {
            movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&genres=" + genre + "&release_date=" + startDate + "-01-01," + startDate + "-12-31&count=" + count + "&start=" + start + "&ref_=adv_nxt").get();
        } else if (startDate > 1 && endDate >= startDate) {
            movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&release_date=" + startDate + "-01-01," + startDate + "-12-31&count=" + count + "&start=" + start + "&ref_=adv_nxt").get();
        }
    }

    public void sortPersons(String person) {
        if (person.startsWith("Stars")) {
            person = cutOccupation(person);
            insertCast(person);
        } else if (person.startsWith("Director")) {
            cutOccupation(person);
            director.add(person);
        } else if (person.startsWith("Writer")) {
            insertWriter(person);

        }
    }

    public String cutOccupation(String employee) {
        String result = employee.substring(employee.indexOf(' ') + 1);
        return result;
    }

    public void insertCast(String persons) {
        ArrayList personenArray = new ArrayList();
        //String personArr[] = persons.split(" ", 2);
        persons = persons.replaceAll("\\(.*\\).", " ");
        persons = persons.replaceAll("\\(.*\\).", " ");
        persons = persons.replaceAll("\\(.*\\).*", " ");
        persons = persons.replaceAll("\\(.*\\).*", " ");
        String personsRest = persons;
        // && personsRest != ""
        while (personsRest != null) {
            String personArr2[] = personsRest.split(" ", 2);
            //personArr2[0] = personArr2[0].replaceAll("\\(.*", "");
            personenArray.add(personArr2[0]);
            //System.out.println(personArr2[0]);
            boolean inBounds = (1 >= 0) && (1 < personArr2.length);
            if (personenArray.get(personenArray.size() - 1).equals(personArr2[0]) || personenArray.get(personenArray.size() - 1).equals("")) {
                if (inBounds) {
                    personsRest = personArr2[1];
                } else {
                    for(int x = 0; x < personenArray.size(); x++){

                        if(personenArray.size() % 2 != 0){
                            personenArray.add("");
                        }

                        this.cast.add(personenArray.get(x) + " " + personenArray.get(x + 1));
                        x = x + 1;

                    }
                    return;
                }
            }
        }
    }

    public void insertWriter (String persons){
        ArrayList personenArray = new ArrayList();
        persons = persons.replaceAll("\\(.*\\).", " ");
        persons = persons.replaceAll("\\(.*\\).", " ");
        persons = persons.replaceAll("\\(.*\\).*", " ");
        persons = persons.replaceAll("\\(.*\\).*", " ");
        //&& personsRest != ""
        String personArr[] = persons.split(" ", 2);
        String personsRest = personArr[1];
        while (personsRest != null) {
            String personArr2[] = personsRest.split(" ", 2);
            //personArr2[0] = personArr2[0].replaceAll("\\(.*", "");
            personenArray.add(personArr2[0]);
            //System.out.println(personArr2[0]);
            boolean inBounds = (1 >= 0) && (1 < personArr2.length);
            if (personenArray.get(personenArray.size() - 1).equals(personArr2[0]) || personenArray.get(personenArray.size() - 1).equals("")) {
                if (inBounds) {
                    personsRest = personArr2[1];
                } else {
                    for(int x = 0; x < personenArray.size(); x++){
                        if(personenArray.size() % 2 != 0){
                            personenArray.add("");
                        }
                        this.writer.add(personenArray.get(x) + " " + personenArray.get(x+1));
                        x = x+1;
                    }
                    return;
                }
            }
        }
    }



    public void clearAllLists(){
        movieGenreArray.removeAll(movieGenreArray);
        cast.removeAll(cast);
        director.removeAll(director);
        writer.removeAll(writer);
    }

    public int convertLength(String runtime) {
        int res = 0;
        int hours, min10, min;
        if ( runtime != "") {
            if (runtime.length() >= 6) {
                hours = Integer.parseInt(String.valueOf(runtime.charAt(0))) * 60;
                min10 = Integer.parseInt(String.valueOf(runtime.charAt(3))) * 10;
                min = Integer.parseInt(String.valueOf(runtime.charAt(4)));
                res = hours + min + min10;
            } else {
                hours = Integer.parseInt(String.valueOf(runtime.charAt(0))) * 60;
                if(runtime.length() == 4) {
                    min = Integer.parseInt(String.valueOf(runtime.charAt(3)));
                }else{
                    min = 0;
                }
                res = hours + min;
            }
        }
        else{
            res = 0;
        }
        return res;
    }


}
