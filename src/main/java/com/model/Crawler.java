package com.model;

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
    ArrayList posterLink = new ArrayList();

    public void getMoviesByGenre(String genre, int start) throws IOException {

        try {
            //while (MovieList.size() < 3000) {
                //Navigates to the IMDB website based on the passed genre and copy's html-code into the document 'movies'
                Document movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&genres=" + genre + "&start=" + start + "&explore=genres&ref_=adv_nxt").get();
                Elements body = movies.select("div.lister-list");
                start += 50;
                //System.out.println(body);



                if (!body.isEmpty()) {

                    //Elements movieImage = movies.select("img.loadlate");

                    //Loading the Image links into an ArrayList
                    for(Element movieImage: movies.select("img.loadlate")){
                        String moviePoster = movieImage.attr("loadlate");
                        posterLink.add(moviePoster);
                        //System.out.println(moviePoster);
                    }

                    //lister.item is the list with the movie entries.
                    for (Element movie : body.select("h3, lister-item-header")) {
                        Elements movieLink = movie.select( "a[href^=/title/tt]");
                        String linkHref = movieLink.attr("href");
                        hreflink.add(linkHref);
                    }

                    for(int x = 0;x < hreflink.size(); x++){
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
                        System.out.println(movieDirector);
                        //Elements movieDirector = moviePersons.select("span.ipc-metadata-list-item__label");
                        //Elements test = moviePersons.select(movieDirector.text()).parents();
                        //Elements test = moviePersons.select(movieDirector.text()).parents();



                        //Elements moviePersonLink = moviePersons.select( "a[href^=/title/]");
                        //String linkHref = moviePersonLink.attr("href");
                        //String moviePersonUrl = "https://www.imdb.com" + linkHref;
                        //Document moviePerson = Jsoup.connect(moviePersonUrl).get();
                        //Elements moviePersonTable = moviePerson.select("div.header");

                        //System.out.println(moviePersonTable);



                        //System.out.println(title);
                        //System.out.println(length);
                        //System.out.println(release);
                        //System.out.println(movieGenreArray.toString());


                    }
                }
            }
        catch(IOException e){
                e.printStackTrace();
            }


        }
    //}



    public void getPosterByGenre(String genre, int start) throws IOException {
        try {
            Document movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&genres=" + genre + "&start=" + start + "&explore=genres&ref_=adv_nxt").get();
            Elements body = movies.select("div.lister-list");

            for(Element movieImage: movies.select("img.loadlate")){
                String moviePoster = movieImage.attr("loadlate");
                posterLink.add(moviePoster);
                System.out.println(moviePoster);
            }
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Error code 503 - service unavailable");
        }





    }



}
