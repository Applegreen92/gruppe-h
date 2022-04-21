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

    public void getMoviesByGenre(String genre) throws IOException{

        try {
            //Navigates to the IMDB website based on the passed genre and copy's html-code into the document 'movies'
            Document movies = Jsoup.connect("https://www.imdb.com/search/title/?genres="+ genre +"&title_type=feature&explore=genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=facfbd0c-6f3d-4c05-9348-22eebd58852e&pf_rd_r=EJ5YBM5AY176J6Y11SY7&pf_rd_s=center-6&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_mvpop_1").get();
            Elements body = movies.select("div.lister-list");
            //System.out.println(body);

            if(!body.isEmpty()) {

                //lister.item is the list with the movie entries.
                for (Element movie : body.select("h3, lister-item-header")) {

                    //scans for the movie poster and saves them as a link. They are found under lister-item-image and
                    // marked with a 'src' tag ,which specifies the location of an external resource.
                    //WORKING!!!
                    String img = movie.select("img, .load-late").attr("loadlate");

                    //retrieves the movie titles from body
                    String title = movie.select("a[href^=/title/]").text();

                    //retrieves release year of the movie by selecting the span element with the class name lister-item-year
                    //TODO format the String to remove parenthesis so it can be parsed to String -->  Stringbuilder or Regex
                    String releaseYear = (movie.select("span[class^=lister-item-year]").text());

                    //TODO do this the proper way !
                    String cheapSolutionForGenre = genre;

                    //TODO make this work somehow
                    String runtime = movie.select("p[class^=text-muted]").text();

                    System.out.println(runtime);



                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }




    }




}
