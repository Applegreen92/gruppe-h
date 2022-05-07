package com.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.String;

import java.util.Arrays;
import java.io.IOException;
import java.util.ArrayList;


public class Crawler {

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


    public void getMoviesByGenre(String genre, int start, int startDate, int endDate) throws IOException {
        try {
            int totalDiff = endDate-startDate + 1;
            int diff = endDate-startDate + 1;
            int count= 0;
            if(diff>=12) {
                count = 3000 / diff;
            }else{
                count = 250;
            }

            //getting the total summ of movies
            for(int x = 0; x < diff; x++){
                int startDateForTotalAmountMovies = startDate+x;

                //getting the Movies Elements
                getMovies(startDateForTotalAmountMovies,endDate,genre,count,start);

                //filter on the total amount of movies
                Elements numOfMoviesElement = movies.select("div.desc");
                Elements numOfMoviesSpan = numOfMoviesElement.select("span");
                Element firstElmenetOfNumOfMoviesSpan = numOfMoviesSpan.first();

                //getting the number of the String
                if(firstElmenetOfNumOfMoviesSpan.text().length() <= 11){
                    numTotalMovies += Integer.parseInt((firstElmenetOfNumOfMoviesSpan.text().split(" ")[0]).replaceAll("[^a-zA-Z0-9]", ""));
                    //System.out.println(numTotalMoviesPerYear);
                }else{
                    numTotalMovies += Integer.parseInt((firstElmenetOfNumOfMoviesSpan.text().split(" ")[2]).replaceAll("[^a-zA-Z0-9]", ""));
                    //System.out.println(numTotalMoviesPerYear);
                }
            }
            //Looping until you get the num of total movies or you have at least 3000
            while((MovieList.size() < 3000) && (MovieList.size() < numTotalMovies)){

                //Reset the StartDate for Loop & link creation
                int startDateForLoop = startDate;
                int countMovies = 0;
                while ((countMovies < 3000) && (countMovies < numTotalMovies)) {
                //Navigates to the IMDB website based on the passed genre and copy's html-code into the document 'movies'
                    if(startDate > 1 && endDate > 1){
                        if(endDate < startDate) {
                            System.out.println("Error! endDate is greater then startDate!");
                        }else {
                            diff = endDate-startDateForLoop;
                        }
                    }
                if(count > 250){
                    count = 250;
                }
                getMovies(startDateForLoop,endDate,genre,count,start);

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
                            //System.out.println(movieGenreArray.toString());
                            //Movie movie = new Movie(title, movieGenres.toString(), posterLink.get(x).toString(), Integer.parseInt(release), 1, "", "", "");
                            //System.out.println(posterLink.get(x).toString());
                            //MovieList.add(movie);
                        }
                        countMovies+= hreflink.size();
                    }
                    if(diff > 0){
                        startDateForLoop+= 1;
                    }else if(diff == 0){
                        start += 250;
                    }
                    hreflink.clear();
                }
                countMovies = 0;
                count = (3000-(count*totalDiff*pass))/totalDiff;
                pass = pass +1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void getPoster(Elements body)  {
        for(Element movieImage: body.select("img.loadlate")){
            String moviePoster = movieImage.attr("loadlate");
            posterLink.add(moviePoster);
            //System.out.println(moviePoster);
        }
    }

    public void getMovies(int startDate, int endDate, String genre, int count, int start) throws IOException {
        if((genre == "" || genre == null) && (startDate > 1 && endDate > 1)) {
            movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&release_date=" + startDate + "-01-01," + startDate + "-12-31&count="+ count +"&start=" + start + "&ref_=adv_nxt").get();
        }else if(startDate < 1 && endDate < 1){
            movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&genres=" + genre + "&start=" + start + "&count="+ count +"&explore=genres&ref_=adv_nxt").get();
        }else if(startDate == 2000 && endDate == 2022){
            movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&release_date=" + startDate + "-01-01," + startDate + "-12-31&count=137&start=1&ref_=adv_nxt").get();
        }else if((startDate > 1 && endDate >= startDate) && genre != "" || genre == null){
            movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&genres=" + genre + "&release_date=" + startDate + "-01-01," + startDate + "-12-31&count="+ count +"&start=" + start + "&ref_=adv_nxt").get();
        }else if(startDate > 1 && endDate >= startDate){
            movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&release_date=" + startDate + "-01-01," + startDate + "-12-31&count="+ count +"&start=" + start + "&ref_=adv_nxt").get();
        }
    }

    public void sortPersons(String person){
        if(person.startsWith("Stars")){
            person = cutOccupation(person);
            //insertCast(person);
        } else if (person.startsWith("Director")) {
            cutOccupation(person);
            director.add(person);
        }
        else if(person.startsWith("Writer")){
            insertWriter(person);

        }
    }

    public String cutOccupation(String employee){
        String result = employee.substring(employee.indexOf(' ') + 1);
        return result;
    }

    public void insertCast(String persons){

        int count = 0;
        int space = 0;
        char[] cast = persons.toCharArray();
        char[] member = new char[50];

        for(int i = 0; i < cast.length; i++){
            member[count] = cast[i];
            count++;
            if(cast[i] == ' '){space++;}
            if(space == 2){
                String person = new String(member);
                member = new char[50];
                this.cast.add(removeBrackets(person));
                System.out.println(removeBrackets(person));
                space = 0;
                count = 0;
            }
            else if( i + 1 == cast.length){
                member[count ] = ' ';
                String person = new String(member);
                this.cast.add(person);
                System.out.println(removeBrackets(person));

            }
        }
    }

    public void insertWriter(String persons){

       /* String personsRest = persons;
        while(personsRest != null && personsRest != ""){
            String personArr[] = personsRest.split(" ", 3);
            this.writer.add(personArr[0]);
            System.out.println(personArr[0]);
            if(personArr[1] != null) {
                personsRest = personArr[1];
            }

        }*/

        int count = 0;
        int space = 0;
        char[] writer = persons.toCharArray();
        char[] member = new char[50];

        for(int i = 0; i < writer.length; i++){
            member[count] = writer[i];
            count++;
            if(writer[i] == ' '){space++;}
            if(space == 2){
                String person = new String(member);
                member = new char[50];
                this.writer.add(removeBrackets(person));
                space = 0;
                count = 0;
            }
            else if( i + 1 == writer.length){
                member[count ] = ' ';
                String person = new String(member);
                this.writer.add(removeBrackets(person));
            }
        }

    }

    public String removeBrackets(String person){
        person = person.substring(person.indexOf(' '));
        return person;

    }
}
