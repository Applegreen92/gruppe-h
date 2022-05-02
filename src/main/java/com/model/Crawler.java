package com.model;

import com.gargoylesoftware.htmlunit.DownloadedContent;
import com.model.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.lang.String;

import java.io.IOException;
import java.util.ArrayList;

public class Crawler {

    ArrayList<Movie> MovieList = new ArrayList<Movie>();
    ArrayList hreflink = new ArrayList();
    ArrayList posterLink = new ArrayList();
    ArrayList movieGenreArray = new ArrayList();

    int pass = 1;


    public void getMoviesByGenre(String genre, int start, int startDate, int endDate) throws IOException {
        Elements body = null;
        Document movies = null;

        //TODO breake if a year have less movies then expected

        try {
            int totalDiff = endDate-startDate + 1;
            int diff = endDate-startDate + 1;
            int count= 0;
            if(diff>=12) {
                count = 3000 / diff;
            }else{
                count = 250;
            }
            while(MovieList.size() < 3000){

                //Reset the StartDate for Loop
                int startDateForLoop = startDate;
                int countMovies = 0;

                while (countMovies < count*totalDiff) {
                //Navigates to the IMDB website based on the passed genre and copy's html-code into the document 'movies'
                    if(startDate > 1 && endDate > 1){
                        if(endDate < startDate) {
                            System.out.println("Ob du behindert bist habe ich dich gefragt!");
                        }else {
                            diff = endDate-startDateForLoop;
                        }
                    }
                if(count > 250){
                    count = 250;
                }

                if((genre == "" || genre == null) && (startDate > 1 && endDate > 1)) {
                    movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&release_date=" + startDateForLoop + "-01-01," + endDate + "-12-31&count="+ count +"&start=" + start + "&ref_=adv_nxt").get();
                    body = movies.select("div.lister-list");
                }else if(startDate < 1 && endDate < 1){
                    movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&genres=" + genre + "&start=" + start + "&count="+ count +"&explore=genres&ref_=adv_nxt").get();
                    body = movies.select("div.lister-list");
                }else if(startDate == 2000 && endDate == 2022){
                    movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&release_date=" + startDateForLoop + "-01-01," + endDate + "-12-31&count=137&start=1&ref_=adv_nxt").get();
                    body = movies.select("div.lister-list");
                }else if((startDate > 1 && endDate > startDate) && genre != "" || genre == null){
                    movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&genres=" + genre + "&release_date=" + startDateForLoop + "-01-01," + startDateForLoop + "-12-31&count="+ count +"&start=" + start + "&ref_=adv_nxt").get();
                    body = movies.select("div.lister-list");
                }else if(startDate > 1 && endDate > startDate){
                    movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&release_date=" + startDateForLoop + "-01-01," + startDateForLoop + "-12-31&count="+ count +"&start=" + start + "&ref_=adv_nxt").get();
                    body = movies.select("div.lister-list");
                }

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
                            Elements movieLink = movie.select( "a[href^=/title/tt]");
                            String linkHref = movieLink.attr("href");
                            hreflink.add(linkHref);
                        }

                        for(int x = 0;x < hreflink.size() -1; x++){
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
                                if(countLiElement >= 3){
                                    //selects the length
                                    //System.out.println("length");
                                    length = movieHeaderListItem.text();
                                    //System.out.println(length);
                                    countLiElement = 1;
                                }else {
                                    countLiElement+= countLiElement;
                                }
                            }
                            //Selects the Title
                            String title = movieHeader.select("h1").text();
                            //selects the release and age
                            String release = movieHeader.select("span.sc-8c396aa2-2").text();
                            //cutting the age off
                            if(release.length() > 4){
                                release = release.substring(0,4);
                            }

                            //TODO (Regisseur,Drehbuchautor,Cast,Filmbanner)

                            //getting Genres
                            Elements movieGenres = focusMovie.select("div.sc-16ede01-4");
                            for (Element movieGenreList : movieGenres.select("a.sc-16ede01-3")) {
                                    String movieGenre = movieGenreList.text();
                                    movieGenreArray.add(movieGenre);
                            }

                            //getting Regisseur,Drehbuchautor,Cast

                            Elements moviePersons = focusMovie.select("div.sc-fa02f843-0");
                            //System.out.println(moviePersons);

                            Elements movieDirector = moviePersons.select("li, href.cast");

                            for(Element persons : movieDirector.select("ul, li")){
                                System.out.println(persons.text());
                            }



                            //System.out.println(title);
                            //System.out.println(length);
                            //System.out.println(release);
                            //System.out.println(movieGenreArray.toString());
                        }
                        countMovies+= 250;
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
}
