package com.model;



import com.model.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crawler {

    public static void getMovies(String genre) throws IOException{

        try {
            //Navigates to the IMDB website based on the passed genre and copy's html-code into the document 'movies'
            Document movies = Jsoup.connect("https://www.imdb.com/search/title/?genres=" + genre +
                    "&title_type=tv_series,mini_series&explore=genres&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=b4e1d6fb-9821-4c7d-ad14-31ed10854442&pf_rd_r=24E9EQTTJXMRQDBDQDVJ&pf_rd_s=center-7&pf_rd_t=15051&pf_rd_i=genre&ref_=ft_gnr_tvpop_1").get();
            Elements body = movies.select("div.lister-list");

            if(!body.isEmpty()) {

                //lister.item is the list with the movie entries.
                for (Element movie : body.select("lister.item")) {

                    //scans for the movie poster and saves them as a link. They are found under lister-item-image and
                    // marked with a 'src' tag ,which specifies the location of an external resource.
                    String img = movie.select("lister-item-image img").attr("src");
                    //System.out.println(img);

                    //String title = movie.select("lister-item-header").attr("src");


                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }




    }




}
