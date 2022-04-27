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

    public void getMoviesByGenre(String genre, int start) throws IOException {

        try {
            //while (MovieList.size() < 3000) {
                //Navigates to the IMDB website based on the passed genre and copy's html-code into the document 'movies'
                Document movies = Jsoup.connect("https://www.imdb.com/search/title/?title_type=feature&genres=" + genre + "&start=" + start + "&explore=genres&ref_=adv_nxt").get();

                Elements body = movies.select("div.lister-list");
                start += 50;
                //System.out.println(body);

                if (!body.isEmpty()) {

                    //lister.item is the list with the movie entries.
                    for (Element movie : body.select("h3, lister-item-header")) {
                        Elements movieLink = movie.select( "a[href^=/title/tt]");
                        //String movieHref = String.valueOf(movieLink.select("id:href"));
                        //System.out.println(movieLink);
                        String linkHref = movieLink.attr("href");
                        hreflink.add(linkHref);

                        Document movies2 = Jsoup.connect("https://www.imdb.com/search/title/?genres=" + genre + "&start=" + start + "&explore=title_type,genres&ref_=adv_nxt").get();
                        Elements body2 = movies.select("div.lister-list");
                        //scans for the movie poster and saves them as a link. They are found under lister-item-image and
                        // marked with a 'src' tag ,which specifies the location of an external resource.
                        //WORKING!!!
                        String img = movie.select("img, .load-late").attr("loadlate");
                        //System.out.println(img);

                        //retrieves the movie titles from body
                        String title = movie.select("a[href^=/title/]").text();

                        //retrieves release year of the movie by selecting the span element with the class name lister-item-year
                        //TODO format the String to remove parenthesis so it can be parsed to String -->  Stringbuilder or Regex
                        String releaseYear = (movie.select("span[class^=lister-item-year]").text());

                        //TODO do this the proper way !
                        String cheapSolutionForGenre = genre;

                        //TODO make this work somehow
                        String runtime = movie.select("p[class^=text-muted]").text();

                        //System.out.println(runtime);


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
                                System.out.println("length");
                                length = movieHeaderListItem.text();
                                System.out.println(length);
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


                        System.out.println(title);
                        System.out.println(length);
                        System.out.println(release);

                    }
                }
            }
        catch(IOException e){
                e.printStackTrace();
            }


        }
    //}



}
